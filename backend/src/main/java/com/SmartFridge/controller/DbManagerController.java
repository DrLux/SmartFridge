package com.smartfridge.controller;

import com.smartfridge.model.Event;
import com.smartfridge.model.Food;
import com.smartfridge.repo.EventRepository;
import com.smartfridge.repo.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DbManagerController {

	@Autowired
	FoodRepository food_repository;
	@Autowired
	EventRepository event_repository;

	@GetMapping(value = "/food/get")
	public List<Food> getAllFoods() {
		System.out.println("Get all Foods...");

		List<Food> foods = new ArrayList<>();
		food_repository.findAll().forEach(foods::add);

		return foods;
	}


	@PostMapping(value = "/food/create")
	public Food postCustomer(@RequestBody Food food) {
		System.out.println("Create new Food...");
		Food _food = food_repository.save(new Food(food.getUrl_img(), food.getName()));
		return _food;
	}

	@GetMapping(value = "/event/get")
	public List<Event> getAllEvents() {
		System.out.println("Get all Events...");

		List<Event> events = new ArrayList<>();
		event_repository.findAll().forEach(events::add);

		return events;
	}

	@PostMapping(value = "/event/create")
	public Event postEvents(@RequestBody Event event) {
		System.out.println("Create new Event...");
		Event _event = event_repository.save(new Event(event.getUrl_img(), event.getName()));
		return _event;
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
}
