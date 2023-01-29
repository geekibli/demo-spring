package com.example.testspring.a07;

import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PreDestroy;

public class Bean2 implements DisposableBean {

    @PreDestroy
    public void destroy1() {
        System.out.println(" @PreDestroy destroy1 ... ");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean destroy ... ");
    }

    public void destroy3(){
        System.out.println("destroy3 ... ");
    }
}
