package com.smartfridge.controller;

import com.google.gson.Gson;
import com.smartfridge.model.Event;
import com.smartfridge.model.Food;
import com.smartfridge.model.ShopItem;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/shoplist")
public class ShopItemService {

    WebClient client = WebClient.create();
    String db_manager_url = setUrl();




    public String setUrl() {
        if (System.getenv("HEROKU_APP_NAME") == null) {
            return "http://localhost:5000/";
        } else {
            return "https://" + System.getenv("HEROKU_APP_NAME") + ".herokuapp.com/";
        }
    }

    @GetMapping("/getItems")
    public String getShopitems() {
        // Ask to db_manager
        String uri = "dbManager/shopitems/getItems";

        System.out.print("\n Asking Shopitems to DB_manger at: \n" + db_manager_url + uri);
        List<ShopItem> shopitems = this.client
                .method(HttpMethod.GET)
                .uri(db_manager_url + uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ShopItem.class)
                .collectList()
                .log()
                .block();

        System.out.println("shopitems: "+shopitems);


        //Create response object
        Dictionary response = new Hashtable();

        //Add data to response
        for (int idx = 0; idx < shopitems.size(); idx++){
            Dictionary item = new Hashtable();

            ShopItem temp_shopitem = (ShopItem) shopitems.get(idx);

            item.put("shopitem", temp_shopitem);
            item.put("delete_callback", db_manager_url+"shoplist/remove/"+temp_shopitem.getId());
            response.put(idx, item);
        }


        //encode response to json
        Gson gson = new Gson();
        String json_response = gson.toJson(response);

        return json_response;

    }

    @DeleteMapping("/remove/{id}")
    public ShopItem removeShopitem(@PathVariable("id") long id) {
        System.out.print("\n Asking DB_manger to remove Item");
        String uri = "dbManager/shopitem/remove/"+id;

        ShopItem response = this.client
                                .method(HttpMethod.DELETE)
                                .uri(db_manager_url+uri)
                                .retrieve()
                                .bodyToMono(ShopItem.class)
                                .block();
        return  response;
    }


    @PostMapping("/addItem")
    public ShopItem addShopItem(@RequestBody ShopItem item) {
        System.out.print("\n Asking DB_manger to add new item\n");
        String uri = "dbManager/shopitems/additem";

        System.out.print("link" + db_manager_url + uri);
        System.out.print("item: " + item.toString());

        //Create Item
        ShopItem response =  this.client
                .method(HttpMethod.POST)
                .uri(db_manager_url+uri)
                .body(BodyInserters.fromObject(item))
                .retrieve()
                .bodyToMono(ShopItem.class)
                .block();

        return  response;
    }
}
