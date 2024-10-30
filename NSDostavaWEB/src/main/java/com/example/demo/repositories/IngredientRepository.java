package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
