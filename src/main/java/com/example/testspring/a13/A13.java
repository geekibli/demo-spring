package com.example.testspring.a13;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author gaolei
 * @Date 2023/2/7 下午11:09
 * @Version 1.0
 */
public class A13 {

    /**
     * 多个方法
     */
    interface Foo {
        void foo();

        void bar();
    }


    static class Target implements Foo {
        @Override
        public void foo() {
            System.out.println("A13#Target#foo ...");
        }

        @Override
        public void bar() {
            System.out.println("A13#Target#bar ...");
        }
    }

    /**
     * 自己模拟一个抽象方法，实现代理功能灵活的增强
     */
    interface InvocationHandler{

        /**
         * 到底调用那个方法
         */
        void invoke(Method method, Object [] args) throws Throwable;
    }

    /**
     * 模拟jdk动态代理
     * @param args
     */
    public static void main(String[] args) {

//        Foo proxy = new $Proxy0(null);
//
//        proxy.foo();
        //$Proxy0#foo before...
        //Target#Foo foo ....


        Foo proxy1 = new $Proxy0(new InvocationHandler() {
            @Override
            public void invoke(Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
                System.out.println("before. ... ");
                method.invoke(new Target(), args);
            }
        });
        proxy1.foo();
        proxy1.bar();

        //$Proxy0#foo before...
        //Target#Foo foo ....

        // 灵活调用哪个方法
        // void invoke(Method method, Object [] args) throws Throwable;
        //before. ...
        //A13#Target#foo ...
        //before. ...
        //A13#Target#bar ...


    }

}
