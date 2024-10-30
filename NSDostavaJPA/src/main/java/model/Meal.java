package model;

import java.io.Serializable;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the meal database table.
 * 
 */
@Entity
@NamedQuery(name="Meal.findAll", query="SELECT m FROM Meal m")
public class Meal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idmeal;

	private int kcal;

	private String name;

	private double price;

	private String slika;

	//bi-directional many-to-one association to Cuisine
	@ManyToOne
	private Cuisine cuisine;

	//bi-directional many-to-one association to Restaurant
	@ManyToOne
	private Restaurant restaurant;

	//bi-directional many-to-many association to Ingredient
	@ManyToMany
	@JoinTable(
		name="meal_has_ingredient"
		, joinColumns={
			@JoinColumn(name="meal_idmeal")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ingredient_idingredient")
			}
		)
	private List<Ingredient> ingredients;

	//bi-directional many-to-many association to Order
	@ManyToMany(mappedBy="meals")
	@JsonIgnore
	private List<Order> orders;

	public Meal() {
	}

	public int getIdmeal() {
		return this.idmeal;
	}

	public void setIdmeal(int idmeal) {
		this.idmeal = idmeal;
	}

	public int getKcal() {
		return this.kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSlika() {
		return this.slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public Cuisine getCuisine() {
		return this.cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}