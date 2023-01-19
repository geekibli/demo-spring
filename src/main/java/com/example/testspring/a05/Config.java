package com.example.testspring.a05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.testspring.a05.Component")
public class Config {

    @Bean
    public Bean1 bean1(){
        return new Bean1();
    }

}
