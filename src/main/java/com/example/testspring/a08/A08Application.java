package com.example.testspring.a08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class A08Application {

    public static void main(String[] args) {
        SpringApplication.run(A08Application.class);
    }

//    @Bean
//    public BeanForSession beanForSession(){
//        return new BeanForSession();
//    }
//
//    @Bean
//    public BeanForApplication beanForApplication (){
//        return new BeanForApplication();
//    }
//
//    @Bean
//    public BeanForRequest beanForRequest(){
//        return new BeanForRequest();
//    }


    /**
     * 查看 session  application  request 的效果
     *
     * 效果图片： https://oscimg.oschina.net/oscnet/up-e0bce87927ab437118e8adb9a383134d039.png
     */

    /**
     *
     */
}
