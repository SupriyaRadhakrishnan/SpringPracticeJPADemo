package com.example.PracticeJPADemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.PracticeJPADemo.dao.AlienJPARepository;
import com.example.PracticeJPADemo.model.Alien;


//We can soecify RestController instead of Controller and remove all the @ResponseBody tags , which means all the methods will act as restcontroller and
//we can have GetMapping and PostMapping instead of RequestMapping
@RestController
public class AlienController {
	
	@Autowired
	AlienJPARepository  arep;
	
	@GetMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	
	@GetMapping("/addAlien")
	public String addAlien(Alien alien)
	{
		
		arep.save(alien);
		return "home.jsp";
	}
	@GetMapping("/getAlien")
	public ModelAndView getAlien(int aid)
	{
			ModelAndView mv = new ModelAndView();
		Alien alien = arep.findById(aid).orElse(new Alien());
		
		System.out.println(arep.findByTech("java"));
		System.out.println(arep.findByAidGreaterThan(101));
		
		System.out.println(arep.findByTechOrderByName("java"));
		mv.addObject("alien",alien);
		mv.setViewName("showAlien.jsp");
		return mv;
	}
	
	@GetMapping("/aliens") // @GetMapping("/aliens",produces ={"application/xml"}) restricts the output to only XML
	public List<Alien> getAliens()
	{
		return arep.findAll();
	}

	
	@GetMapping("/alien/{aid}")
	public Optional<Alien> getAliens(@PathVariable("aid") int aid)
	{
		return arep.findById(aid);
	}
	
	@PostMapping("/alien")
	public Alien addAnAlien(@RequestBody Alien alien)//@RequestBody is used for adding the data ad raw data. @PostMapping("/alien",consumes ="application/json") 
	{
		
		arep.save(alien);
		return alien;
	}
	
	@DeleteMapping("/alien/{aid}")
	public void deleteAlien(@PathVariable int aid)
	{
		arep.deleteById(aid);
		
	}
	
	@PutMapping("/alien")
	public Alien updateAlien(@RequestBody Alien alien)//@RequestBody is used for adding the data ad raw data. @PostMapping("/alien",consumes ="application/json") 
	{
		
		arep.save(alien);
		return alien;
	}

}
