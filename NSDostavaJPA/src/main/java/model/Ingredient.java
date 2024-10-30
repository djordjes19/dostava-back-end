package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the ingredient database table.
 * 
 */
@Entity
@NamedQuery(name="Ingredient.findAll", query="SELECT i FROM Ingredient i")
public class Ingredient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idingredient;

	private String name;

	//bi-directional many-to-many association to Meal
	@ManyToMany(mappedBy="ingredients")
	private List<Meal> meals;

	public Ingredient() {
	}

	public int getIdingredient() {
		return this.idingredient;
	}

	public void setIdingredient(int idingredient) {
		this.idingredient = idingredient;
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

}