package model;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the cuisine database table.
 * 
 */
@Entity
@NamedQuery(name="Cuisine.findAll", query="SELECT c FROM Cuisine c")
public class Cuisine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idcuisine;

	private String name;

	//bi-directional many-to-one association to Meal
	@OneToMany(mappedBy="cuisine")
	@JsonIgnore
	private List<Meal> meals;

	public Cuisine() {
	}

	public int getIdcuisine() {
		return this.idcuisine;
	}

	public void setIdcuisine(int idcuisine) {
		this.idcuisine = idcuisine;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Meal> getMeals() {
		return this.meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public Meal addMeal(Meal meal) {
		getMeals().add(meal);
		meal.setCuisine(this);

		return meal;
	}

	public Meal removeMeal(Meal meal) {
		getMeals().remove(meal);
		meal.setCuisine(null);

		return meal;
	}

}