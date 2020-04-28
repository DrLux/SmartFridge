package com.smartfridge.controller;

import com.smartfridge.model.Event;
import com.smartfridge.model.Food;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fridge")
public class FridgeService {

    //https://www.baeldung.com/spring-5-webclient
    WebClient client = WebClient.create();
    String db_manager_url = "http://localhost:8081/api/";

    @PostMapping(value = "/addFood")
    public Food addFood(@RequestBody Food food) {
        System.out.print("\n Asking all foods from DB_manger");

        String uri = "/food/addFood";
        Food response =  this.client
                                    .method(HttpMethod.POST)
                                    .uri(db_manager_url+uri)
                                    .body(BodyInserters.fromObject(food))
                                    .retrieve()
                                    .bodyToMono(Food.class)
                                    .block();
        return  response;
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

    @GetMapping("/to_shoplist/{id}")
    public void toShoplist(@PathVariable("id") long id){
        //creare un get in dbmanager e richiamarlo da qui
        ///il get prende l' id del food e ne estrapola i dati per creare l' item

        System.out.print("\n Asking DB_manger to add Food to shopList");
        String uri = "/";
        response =  this.client
                .method(HttpMethod.GET)
                .uri(db_manager_url+uri)
                .retrieve()
                .bodyToMono(List.class)
                .block();
    }


}
