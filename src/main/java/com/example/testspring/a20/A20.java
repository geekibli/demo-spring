package com.example.testspring.a20;

import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

public class A20 {

    public static void main(String[] args) throws Exception {

        /**
         * AnnotationConfig: 支持注解 配置
         * ServletWebServer: 内嵌web容器
         */
        AnnotationConfigServletWebServerApplicationContext context = new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

        // 解析 @RequestMapping 以及 派生注解 生成路径和控制器方法的映射关系 在初始化时就生成了
        RequestMappingHandlerMapping handlerMapping = context.getBean(RequestMappingHandlerMapping.class);

        // 获取映射结果
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        handlerMethods.forEach((k , v)-> {
            System.out.println(k + "    " + v);
        });

//        {GET /test1}    com.example.testspring.a20.Controller1#test1()
//        {POST /test2}    com.example.testspring.a20.Controller1#test2(String)
//        {PUT /test3}    com.example.testspring.a20.Controller1#test3(String)

        // 获取具体的控制器方法,
        HandlerExecutionChain chain = handlerMapping.getHandler(new MockHttpServletRequest("GET", "/test1"));
        // chain中还可以打印出一些拦截器
        System.out.println("chain.getHandler() : " + chain.getHandler());
        // chain.getHandler() : com.example.testspring.a20.Controller1#test1()

    }
}
