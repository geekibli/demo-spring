package com.example.testspring.a01;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Field;
import java.util.Map;

@SpringBootApplication
public class A01Application {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        ConfigurableApplicationContext context = SpringApplication.run(A01Application.class, args);

        Field singletonObjects = DefaultSingletonBeanRegistry.class.getDeclaredField("singletonObjects");
        singletonObjects.setAccessible(true);

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Map<String, Object> map = (Map<String, Object>) singletonObjects.get(beanFactory);

        for (String key : map.keySet()) {
            if (!key.equals("component1")) {
                continue;
            }
            System.err.println(key + "   " + map.get(key));
        }



    }
}
