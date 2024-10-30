package model;

import java.io.Serializable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idorder;

	@Column(name="user_iduser")
	private int userIduser;

	//bi-directional many-to-many association to Meal
	@ManyToMany
	@JoinTable(
		name="order_has_meal"
		, joinColumns={
			@JoinColumn(name="order_idorder")
			}
		, inverseJoinColumns={
			@JoinColumn(name="meal_idmeal")
			}
		)
	private List<Meal> meals;

	public Order() {
	}

	public int getIdorder() {
		return this.idorder;
	}

	public void setIdorder(int idorder) {
		this.idorder = idorder;
	}

	public int getUserIduser() {
		return this.userIduser;
	}

	public void setUserIduser(int userIduser) {
		this.userIduser = userIduser;
	}

	public List<Meal> getMeals() {
		if (this.meals == null)
			this.meals = new ArrayList<Meal>();
		return this.meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

}