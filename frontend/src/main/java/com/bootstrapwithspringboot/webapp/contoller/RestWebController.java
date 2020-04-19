package com.bootstrapwithspringboot.webapp.contoller;

import java.util.ArrayList;
import java.util.List;

import com.bootstrapwithspringboot.webapp.message.Response;
import com.bootstrapwithspringboot.webapp.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestWebController {

    List<Customer> cust = new ArrayList<Customer>();

    @GetMapping(value = "/all")
    public Response getResource() {
        Response response = new Response("Done", cust);
        return response;
    }

    @PostMapping(value = "/save")
    public Response postCustomer(@RequestBody Customer customer) {
        cust.add(customer);

        // Create Response Object
        Response response = new Response("Done", customer);
        return response;
    }
}
