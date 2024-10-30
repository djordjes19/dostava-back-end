package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer> {

	Meal findByName(String name);

	List<Meal> findAllByName(String name);
	
	@Query("select m from Meal m where m.restaurant.name = :name")
	List<Meal> findAllByResName(@Param("name") String name);

}
