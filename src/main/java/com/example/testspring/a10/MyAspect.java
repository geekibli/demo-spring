package com.example.testspring.a10;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {

    // TODO jar

    @Before("execution(* com.example.testspring.a10.MyService.foo())")
    public void before(){
        System.out.println("before() ... ");
    }

}
