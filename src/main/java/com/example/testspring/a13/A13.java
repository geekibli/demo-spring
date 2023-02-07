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

        int bar();
    }


    static class Target implements Foo {
        @Override
        public void foo() {
            System.out.println("A13#Target#foo ...");
        }

        @Override
        public int bar() {
            System.out.println("A13#Target#bar ...");
            return 100;
        }
    }

    /**
     * 自己模拟一个抽象方法，实现代理功能灵活的增强
     */
    interface InvocationHandler{

        /**
         * 到底调用那个方法
         */
        Object invoke(Object proxy , Method method, Object [] args) throws Throwable;
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
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before. ... ");
                Object invoke = method.invoke(new Target(), args);
                return invoke;
            }
        }
        );
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


        // Object invoke(Object proxy , Method method, Object [] args) throws Throwable;
        // 支持返回值


        // 异常的处理


        // Method 静态，不用每次都重新获取

    }

}
