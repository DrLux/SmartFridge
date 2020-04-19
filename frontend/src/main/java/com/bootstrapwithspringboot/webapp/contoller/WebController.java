package com.bootstrapwithspringboot.webapp.contoller;

import org.springframework.web.bind.annotation.GetMapping;

public class WebController {
    @GetMapping(value="/")
    public String homepage(){
        return "index";
    }
}
