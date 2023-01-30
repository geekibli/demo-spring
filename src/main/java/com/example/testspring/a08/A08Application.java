package com.example.testspring.a08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class A08Application {

    public static void main(String[] args) {
        SpringApplication.run(A08Application.class);
    }

    @Bean
    public BeanForSession beanForSession(){
        return new BeanForSession();
    }

    @Bean
    public BeanForApplication beanForApplication (){
        return new BeanForApplication();
    }

    @Bean
    public BeanForRequest beanForRequest(){
        return new BeanForRequest();
    }
}
