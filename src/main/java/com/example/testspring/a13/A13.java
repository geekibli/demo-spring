package com.example.testspring.a13;

import java.lang.reflect.Method;

/**
 * @Author gaolei
 * @Date 2023/2/7 下午11:09
 * @Version 1.0
 *
 * https://www.bilibili.com/video/BV1P44y1N7QG/?p=38&spm_id_from=pageDriver&vd_source=3ff1db20d26ee8426355e893ae553d51
 *
 */
public class A13 {

    /**
     * jdk动态代理没有经历源码 .java 到字节码的编译阶段 .class
     * 生成的代理类直接就是 .class  底层是通过asm实现的
     */

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
