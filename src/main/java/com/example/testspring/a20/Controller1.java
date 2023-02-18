package com.example.testspring.a20;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controller1 {

    @GetMapping("/test1")
    public ModelAndView test1(){
        System.err.println("test1....");
        return null;
    }

    @PostMapping("/test2")
    public ModelAndView test2(@RequestParam("name")String name){
        System.err.println("test2.... " + name);
        return null;
    }

    @PutMapping("/test3")
    public ModelAndView test3(@Token String token){
        System.out.println("test3 " + token);
        return null;
    }

}
