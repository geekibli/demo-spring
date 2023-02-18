package com.example.testspring.a17;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJMethodBeforeAdvice;
import org.springframework.aop.aspectj.SingletonAspectInstanceFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.bilibili.com/video/BV1P44y1N7QG?p=55&spm_id_from=pageDriver&vd_source=3ff1db20d26ee8426355e893ae553d51
 */
public class A17_2 {

    static class Aspect {
        @Before("execution(* foo())")
        public void before1(){
            System.out.println("Aspect before1 ... ");
        }

        @Before("execution(* foo())")
        public void before2(){
            System.out.println("Aspect before2 ... ");
        }

        public void after(){
            System.out.println("Aspect after ... ");
        }

        public void afterReturning(){
            System.out.println("Aspect afterReturing ... ");
        }

        public void afterThrowing(){
            System.out.println("Aspect afterThrowing ... ");
        }
    }

    static class Target1{
        public void foo(){
            System.out.println("Target1 foo ...");
        }
    }

    public static void main(String[] args) {

        AspectInstanceFactory factory = new SingletonAspectInstanceFactory(new Aspect());

        List<Advisor> advisorList = new ArrayList<>();
        Method[] declaredMethods = Aspect.class.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Before.class)) {
                String expression = method.getAnnotation(Before.class).value();
                AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
                pointcut.setExpression(expression);

                AspectJMethodBeforeAdvice advice = new AspectJMethodBeforeAdvice(method, pointcut, factory);
                DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

                advisorList.add(advisor);
            }

            if (method.isAnnotationPresent(After.class)) {
                // ...
            }

        }

//        org.springframework.aop.support.DefaultPointcutAdvisor: pointcut [AspectJExpressionPointcut: () execution(* foo())]; advice [org.springframework.aop.aspectj.AspectJMethodBeforeAdvice: advice method [public void com.example.testspring.a17.A17_2$Aspect.before1()]; aspect name '']
//        org.springframework.aop.support.DefaultPointcutAdvisor: pointcut [AspectJExpressionPointcut: () execution(* foo())]; advice [org.springframework.aop.aspectj.AspectJMethodBeforeAdvice: advice method [public void com.example.testspring.a17.A17_2$Aspect.before2()]; aspect name '']

        for (Advisor advisor : advisorList) {
            System.out.println(advisor);
        }
    }
}
