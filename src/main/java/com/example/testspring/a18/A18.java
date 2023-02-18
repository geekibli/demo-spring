package com.example.testspring.a18;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.Advisor;
import org.springframework.aop.AfterAdvice;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.aspectj.*;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.interceptor.ExposeInvocationInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.util.Assert;

import java.io.Serializable;
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



        /**
         *

         无论ProxyFactory 基于那种方式创建代理，最终干活的都是一个 MethodInvocation 对象
         1. 因为 advisor 由多个 ，且一个套一个的调用，因此需要一个调用链对象，也就是 MethodInvocation
         2. MethodInvocation 要知道 advice 有哪些， 还要知道目标，调用次序如下：

         before1 ---------------------------------------------------------------｜
         before2 -------------------------------------------------｜        ｜
         target  ---------------------------- 目标          advice2     advice1
         after2 --------------------------------------------------｜        ｜
         after1 ----------------------------------------------------------------｜

         将 methodInvocation 放入当前线程，threadLocak , 因为有的advise需要用到
         *
         */


//        AspectJAroundAdvice

//        AspectJAfterAdvice
//        AspectJAfterReturningAdvice
//        AspectJAfterThrowingAdvice
//        AspectJMethodBeforeAdvice


        // 以上4种通知 最终都会被转换为环绕通知    如果实现了 MethodInterceptor 就是环绕通知


        //
        Target1 target = new Target1();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(ExposeInvocationInterceptor.INSTANCE);
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


        // 适配器模式
//         AfterReturningAdviceInterceptor

        class AfterReturningAdviceInterceptor implements MethodInterceptor, AfterAdvice, Serializable {
            private final AfterReturningAdvice advice;

            public AfterReturningAdviceInterceptor(AfterReturningAdvice advice) {
                Assert.notNull(advice, "Advice must not be null");
                this.advice = advice;
            }

            public Object invoke(MethodInvocation mi) throws Throwable {
                Object retVal = mi.proceed();
                this.advice.afterReturning(retVal, mi.getMethod(), mi.getArguments(), mi.getThis());
                return retVal;
            }
        }

        // 3. 创建并执行调用链路 （环绕通知，目标）
        // https://www.bilibili.com/video/BV1P44y1N7QG?p=59
        // protected
//        MethodInvocation methodInvocation = new ReflectiveMethodInvocation(
//                null, target, target.getClass().getMethod("foo"), new Object[0] , Target1.class, advisorList
//        );
//        methodInvocation.proceed();

    }
}
