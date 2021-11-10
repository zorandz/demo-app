package com.zoran.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.zoran.demo.entities.State;

@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Integer> { 
	
	List<State> findByCountryCode(@Param("code") String code);
	
	// http://localhost:8080/api/states/search/findByCountryCode?code=US

}
