package com.example.testspring.a16;

import lombok.experimental.Accessors;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.MergedAnnotations;

import java.beans.Transient;
import java.lang.reflect.Method;

/**
 * https://www.bilibili.com/video/BV1P44y1N7QG?p=48&spm_id_from=pageDriver&vd_source=3ff1db20d26ee8426355e893ae553d51
 */
public class A16 {

    public static void main(String[] args) throws NoSuchMethodException {

        // 只能匹配方法
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution( * bar())");
        System.out.println(pointcut.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pointcut.matches(T1.class.getMethod("bar"), T1.class));

        // 只能匹配方法的注解
        AspectJExpressionPointcut pointcut2 = new AspectJExpressionPointcut();
        pointcut2.setExpression("@annotation(java.beans.Transient)");
        System.out.println(pointcut2.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pointcut2.matches(T1.class.getMethod("bar"), T1.class));


        // @Transaction 具备事务能力
        // AspectJExpressionPointcut +  pointcut2.setExpression("@annotation(java.beans.Transient)"); 方式实现的

        // 底层实现 https://www.bilibili.com/video/BV1P44y1N7QG?p=49&spm_id_from=pageDriver&vd_source=3ff1db20d26ee8426355e893ae553d51

        /**
         *
         * 1. 加载类上 "所有"的方法 实现事务增强
         * 2.
         *
         */
        StaticMethodMatcherPointcut pointcut1 = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> aClass) {

                MergedAnnotations annos = MergedAnnotations.from(method);
                if (annos.isPresent(Transient.class)) {
                    return true;
                }

                // 默认搜索策略 只查找本类，如果递归找呢？ 添加 MergedAnnotations.SearchStrategy.TYPE_HIERARCHY
                MergedAnnotations cAnnos = MergedAnnotations.from(aClass);
                if (cAnnos.isPresent(Transient.class)) {
                    return true;
                }

                return false;
            }
        };

        System.out.println("\n");
        System.out.println(pointcut1.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pointcut1.matches(T1.class.getMethod("bar"), T1.class));
        System.out.println(pointcut1.matches(T2.class.getMethod("foo"), T2.class));

    }

    static class T1 {

        @Transient // TODo
        public void foo() {
            System.out.println("t1 foo");
        }

        public void bar() {
            System.out.println("t1 bar");
        }
    }

    @Accessors
    static class T2 {
        public void foo() {
            System.out.println("t2 foo");
        }
    }
}
