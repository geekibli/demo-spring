package com.example.testspring.a15;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * https://www.bilibili.com/video/BV1P44y1N7QG?p=46&spm_id_from=pageDriver&vd_source=3ff1db20d26ee8426355e893ae553d51
 */
public class A15_1 {

    public static void main(String[] args) {

        // 备好切点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");


        // 备好通知
        MethodInterceptor advice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                // 前置增强
                System.out.println("before ...");

                // 调用目标
                methodInvocation.proceed();

                // 后置增强
                System.out.println("after ...");
                return null;
            }
        };


        // 备好切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        // 创建代理
        Target1 target = new Target1();
        ProxyFactory factory = new ProxyFactory();
        factory.setTarget(target);
        factory.addAdvisor(advisor);

        // jdk com.example.testspring.a15.$Proxy0
        factory.setInterfaces(target.getClass().getInterfaces());

        // com.example.testspring.a15.A15_1$Target1$$EnhancerBySpringCGLIB$$b5d948ad
        factory.setProxyTargetClass(true);

        T1 proxy = (T1) factory.getProxy();
        System.out.println(proxy.getClass().getName());
        proxy.foo();
        proxy.bar();
    }


    interface T1 {
        void foo();

        void bar();
    }

    static class Target1 implements T1 {

        @Override
        public void foo() {
            System.out.println("Target1#foo ....");
        }

        @Override
        public void bar() {
            System.out.println("Target1#bar ....");
        }
    }
}
