package model;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the restaurant database table.
 * 
 */
@Entity
@NamedQuery(name="Restaurant.findAll", query="SELECT r FROM Restaurant r")
public class Restaurant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idrestaurant;

	private String address;
	
	private String photo;

	private String name;

	//bi-directional many-to-one association to Meal
	@OneToMany(mappedBy="restaurant")
	@JsonIgnore
	private List<Meal> meals;

	public Restaurant() {
	}

	public int getIdrestaurant() {
		return this.idrestaurant;
	}

	public void setIdrestaurant(int idrestaurant) {
		this.idrestaurant = idrestaurant;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		meal.setRestaurant(this);

		return meal;
	}

	public Meal removeMeal(Meal meal) {
		getMeals().remove(meal);
		meal.setRestaurant(null);

		return meal;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}