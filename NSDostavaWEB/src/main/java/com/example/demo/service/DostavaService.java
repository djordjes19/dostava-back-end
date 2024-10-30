package com.example.demo.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.Response;
import com.example.demo.repositories.CuisineRepository;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.repositories.MealRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.RestaurantRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserTypeRepository;

import model.Meal;
import model.Order;
import model.Restaurant;
import model.User;
import model.UserType;

@Service
public class DostavaService {

	@Autowired
	CuisineRepository cr;

	@Autowired
	IngredientRepository ir;

	@Autowired
	MealRepository mr;

	@Autowired
	OrderRepository or;

	@Autowired
	RestaurantRepository rr;

	@Autowired
	UserRepository ur;

	@Autowired
	UserTypeRepository utr;
	
	
	

	
	public ResponseEntity<Boolean> saveKorisnik(@RequestBody User user) {
		if (user.getUsername() != null && ur.findByUsername(user.getUsername()) != null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		User u = new User();
		u.setIme(user.getIme());
		u.setPrezime(user.getPrezime());
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setAdresa(user.getAdresa());
		List<UserType> utl = utr.findAll();
		Random rand = new Random();
		UserType ut = utl.get(rand.nextInt(utl.size()));
//		UserType ut = utr.findByName(user.getUserType().getName());
		System.out.println(ut.getName());
		u.setUserType(ut);
		ur.save(u);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	
	public ResponseEntity<Response> login(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		Response r = new Response();

		if (ur.findByUsername(username) != null) {
			if (((User) ur.findByUsername(username)).getPassword().equals(password)) {
				r.setUsername(username);
				r.setIduserType(((User) ur.findByUsername(username)).getUserType().getIduserType());
				r.setIme(((User) ur.findByUsername(username)).getIme());
				r.setPrezime(((User) ur.findByUsername(username)).getPrezime());
			}
		}
		return new ResponseEntity<Response>(r, HttpStatus.OK);
	}

	
	public ResponseEntity<List<Meal>> svaJela(@RequestParam(name = "name") String name) {
		Restaurant r = rr.findByName(name);
		List<Meal> lista = r.getMeals();
		return new ResponseEntity<List<Meal>>(lista, HttpStatus.OK);
	}

	
	public ResponseEntity<List<Restaurant>> sviRestorani() {
		return new ResponseEntity<List<Restaurant>>(rr.findAll(), HttpStatus.OK);
	}

	
	public ResponseEntity<List<Meal>> filter(@RequestParam(name = "name") String name,
			@RequestParam(name = "price") String priceString, @RequestParam(name = "kcal") String kcalString)
			throws ParseException {

		List<Meal> lista = mr.findAll();
		List<Meal> glavnaLista = new ArrayList<Meal>();

		// sva 3 parametra uneta

		if (!name.equals("") && !priceString.equals("") && !kcalString.equals("")) {
			int price = Integer.parseInt(priceString);
			glavnaLista = lista.stream().filter(x -> x.getName().contains(name) && x.getPrice() <= price
					&& x.getKcal() == (Integer.parseInt(kcalString))).collect(Collectors.toList());
		}

		// name and price
		else if (!name.equals("") && !priceString.equals("") && kcalString.equals("")) {
			int price = Integer.parseInt(priceString);
			glavnaLista = lista.stream().filter(x -> x.getName().contains(name) && x.getPrice() <= price)
					.collect(Collectors.toList());
		}

		// name and kcal
		else if (!name.equals("") && priceString.equals("") && !kcalString.equals("")) {
			glavnaLista = lista.stream()
					.filter(x -> x.getName().contains(name) && x.getKcal() == (Integer.parseInt(kcalString)))
					.collect(Collectors.toList());
		}

		// price and kcal

		else if (name.equals("") && !priceString.equals("") && !kcalString.equals("")) {
			int cena = Integer.parseInt(priceString);
			glavnaLista = lista.stream()
					.filter(x -> x.getKcal() == (Integer.parseInt(kcalString)) && x.getPrice() <= cena)
					.collect(Collectors.toList());
		}

		// name

		else if (!name.equals("") && priceString.equals("") && kcalString.equals("")) {
			glavnaLista = lista.stream().filter(x -> x.getName().contains(name)).collect(Collectors.toList());
		}

		// price

		else if (name.equals("") && !priceString.equals("") && kcalString.equals("")) {
			int cena = Integer.parseInt(priceString);
			glavnaLista = lista.stream().filter(x -> x.getPrice() <= cena).collect(Collectors.toList());
		}

		// kcal
		else if (name.equals("") && priceString.equals("") && !kcalString.equals("")) {
			glavnaLista = lista.stream().filter(x -> x.getKcal() == (Integer.parseInt(kcalString)))
					.collect(Collectors.toList());
		}

		return new ResponseEntity<List<Meal>>(glavnaLista, HttpStatus.OK);
	}

	
	public ResponseEntity<List<Restaurant>> filterRes(@RequestParam(name = "name") String name,
			@RequestParam(name = "address") String address) throws ParseException {

		List<Restaurant> lista = rr.findAll();
		List<Restaurant> glavnaLista = new ArrayList<Restaurant>();

		// name and address
		if (!name.equals("") && !address.equals("")) {
			glavnaLista = lista.stream().filter(x -> x.getName().contains(name) && x.getAddress().contains(address))
					.collect(Collectors.toList());
		}

		// name

		else if (!name.equals("") && address.equals("")) {
			glavnaLista = lista.stream().filter(x -> x.getName().contains(name)).collect(Collectors.toList());
		}

		// address
		else if (name.equals("") && !address.equals("")) {
			glavnaLista = lista.stream().filter(x -> x.getAddress().contains(address)).collect(Collectors.toList());
		}

		return new ResponseEntity<List<Restaurant>>(glavnaLista, HttpStatus.OK);
	}

	
	public ResponseEntity<Meal> addMeal(@RequestParam(name = "name") String name,
			@RequestParam(name = "price") double price, @RequestParam("kcal") int kcal,
			@RequestParam(name = "slika") String slika, @RequestParam(name = "res") String res) throws ParseException {
		Meal meal = new Meal();
		meal.setName(name);
		meal.setSlika(slika);
		meal.setPrice(price);
		meal.setKcal(kcal);
		Restaurant restaurant = rr.findByName(res);
		meal.setRestaurant(restaurant);
		meal.setCuisine(cr.findByName("domaca"));
		restaurant.getMeals().add(meal);
		mr.save(meal);

		return new ResponseEntity<Meal>(meal, HttpStatus.OK);
	}

	
	public ResponseEntity<Restaurant> addRes(@RequestParam(name = "name") String name,
			@RequestParam(name = "address") String address, @RequestParam(name = "photo") String photo) throws ParseException {
		Restaurant res = new Restaurant();
		res.setName(name);
		res.setAddress(address);
		res.setPhoto(photo);
		rr.save(res);

		return new ResponseEntity<Restaurant>(res, HttpStatus.OK);
	}

	
	public ResponseEntity<Boolean> deleteMeal(@RequestParam(name = "name") String name) {
		Meal meal = mr.findByName(name);
		System.out.println(meal.getName());
		List<Order> order = meal.getOrders();
		System.out.println(111);
//		if (!order.isEmpty()) {
//			System.out.println(123);
//			for (Order o : order) {
//				for (Meal m : o.getMeals()) {
//					if (m.equals(meal)) {
//						or.delete(o);
//						break;
//					}
//				}
//			}
//		}
		mr.delete(meal);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	
	public ResponseEntity<Boolean> deleteRes(@RequestParam(name = "name") String name) {
		Restaurant res = rr.findByName(name);
		List<Meal> meals = res.getMeals();
		for (Meal m : meals) {
			m.getIngredients().clear();
			mr.delete(m);
		}
		rr.delete(res);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	
	public ResponseEntity<Boolean> updateMeal(@RequestParam(name = "name") String name,
			@RequestParam(name = "price") String priceString, @RequestParam(name = "slika") String slika)
			throws ParseException {
		Meal m = mr.findByName(name);

		// price
		if (priceString != null && !priceString.equals("")) {
			m.setPrice(Double.parseDouble(priceString));
			mr.save(m);
		}

		// photo
		if (slika != null && !slika.equals("")) {
			m.setSlika(slika);
			mr.save(m);
		}

		else
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	
	public ResponseEntity<Boolean> updateRes(@RequestParam(name = "name") String name,
			@RequestParam(name = "address") String address, @RequestParam(name = "photo") String photo) throws ParseException {
		Restaurant r = rr.findByName(name);

		// address
		if (!address.equals("")) {
			r.setAddress(address);
			rr.save(r);
		}

		if (!photo.equals("")) {
			r.setPhoto(photo);
			rr.save(r);
		}

		else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);

		}

		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	
	public ResponseEntity<Restaurant> getResByName(@RequestParam(name = "name") String name) {
		return new ResponseEntity<Restaurant>(rr.findByName(name), HttpStatus.OK);
	}

	public ResponseEntity<Meal> getMealByName(@RequestParam(name = "name") String name) {
		return new ResponseEntity<Meal>(mr.findByName(name), HttpStatus.OK);
	}

	public ResponseEntity<List<User>> allUsers() {
		return new ResponseEntity<List<User>>(ur.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<Boolean> deleteUser(@RequestParam(name = "username") String username) {
		User u = (User) ur.findByUsername(username);
		ur.delete(u);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	public ResponseEntity<Boolean> orderMeal(@RequestParam(name = "name") String name,
			@RequestParam(name = "username") String username) {
		Meal m = mr.findByName(name);
		List<Order> orders = m.getOrders();
		User u = (User) ur.findByUsername(username);
		Order o = new Order();
		o.getMeals().add(m);
		o.setUserIduser(u.getIduser());
//		u.getOrders().add(o);
		orders.add(o);
		or.save(o);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
