package com.example.testspring.a19;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * https://www.bilibili.com/video/BV1P44y1N7QG?p=63
 */
public class A19 {

    @Aspect
    static class MyAspect {

        // 静态通知
        @Before("execution(* foo(..))")
        public void before1 (){
            System.out.println("before1 ...");
        }


        // 动态通知 参数绑定 性能有影响
        @Before("execution(* foo(..)) && args(x)")
        public void before2 (int x){
            System.out.println("before2 ...");
            System.out.printf("before2(%d)%n" , x);
        }

    }

    static class Target {
        public void foo(int x) {
            System.out.printf("target foo(%d)%n" , x);
        }
    }

    @Configuration
    static class MyConfig{

        @Bean
        AnnotationAwareAspectJAutoProxyCreator proxyCreator (){
            return new AnnotationAwareAspectJAutoProxyCreator();
        }

        @Bean
        public MyAspect myAspect(){
            return new MyAspect();
        }
    }


    public static void main(String[] args) {

        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(ConfigurationClassPostProcessor.class);
        context.registerBean(MyConfig.class);
        context.refresh();

        AnnotationAwareAspectJAutoProxyCreator creator = context.getBean(AnnotationAwareAspectJAutoProxyCreator.class);


    }
}
