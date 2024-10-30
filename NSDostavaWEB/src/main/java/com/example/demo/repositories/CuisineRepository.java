package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Cuisine;

public interface CuisineRepository extends JpaRepository<Cuisine, Integer> {

	Cuisine findByName(String string);

}
