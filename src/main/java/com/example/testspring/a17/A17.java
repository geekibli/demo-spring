package com.example.testspring.a17;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 * <a href="https://www.bilibili.com/video/BV1P44y1N7QG?p=50&vd_source=3ff1db20d26ee8426355e893ae553d51">...</a>
 */
public class A17 {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("aspect1", Aspect1.class);
        context.registerBean("config", Config.class);

        context.registerBean(ConfigurationClassPostProcessor.class);

        context.refresh();

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println("beanmame : " + beanName);
        }

    }

    static class Target1 {
        public void foo(){
            System.out.println("Target1 foo ...");
        }
    }

    static class Target2 {
        public void bar(){
            System.out.println("Target2 bar ...");
        }
    }


    /**
     * 高级切面里面可以有多个通知
     */
    @Aspect
    static class Aspect1 {

        @Before("execution(* foo())")
        public void before (){
            System.out.println("Aspect1 before ...");
        }

        @After("execution(* foo())")
        public void after (){
            System.out.println("Aspect1 after ...");
        }

    }



    @Configuration
    static class Config{

        @Bean
        public Advisor advisor3(MethodInterceptor advice3){
            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            pointcut.setExpression("execution(* foo())");
            return new DefaultPointcutAdvisor(pointcut, advice3); // 传入 切点 和通知 参数
        }

        /**
         * 低级切面  环绕通知
         * @return
         */
        @Bean
        public MethodInterceptor advice3 (){
           return new org.aopalliance.intercept.MethodInterceptor() {
                @Override
                public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                    System.out.println("advice3 before ...");

                    Object proceed = methodInvocation.proceed();

                    System.out.println("advice3 after ...");
                    return proceed;
                }
            };
        }


    }


}
