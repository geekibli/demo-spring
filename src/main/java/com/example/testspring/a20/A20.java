package com.example.testspring.a20;

import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

public class A20 {

    public static void main(String[] args) {

        /**
         * AnnotationConfig: 支持注解 配置
         * ServletWebServer: 内嵌web容器
         */
        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);






    }
}
