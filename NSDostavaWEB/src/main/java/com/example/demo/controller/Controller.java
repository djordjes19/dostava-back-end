package com.example.demo.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DostavaService;

import model.User;
import model.Meal;
import model.Restaurant;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class Controller {

	@Autowired
	DostavaService ds;

//	@GetMapping("getKorisnici")
//	public List<User> getKorisnici() {
//		return ds.getKorisnici();
//	}
//
//	@GetMapping("getKuhinje")
//	public List<Kuhinja> getKuhinje() {
//		return ds.getKuhinje();
//	}
//
//	@GetMapping("getObroci")
//	public List<Meal> getObroci() {
//		return ds.getObroci();
//	}
//
//	@GetMapping("getPorudzbine")
//	public List<Porudzbina> getPorudzbine() {
//		return ds.getPorudzbine();
//	}
//
//	@GetMapping("getSastojci")
//	public List<Sastojak> getSastojci() {
//		return ds.getSastojci();
//	}
//
//	@GetMapping("getRestauranti")
//	public List<Restaurant> getRestauranti() {
//		return ds.getRestauranti();
//	}
//
//	@GetMapping("getTipUsera")
//	public List<TipUsera> getTipUsera() {
//		return ds.getTipUsera();
//	}

	@PostMapping("register")
	public ResponseEntity<Boolean> saveUser(@RequestBody User User) {
		return ds.saveKorisnik(User);
	}

	@PostMapping("login")
	public ResponseEntity<Response> login(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		return ds.login(username, password);
	}

	@GetMapping("allMeals")
	public ResponseEntity<List<Meal>> svaJela(@RequestParam(name = "name") String name) {
		return ds.svaJela(name);
	}

	@GetMapping("allRestaurants")
	public ResponseEntity<List<Restaurant>> sviRestorani() {
		return ds.sviRestorani();
	}

	@GetMapping("filter")
	public ResponseEntity<List<Meal>> filter(@RequestParam(name = "name") String name,
			@RequestParam(name = "price") String priceString, @RequestParam(name = "kcal") String kcalString)
			throws ParseException {

		return ds.filter(name, priceString, kcalString);
	}

	@GetMapping("filterRes")
	public ResponseEntity<List<Restaurant>> filterRes(@RequestParam(name = "name") String name,
			@RequestParam(name = "address") String address) throws ParseException {

		return ds.filterRes(name, address);
	}
	
	@PostMapping("addMeal")
	public ResponseEntity<Meal> addMeal(@RequestParam(name = "name") String name,
			@RequestParam(name = "price") double price, @RequestParam("kcal") int kcal,
			@RequestParam(name = "slika") String slika, @RequestParam(name = "res") String res) throws ParseException {

		return ds.addMeal(name, price, kcal, slika, res);
	}

	@PostMapping("addRes")
	public ResponseEntity<Restaurant> addRes(@RequestParam(name = "name") String name,
			@RequestParam(name = "address") String address, @RequestParam(name = "photo") String photo)
			throws ParseException {

		return ds.addRes(name, address, photo);
	}

	@PostMapping("deleteMeal")
	public ResponseEntity<Boolean> deleteMeal(@RequestParam(name = "name") String name) {
		return ds.deleteMeal(name);
	}

	@PostMapping("deleteRes")
	public ResponseEntity<Boolean> deleteRes(@RequestParam(name = "name") String name) {
		return ds.deleteRes(name);
	}

	@PostMapping("updateMeal")
	public ResponseEntity<Boolean> updateMeal(@RequestParam(name = "name") String name,
			@RequestParam(name = "price") String priceString, @RequestParam(name = "slika") String slika)
			throws ParseException {
		return ds.updateMeal(name, priceString, slika);
	}

	@PostMapping("updateRes")
	public ResponseEntity<Boolean> updateRes(@RequestParam(name = "name") String name,
			@RequestParam(name = "address") String address, @RequestParam(name = "photo") String photo) throws ParseException {
		return ds.updateRes(name, address, photo);
	}

	@GetMapping("getResByName")
	public ResponseEntity<Restaurant> getResByName(@RequestParam(name = "name") String name) {
		return ds.getResByName(name);
	}

	@GetMapping("getMealByName")
	public ResponseEntity<Meal> getMealByName(@RequestParam(name = "name") String name) {
		return ds.getMealByName(name);
	}

	@GetMapping("allUsers")
	public ResponseEntity<List<User>> allUsers() {
		return ds.allUsers();
	}

	@PostMapping("deleteUser")
	public ResponseEntity<Boolean> deleteUser(@RequestParam(name = "username") String username) {
		return ds.deleteUser(username);
	}

	@PostMapping("orderMeal")
	public ResponseEntity<Boolean> orderMeal(@RequestParam(name = "name") String name,
			@RequestParam(name = "username") String username) {
		return ds.orderMeal(name, username);
	}

}
