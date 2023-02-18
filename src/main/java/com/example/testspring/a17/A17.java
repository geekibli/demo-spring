package com.example.testspring.a17;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * <a href="https://www.bilibili.com/video/BV1P44y1N7QG?p=50&vd_source=3ff1db20d26ee8426355e893ae553d51">...</a>
 */
public class A17 {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("aspect1", Aspect1.class);
        context.registerBean("config", Config.class);

        context.registerBean(ConfigurationClassPostProcessor.class);

        /**
         * beanPostProcessor
         * 创建   (扩展 创建代理) 依赖注入  初始化 （创建代理）
         *
         * AnnotationAwareAspectJAutoProxyCreator 收集所有的高级代理aspect 和 低级代理advisor , 最终 把aspect 转换成 advisor
         *
         *  具体方法：
         *  isEligibleAspectBean
         *  findCandidateAdvisors
         *
         *  wrapIfNecessary() 创建代理对象
         *  org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#wrapIfNecessary(java.lang.Object, java.lang.String, java.lang.Object)
         */
        context.registerBean(AnnotationAwareAspectJAutoProxyCreator.class);

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
    @Order(1)
    static class Aspect1 {

        /**
         * <a href="https://www.bilibili.com/video/BV1P44y1N7QG?p=51&spm_id_from=pageDriver&vd_source=3ff1db20d26ee8426355e893ae553d51">...</a>
         * 最终会转换成一个advisor  低级切面
         */
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
            DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice3);
            advisor.setOrder(2);
            return advisor; // 传入 切点 和通知 参数
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

    /**
     *
     * https://www.bilibili.com/video/BV1P44y1N7QG?p=54
     *
     * @Order 和 @Bean 不起作用
     *
     *          @Bean
     *         public Advisor advisor3(MethodInterceptor advice3){
     *             AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
     *             pointcut.setExpression("execution(* foo())");
     *             DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice3);
     *             advisor.setOrder(2);
     *             return advisor; // 传入 切点 和通知 参数
     *         }
     *
     * @Order 加载高级切面的通知上 不起作用
     *  也就是加载方法上不起作用，必须要加载class上
     *
     *          @Order(2)
     *         @Before("execution(* foo())")
     *         public void before (){
     *             System.out.println("Aspect1 before ...");
     *         }
     *
     *          不起作用
     */

}
