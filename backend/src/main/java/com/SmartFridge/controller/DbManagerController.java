package com.smartfridge.controller;

import com.smartfridge.model.Category;
import com.smartfridge.model.Event;
import com.smartfridge.model.Food;
import com.smartfridge.repo.EventRepository;
import com.smartfridge.repo.FoodRepository;
import com.smartfridge.repo.ShopItemRepository;
import com.smartfridge.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	@Autowired
	ShopItemRepository shopitem_repository;
	@Autowired
	UsersRepository user_repository;

	private long current_user_id = 1;


	// FOOD
	@GetMapping(value = "/test/food/addFood")
	public Food testaddFood() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date dateobj = new Date();
		Food food = new Food("Pomodoro", this.current_user_id,"https://www.supermercato24.it/asset/smhd/28f2e/27ce9/a5c3d/2045729846_img.jpg", dateobj, Category.a);
		System.out.println("Adding new Food: "+food);
		Food _food = food_repository.save(new Food(food.getName(), this.current_user_id, food.getUrl_img(), food.getExpiry_date(), food.getCategory()));
		return _food;
	}

	@PostMapping(value = "/food/addFood")
	public Food addFood(@RequestBody Food food) {
		System.out.println("Adding new Food: "+food);
		Food _food = food_repository.save(new Food(food.getName(), this.current_user_id, food.getUrl_img(), food.getExpiry_date(), food.getCategory()));
		return _food;
	}


	@GetMapping(value = "/food/getFoodPerCategory/{category}")
	public List<Food> getFoodPerCategory(@PathVariable Category category) {
		System.out.println("Get Foods per Category: " + category);
		List<Food> foods = food_repository.findByCategory(category);
		return foods;
	}

	@GetMapping(value = "/food/getAllFood")
	public List<Food> getAllFood() {
		System.out.println("Get All Foods!");
		List<Food> foods = (List<Food>) food_repository.findAll();
		return foods;
	}

	@DeleteMapping("/food/remove/{id}")
	public ResponseEntity<String> removeFood(@PathVariable("id") long id) {
		System.out.println("Delete Food with ID = " + id );
		food_repository.deleteById(id);
		return new ResponseEntity<>("Food has been deleted!", HttpStatus.OK);
	}

	// EVENT
	@PostMapping(value = "/event/createEvent")
	public Event addFood(@RequestBody Event event) {
		System.out.println("Adding new Event: "+ event);
		Event _event = event_repository.save(new Event( event.getName(), this.current_user_id, event.getUrl_img(), event.getExpiry_date()));
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
