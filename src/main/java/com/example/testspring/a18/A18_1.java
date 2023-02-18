package com.example.testspring.a18;

import lombok.Data;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * https://www.bilibili.com/video/BV1P44y1N7QG?p=60
 */
public class A18_1 {

    static class Target {
        public void foo(){
            System.out.println("Target foo ...");
        }
    }

    static class Advice1 implements MethodInterceptor{

        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {

            System.out.println("Advice1 before ...");
            Object result = methodInvocation.proceed();
            System.out.println("Advice1 after ...");

            return result;
        }
    }

    static class Advice2 implements MethodInterceptor{

        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {

            System.out.println("Advice2 before ...");
            Object result = methodInvocation.proceed();
            System.out.println("Advice2 after ...");

            return result;
        }
    }

    // 调用多个通知和目标
    @Data
    static class MyInvocation implements MethodInvocation {

        private Object target;
        private Method method;
        private Object[] args;
        private List<MethodInterceptor> methodInterceptorList;
        private int count = 1 ; // 调用次数

        @Override
        public Method getMethod() {
            return this.method;
        }

        @Override
        public Object[] getArguments() {
            return this.args;
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

         将 methodInvocation 放入当前线程，threadLocal , 因为有的advise需要用到
         *
         */

        /**
         * 调用每一个环绕通知 调用目标
         * @return
         * @throws Throwable
         */
        @Override
        public Object proceed() throws Throwable {

            if (count > methodInterceptorList.size()) {
                // 调用目标 返回并结束递归操作
                return method.invoke(target, args);
            }

            // 逐步调用通知，并且 count + 1
            // https://www.bilibili.com/video/BV1P44y1N7QG?p=61

            MethodInterceptor methodInterceptor = methodInterceptorList.get(count - 1);
            count++;
            return methodInterceptor.invoke(this);

        }

        @Override
        public Object getThis() {
            return this.target;
        }

        @Override
        public AccessibleObject getStaticPart() {
            return method;
        }
    }

    /**
     * 责任链模式 递归调用
     * @param args
     * @throws Throwable
     */
    public static void main(String[] args) throws Throwable {
        Target target = new Target();
        List<MethodInterceptor> list = new ArrayList<>();
        list.add(new Advice1());
        list.add(new Advice2());

        MyInvocation myInvocation = new MyInvocation();
        myInvocation.setTarget(target);
        myInvocation.setMethod(Target.class.getMethod("foo"));
        myInvocation.setArgs(new Object[0]);
        myInvocation.setMethodInterceptorList(list);

        myInvocation.proceed();


//        Advice1 before ...
//        Advice2 before ...
//        Target foo ...
//        Advice2 after ...
//        Advice1 after ...
    }


}
