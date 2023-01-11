package com.example.testspring;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.example.testspring")
public class TestSpringApplication {

    public static void main(String[] args)  {
        ConfigurableApplicationContext context = SpringApplication.run(TestSpringApplication.class, args);
        context.close();;
    }

}
