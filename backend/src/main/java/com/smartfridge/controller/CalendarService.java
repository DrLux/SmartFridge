package com.smartfridge.controller;

import com.smartfridge.model.Event;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/calendar")
public class CalendarService {

    WebClient client = WebClient.create();
    //String db_manager_url = "https://" + System.getenv("HEROKU_APP_NAME") + ".herokuapp.com/";
    String db_manager_url = setUrl();

    public String setUrl(){
        if (System.getenv("HEROKU_APP_NAME") == null){
            return  "http://localhost:5000/";
        } else {
            return  "https://" + System.getenv("HEROKU_APP_NAME") + ".herokuapp.com/";
        }
    }

    @GetMapping(value = "/getEvents/{year}/{month}")
    public List<Event> getEvents(@PathVariable int year, @PathVariable int month) {

        String uri = "dbManager/event/getEvents/"+String.valueOf(year)+"/"+String.valueOf(month);
        System.out.print("\n Asking all event from DB_manger at: "+db_manager_url + uri);
        List<?> response =  this.client
                .method(HttpMethod.GET)
                .uri(db_manager_url+uri)
                .retrieve()
                .bodyToMono(List.class)
                .block();
        return  (List<Event>) response;
    }

}
