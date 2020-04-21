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

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
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
	/// di debug
	private long current_id = 48;


	// FOOD
	@GetMapping(value = "/test/food/addFood")
	public Food testaddFood() {
		LocalDate date = LocalDate.of(2020,04,20);
		Food food = new Food("Pomodoro", this.current_user_id,"https://www.supermercato24.it/asset/smhd/28f2e/27ce9/a5c3d/2045729846_img.jpg", date, Category.a);
		System.out.println("\nAdding new Food: "+food);
		Food _food = food_repository.save(new Food(food.getName(), this.current_user_id, food.getUrl_img(), food.getExpiry_date(), food.getCategory()));

		current_id = _food.getId()+1;

		return _food;
	}

	@PostMapping(value = "/food/addFood")
	public Food addFood(@RequestBody Food food) {
		System.out.println("\nAdding new Food: "+food);
		Food _food = food_repository.save(new Food(food.getName(), this.current_user_id, food.getUrl_img(), food.getExpiry_date(), food.getCategory()));
		return _food;
	}


	@GetMapping(value = "/food/getFoodPerCategory/{category}")
	public List<Food> getFoodPerCategory(@PathVariable Category category) {
		System.out.println("\nGet Foods per Category: " + category);
		List<Food> foods = food_repository.findByCategory(category);
		return foods;
	}

	@GetMapping(value = "/food/getAllFood")
	public List<Food> getAllFood() {
		System.out.println("\nGet All Foods!");
		List<Food> foods = (List<Food>) food_repository.findAll();
		return foods;
	}

	@DeleteMapping("/food/remove/{id}")
	public ResponseEntity<String> removeFood(@PathVariable("id") long id) {
		System.out.println("\nDelete Food with ID = " + id );
		if (food_repository.existsById(id)) {
			food_repository.deleteById(id);
			return new ResponseEntity<>("Food has been deleted!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Food not found!", HttpStatus.OK);
		}
	}

	// EVENT

    /*@GetMapping(value = "/test")
    public String test(HttpServletRequest request) {
		String server_url = request.getRequestURL().toString().replaceAll(request.getRequestURI(),"");
		return server_url;
    }*/

    @GetMapping(value = "/test/event/addEvent/{month}")
	public Event testaddEvent(@PathVariable int month,HttpServletRequest request) {
    	String server_url = request.getRequestURL().toString().replaceAll(request.getRequestURI(),"/api/event/remove/"+String.valueOf(current_id));
		Event event = new Event("gennaio_event",0,"https.jpg",2020,month,20, server_url);
		System.out.println("\nAdding new Event: "+ event);
		Event _event = event_repository.save(new Event(event.getName(), this.current_user_id, event.getUrl_img(), event.getYear(), event.getMonth(), event.getDay(), event.getUrl_callback() ));

		this.current_id = _event.getId()+1;

		return _event;
	}

	@GetMapping(value = "/event/getEvents/{year}/{month}")
	public List<Event> getEvents(@PathVariable int year,@PathVariable int month) {
		System.out.println("\nGet Events in " + year + "-" + month + "*");
		//System.out.print(event_repository.findAll());
		return event_repository.findAllByYearEqualsAndMonthEqualsAndDayGreaterThanEqual(year,month,0);
	}


	@PostMapping(value = "/event/createEvent")
	public Event addFood(@RequestBody Event event) {
		System.out.println("\nAdding new Event: "+ event);

		//LocalDate date = LocalDate.of(2020,04,20);
		//Event _event = event_repository.save(new Event( event.getName(), this.current_user_id, event.getUrl_img(), event.getExpiry_date()));
		Event _event = null;
		return _event;
	}


	@DeleteMapping("/event/remove/{id}")
	public ResponseEntity<String> removeEvent(@PathVariable("id") long id) {
		System.out.println("\nDelete Food with ID = " + id );
		if (event_repository.existsById(id)) {
			event_repository.deleteById(id);
			return new ResponseEntity<>("Event has been deleted!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Event not found!", HttpStatus.OK);
		}
	}
	/*

	@DeleteMapping("/customers/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {
		System.out.println("Delete Customer with ID = " + id + "...");
		event_repository.deleteById(id);
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
