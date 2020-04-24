package com.bootstrapwithspringboot.webapp.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebController {

    @GetMapping(value="/")
    public ModelAndView homepage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        System.out.print("\n Load Index page!");
        return modelAndView;
    }

    @GetMapping(value="/foodselector")
    public ModelAndView foodselector(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("foodselector");
        System.out.print("\n Load foodselector page!");
        return modelAndView;
    }

}
