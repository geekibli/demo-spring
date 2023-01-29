package com.example.testspring.a07;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class Bean1 implements InitializingBean {

    @PostConstruct
    public void init1(){
        System.out.println("Bean1 @PostConstruct init1 ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean1 afterPropertiesSet ... ");
    }

    public void init3(){
        System.out.println("Bean1 init3 ... ");
    }

}
