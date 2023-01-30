package com.example.testspring.a08;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Scope("application")
public class BeanForApplication {


    /**
     * 按说是在Tomcat停止是 销毁 打印这个方法
     */
    @PreDestroy
    public void destroy(){
        System.out.println("BeanForApplication destroy ... ");
    }

}
