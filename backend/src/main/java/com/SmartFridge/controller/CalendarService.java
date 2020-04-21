package com.smartfridge.controller;

import com.smartfridge.model.Event;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/calendar")
public class CalendarService {

    //https://www.baeldung.com/spring-5-webclient
    WebClient client = WebClient.create();
    String db_manager_url = "http://localhost:8081/api/event";

    @GetMapping(value = "/getEvents/{year}/{month}")
    public List<Event> getEvents(@PathVariable int year,@PathVariable int month) {
        System.out.print("\n Asking all event from DB_manger");

        String uri = "/getEvents/"+String.valueOf(year)+"/"+String.valueOf(month);
        List<?> response =  this.client
                                    .method(HttpMethod.GET)
                                    .uri(db_manager_url+uri)
                                    .retrieve()
                                    .bodyToMono(List.class)
                                    .block();
        return  (List<Event>) response;
    }

    @DeleteMapping("/event/remove/{id}")
    public String removeEvent(@PathVariable("id") long id) {
        System.out.print("\n Asking DB_manger to remove Event");
        String uri = "/remove/"+String.valueOf(id);
        String response =  this.client
                .method(HttpMethod.DELETE)
                .uri(db_manager_url+uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.print(response);
        return  response;
    }

}
