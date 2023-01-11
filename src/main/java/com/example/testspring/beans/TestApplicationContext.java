package com.example.testspring.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.Controller;

import javax.management.MXBean;
import java.sql.SQLOutput;

public class TestApplicationContext {
    public static void main(String[] args) {
        testAnnotationConfigServletWebServerApplicationContext();
    }

    private static void testXmlPath(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("b01.xml");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println("name : " + name);
        }

        System.out.println(applicationContext.getBean("bean1"));
    }

    private static void testAnnotationConfiqApplicationContext(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println("name : " + name);
        }
    }


    private static void testAnnotationConfigServletWebServerApplicationContext(){
        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println("name : " + name);
        }
    }

    @Configuration
    static class WebConfig{
        @Bean
        public ServletWebServerFactory servletWebServerFactory (){
            return new TomcatServletWebServerFactory();
        }
        @Bean
        public DispatcherServlet dispatchServlet (){
            return new DispatcherServlet();
        }
        @Bean
        public DispatcherServletRegistrationBean dispatcherServletRegistrationBean(DispatcherServlet dispatcherServlet){
            // path 是限制哪些请求经过dispatchServlet来进行分发
            return new DispatcherServletRegistrationBean(dispatcherServlet, "/");
        }
        @Bean("/hello")
        public Controller myController(){
            return ((httpServletRequest, httpServletResponse) -> {
                httpServletResponse.getWriter().println("hello....");
                return null;
            });
        }
    }

    @Configuration
    static class Config {

        @Bean("bean1")
        public Bean1 getBean1(){
            return new Bean1();
        }

        @Bean("bean2")
        public Bean2 getBean2(Bean1 bean1){
            Bean2 bean2 = new Bean2();
            bean2.setBean1(bean1);
            return bean2;
        }

    }

    @Data
    @NoArgsConstructor
    static class Bean1{

    }

    @Data
    @NoArgsConstructor
    static class Bean2 {
        private Bean1 bean1;
    }
}
