package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface KeyInfoRepository extends JpaRepository<KeyInfo, Long> {
	@Query("select distinct make from KeyInfo")
	List<KeyInfo> findDistinctMakes();
	@Query("select distinct model from KeyInfo u where u.make = ?1")
	List<KeyInfo> findModelsForMake(String make);
	@Query("select distinct year FROM KeyInfo u where u.make = ?1 and u.model=?2")
	List<KeyInfo> findInfo(String make, String model);
	
	//List<KeyInfo> findAllByMakeAndByModel(String make, String model);
}
