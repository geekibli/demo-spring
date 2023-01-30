package com.example.testspring.a08;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class MyControllor {

    @Lazy
    @Autowired
    BeanForRequest beanForRequest;

    @Lazy
    @Autowired
    BeanForApplication beanForApplication;

    @Lazy
    @Autowired
    BeanForSession beanForSession;


    @GetMapping(value = "/test" , produces = "text/html")
    public String test(HttpServletRequest request , HttpSession session){

        ServletContext servletContext = request.getServletContext();

        String result = "<ul>" +
                "<li>" + "request score : " + beanForRequest + "</li> " +
                "<li>" + "application score : " + beanForApplication + "</li> " +
                "<li>" + "session score : " + beanForSession + "</li> " +
                "</ul>";

        return result;
    }

}
