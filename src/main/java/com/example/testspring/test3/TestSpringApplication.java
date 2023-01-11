package com.example.testspring.test3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestSpringApplication {
    public static void main(String[] args)  {
        ConfigurableApplicationContext context = SpringApplication.run(TestSpringApplication.class, args);
        context.close();
    }
}
