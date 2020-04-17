/*
package com.smartfridge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.smartfridge.repo.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartfridge.model.Food;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FoodController {

	@Autowired
	FoodRepository repository;

	@GetMapping("/food")
	public List<Food> getAllFoods() {
		System.out.println("Get all Foods...");

		List<Food> foods = new ArrayList<>();
		repository.findAll().forEach(foods::add);

		return foods;
	}

	@PostMapping(value = "/food/create")
	public Food postCustomer(@RequestBody Food food) {
		System.out.println("Create new Food...");
		Food _food = repository.save(new Food(food.getUrl_img(), food.getName()));
		return _food;
	}

	@DeleteMapping("/food/delete")
	public ResponseEntity<String> deleteAllCustomers() {
		System.out.println("Delete All Foods...");

		repository.deleteAll();

		return new ResponseEntity<>("All Foods have been deleted!", HttpStatus.OK);
	}


	/*

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		repository.deleteById(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}


	@GetMapping(value = "customers/age/{age}")
	public List<Food> findByAge(@PathVariable int age) {

		List<Food> foods = repository.findByAge(age);
		return foods;
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Food> updateCustomer(@PathVariable("id") long id, @RequestBody Food food) {
		System.out.println("Update Customer with ID = " + id + "...");

		Optional<Food> customerData = repository.findById(id);

		if (customerData.isPresent()) {
			Food _food = customerData.get();
			_food.setName(food.getName());
			_food.setAge(food.getAge());
			_food.setActive(food.isActive());
			return new ResponseEntity<>(repository.save(_food), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	 */
