package com.smartfridge.controller;

import com.google.gson.Gson;
import com.smartfridge.model.Event;
import com.smartfridge.model.Food;
import com.smartfridge.model.ShopItem;
import com.smartfridge.repo.EventRepository;
import com.smartfridge.repo.FoodRepository;
import com.smartfridge.repo.ShopItemRepository;
import com.smartfridge.repo.UserRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RequestMapping("/dbManager")
@RestController
public class DbManager {

    @Autowired
    FoodRepository food_repository;
    @Autowired
    EventRepository event_repository;
    @Autowired
    ShopItemRepository shopitem_repository;
    @Autowired
    UserRepository user_repository;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    private long current_user_id = 0;

    // User
    @GetMapping(value = "/setUserId/{id}")
    public long setId(@PathVariable long id){
        this.current_user_id = id;
        System.out.println("Current user id: "+ this.current_user_id);
        return this.current_user_id;
    }

    //Connect to DB
    /*
    @Bean
    public DataSource dataSource() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        }
    }
    */

    // FOOD
    @PostMapping(value = "/food/addFood")
    public Food addFood(@RequestBody Food food) {
        System.out.println("\nAdding new Food: "+food);
        //Event _event = event_repository.save(new Event( food.getName(), this.current_user_id, food.getUrl_img(), food.getYear(), food.getMonth(), food.getDay()));
        Food _food = food_repository.save(new Food(food.getName(), this.current_user_id, food.getUrl_img(), food.getYear(),food.getMonth(),food.getDay(), food.getCategory(), food.getAssociated_event()));
        return _food;
    }

    @GetMapping(value = "/food/getFoodPerCategory/{category}")
    public List<Food> getFoodPerCategory(@PathVariable String category) {
        System.out.println("\nGet Foods per Category: " + category);
        List<Food> foods;
        if (category.equals("all")) {
            foods = food_repository.findAll();
        } else {
            foods = food_repository.findByCategory(category);
        }
        return foods;
    }

    @GetMapping("/food/getFoodByID/{id}")
    public Food getFoodById(@PathVariable("id") long id) {
        System.out.println("\nGet Food with ID = " + id );
        return food_repository.findById(id);
    }

    @GetMapping("/food/getFoodByDate/{year}/{month}/{day}")
    public List<Food> getFoodByDate(@PathVariable("year") int year,@PathVariable("month") int month,@PathVariable("day") int day) {
        List<Food> expiry_foods = this.food_repository.findAllByYearEqualsAndMonthEqualsAndDayIsLessThanEqual(year,month,day);
        return expiry_foods;
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


    //Event
    @PostMapping(value = "/event/createEvent")
    public Event addFood(@RequestBody Event event) {
        System.out.println("\nAdding new Event: "+ event);
        Event _event = event_repository.save(new Event( event.getName(), this.current_user_id, event.getUrl_img(),event.getYear(),event.getMonth(),event.getDay()));
        return _event;
    }

    @GetMapping(value = "/event/getEvents/{year}/{month}")
    public List<Event> getEvents(@PathVariable int year,@PathVariable int month) {
        System.out.println("\nGet Events in " + year + "-" + month + "*");
        return event_repository.findAllByYearEqualsAndMonthEqualsAndDayGreaterThanEqual(year,month,0);
    }

    @DeleteMapping("/event/remove/{id}")
    public ResponseEntity<String> removeEvent(@PathVariable("id") long id) {
        System.out.println("\nDelete Event with ID = " + id );
        if (event_repository.existsById(id)) {
            event_repository.deleteById(id);
            return new ResponseEntity<>("Event has been deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Event not found!", HttpStatus.OK);
        }
    }


    // ShopList
    @PostMapping("/shopitems/additem")
    public ShopItem addItem(@RequestBody ShopItem item) {
        System.out.println("\nAdding new Item: " + item);
        ShopItem _item = shopitem_repository.save(new ShopItem( item.getName(),  this.current_user_id,  item.getUrl_img(), item.getNotes(), item.getAutomatic_gen(), item.getCategory()));
        return _item;
    }

    @GetMapping("/shopitems/getItems")
    public List<ShopItem> getAllShopitems(){
        System.out.print("\n Getting all shop items!");
        List<ShopItem> items = shopitem_repository.findAll();
        return items;
    }

    @DeleteMapping("/shopitem/remove/{id}")
    public ShopItem removeShopitem(@PathVariable("id") long id) {
        System.out.println("\nDelete ShopItem with ID = " + id );
        if (shopitem_repository.existsById(id)) {
            ShopItem _item = shopitem_repository.getById(id);
            shopitem_repository.deleteById(id);
            return _item;
        } else {
            return new ShopItem();
        }
    }

    public String getUrl(){
        if (System.getenv("HEROKU_APP_NAME") == null){
            return "http://localhost:5000/";
        } else {
            return "https://" + System.getenv("HEROKU_APP_NAME") + ".herokuapp.com/";
        }
    }


    // Get Categories
    @GetMapping(value = "/firstCall")
    public String getCategories(){
        System.out.println("Getting first call!");

        //Create categories
        String[] categories = new String[]{
                "Altro",
                "Carne",
                "Pesce",
                "Pane e Pasta",
                "Colazione",
                "Uova,Latte e Derivati",
                "Banco Frigo",
                "Frutta",
                "Surgelati",
                "Nutella,Miele e Creme",
                "Dolci e Snacks",
                "Cibo in Scatola",
                "Prodotti Vegani",
                "Tisane,Caffé e Infusi",
                "Igene e Cura Personale",
                "Articoli per Tavola e Cucina",
                "Articoli per Bambini",
                "Articoli per Animali",
                "Articoli per la Casa",
                "Ingredienti e Condimenti",
                "Bibite",
                "Salumi e Formaggi",
                "Sughi e Salse",
                "Farmaci",
                "Legumi",
                "Verdura",
                "Spezie e Erbe",
                "Semi e noci"};
        //Create response object
        Dictionary response = new Hashtable();

        //Add data to response
        response.put("Categories", categories);
        response.put("fridge_service", "/fridge");
        response.put("calendar_service", "/calendar");
        response.put("shoplist_service", "/shoplist");

        //encode response to json
        Gson gson = new Gson();
        String json_response = gson.toJson(response);

        return json_response;
    }


    // Extra
    @GetMapping("/food/food_to_shopitem/{id}")
    public ShopItem food_to_shopite(@PathVariable("id") long id){
        Food food = food_repository.findById(id);
        ShopItem _item = shopitem_repository.save(new ShopItem( food.getName(),  this.current_user_id, food.getUrl_img(), "No notes!", true, food.getCategory()));
        return _item;
    }



    // TEST
    @GetMapping(value = "/hello")
    public String hello(){
        return "hello from dbmanager";
    }








}
