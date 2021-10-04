package com.example.PracticeJPADemo.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.PracticeJPADemo.model.Alien;

public interface AlienJPARepository extends JpaRepository<Alien, Integer>{

	List<Alien> findByTech(String tech);
	
	List<Alien> findByAidGreaterThan(int aid);
	
	@Query("from Alien where tech=?1 order by aname")
	List<Alien> findByTechOrderByName(String tech);

}
