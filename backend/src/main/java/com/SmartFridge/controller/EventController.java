/*
package com.smartfridge.controller;

import com.smartfridge.model.Event;
import com.smartfridge.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api1")
public class EventController {

	@Autowired
	EventRepository repository;

	@GetMapping("/event")
	public List<Event> getAllEvents() {
		System.out.println("Get all Events...");

		List<Event> events = new ArrayList<>();
		repository.findAll().forEach(events::add);

		return events;
	}

	@PostMapping(value = "/event/create")
	public Event postEvents(@RequestBody Event event) {
		System.out.println("Create new Event...");
		Event _event = repository.save(new Event(event.getUrl_img(), event.getName()));
		return _event;
	}

	@DeleteMapping("/event/delete")
	public ResponseEntity<String> deleteAllEvents() {
		System.out.println("Delete All Events...");

		repository.deleteAll();

		return new ResponseEntity<>("All Events have been deleted!", HttpStatus.OK);
	}

}
*/