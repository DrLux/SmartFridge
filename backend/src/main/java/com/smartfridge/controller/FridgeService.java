package com.smartfridge.controller;

import com.google.gson.Gson;
import com.smartfridge.model.Event;
import com.smartfridge.model.ShopItem;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import com.smartfridge.model.Food;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/fridge")
public class FridgeService {
    //https://www.baeldung.com/spring-5-webclient
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

    @GetMapping("/helloFridge")
    public String helloFridge(){
        System.out.print("\n Asking to say 'hello' to DB_manger\n");

        String uri = "dbManager/hello";
        String response =  this.client
                .method(HttpMethod.GET)
                .uri(db_manager_url+uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return  response;
    }

    @PostMapping("/addFood")
    public Food addFood(@RequestBody Food food) {
        System.out.print("\n Asking DB_manger to add food\n");
        String uri = "dbManager/food/addFood";

        System.out.print("link" + db_manager_url + uri);

        //Create Event
        Event event_to_add = new Event(food.getName(), food.getUser_id(), food.getUrl_img(), food.getYear(), food.getMonth(), food.getDay());
        Event event_response =  this.client
                .method(HttpMethod.POST)
                .uri(db_manager_url+"dbManager/event/createEvent")
                .body(BodyInserters.fromObject(event_to_add))
                .retrieve()
                .bodyToMono(Event.class)
                .block();

        //Create Food
        food.setAssociated_event(event_response.getId());
        Food response =  this.client
                .method(HttpMethod.POST)
                .uri(db_manager_url+uri)
                .body(BodyInserters.fromObject(food))
                .retrieve()
                .bodyToMono(Food.class)
                .block();


        return  response;

    }

    //Service API
    @GetMapping("/getFoodPerCategory/{category}")
    public String getFoodPerCategory (@PathVariable String category) {
        // Ask to db_manager
        String uri = "dbManager/food/getFoodPerCategory/"+category;

        System.out.print("\n Asking getFood to DB_manger at: \n" + db_manager_url+uri);
        List<Food> foods =  this.client
                            .method(HttpMethod.GET)
                            .uri(db_manager_url+uri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToFlux(Food.class)
                            .collectList()
                            .log()
                            .block();

        //Create response object
        Dictionary response = new Hashtable();

        //Add data to response
        for (int idx = 0; idx < foods.size(); idx++){
            Dictionary item = new Hashtable();

            Food temp_food = (Food) foods.get(idx);

            item.put("food", temp_food);
            item.put("to_shoplist_callback", db_manager_url+"fridge/food_to_shopitem/"+temp_food.getId());
            item.put("delete_callback", db_manager_url+"fridge/remove/"+temp_food.getId());
            response.put(idx, item);
        }


        // Encode response to json
        Gson gson = new Gson();
        String json_response = gson.toJson(response);

        return json_response;
    }


    @GetMapping("/food_to_shopitem/{id}")
    public String toShoplist(@PathVariable("id") long id){

        System.out.print("\n Asking DB_manger to add Food to shopList");
        String uri = "dbManager/food/food_to_shopitem/";

        ShopItem response = this.client
                .method(HttpMethod.GET)
                .uri(db_manager_url + uri + id)
                .retrieve()
                .bodyToMono(ShopItem.class)
                .block();

        return response.toString();
    }

    @DeleteMapping("/remove/{id}")
    public String removeEvent(@PathVariable("id") long id) {
        System.out.print("\n Asking DB_manger to remove Food");
        String uri = "dbManager/food/remove/"+id;

        //Get food to delete
        Food food_to_remove = this.client
                                    .method(HttpMethod.GET)
                                    .uri(db_manager_url+"dbManager/food/getFoodByID/"+id)
                                    .retrieve()
                                    .bodyToMono(Food.class)
                                    .block();

        //Delete associated Event
        String response_event =  this.client
                                        .method(HttpMethod.DELETE)
                                        .uri(db_manager_url+"dbManager/event/remove/"+food_to_remove.getAssociated_event())
                                        .retrieve()
                                        .bodyToMono(String.class)
                                        .block();

        String response =  this.client
                .method(HttpMethod.DELETE)
                .uri(db_manager_url+uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return  response;
    }

}
