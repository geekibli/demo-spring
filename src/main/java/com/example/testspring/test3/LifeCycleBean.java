package com.example.testspring.test3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class LifeCycleBean {

    public LifeCycleBean(){
        System.out.println("init LifeCycleBean");
    }

    @Autowired
    public void autowire(@Value("java_home") String javaHome){
        System.out.println("javaHome = " + javaHome);
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁");
    }

}
