package com.example.testspring.a18;

import org.aspectj.lang.annotation.*;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.*;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.bilibili.com/video/BV1P44y1N7QG?p=55&spm_id_from=pageDriver&vd_source=3ff1db20d26ee8426355e893ae553d51
 */
public class A18 {

    static class Aspect {
        @Before("execution(* foo())")
        public void before1(){
            System.out.println("Aspect before1 ... ");
        }

        @Before("execution(* foo())")
        public void before2(){
            System.out.println("Aspect before2 ... ");
        }

        @After("execution(* foo())")
        public void after(){
            System.out.println("Aspect after ... ");
        }

        @Around("execution(* foo())")
        public void Around(){
            System.out.println("Aspect Around ... ");
        }

        @AfterReturning("execution(* foo())")
        public void afterReturning(){
            System.out.println("Aspect afterReturing ... ");
        }

        @AfterThrowing("execution(* foo())")
        public void afterThrowing(){
            System.out.println("Aspect afterThrowing ... ");
        }
    }

    static class Target1{
        public void foo(){
            System.out.println("Target1 foo ...");
        }
    }

    public static void main(String[] args) throws NoSuchMethodException {

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

            else if (method.isAnnotationPresent(AfterReturning.class)) {
                String expression = method.getAnnotation(AfterReturning.class).value();
                AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
                pointcut.setExpression(expression);

                AspectJAfterReturningAdvice advice = new AspectJAfterReturningAdvice(method, pointcut, factory);
                DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

                advisorList.add(advisor);
            }

            else if (method.isAnnotationPresent(Around.class)) {
                String expression = method.getAnnotation(Around.class).value();
                AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
                pointcut.setExpression(expression);

                AspectJAroundAdvice advice = new AspectJAroundAdvice(method, pointcut, factory);
                DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

                advisorList.add(advisor);
            }

        }

//        org.springframework.aop.support.DefaultPointcutAdvisor: pointcut [AspectJExpressionPointcut: () execution(* foo())]; advice [org.springframework.aop.aspectj.AspectJMethodBeforeAdvice: advice method [public void com.example.testspring.a17.A17_2$Aspect.before1()]; aspect name '']
//        org.springframework.aop.support.DefaultPointcutAdvisor: pointcut [AspectJExpressionPointcut: () execution(* foo())]; advice [org.springframework.aop.aspectj.AspectJMethodBeforeAdvice: advice method [public void com.example.testspring.a17.A17_2$Aspect.before2()]; aspect name '']

        for (Advisor advisor : advisorList) {
            System.out.println(advisor);
        }


//        AspectJAroundAdvice

//        AspectJAfterAdvice
//        AspectJAfterReturningAdvice
//        AspectJAfterThrowingAdvice
//        AspectJMethodBeforeAdvice


        // 以上4种通知 最终都会被转换为环绕通知    如果实现了 MethodInterceptor 就是环绕通知


        //
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new Target1());
        proxyFactory.addAdvisors(advisorList);

        System.out.println(">>>>>>>>>>>>>>>>");

        // 转换成 环绕通知
        List<Object> InterceptList = proxyFactory.getInterceptorsAndDynamicInterceptionAdvice(Target1.class.getMethod("foo"), Target1.class);
        for (Object o : InterceptList ) {
            System.out.println(o);
        }


//        https://www.bilibili.com/video/BV1P44y1N7QG?p=57
//        org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor@57baeedf
//        org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor@343f4d3d
//        org.springframework.aop.aspectj.AspectJAroundAdvice: advice method [public void com.example.testspring.a18.A18$Aspect.Around()]; aspect name ''
//        org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor@53b32d7

    }
}
