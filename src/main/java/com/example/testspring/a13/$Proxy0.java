package com.example.testspring.a13;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author gaolei
 * @Date 2023/2/7 下午11:11
 * @Version 1.0
 */

public class $Proxy0 implements A13.Foo {

    private A13.InvocationHandler invocationHandler;

    public $Proxy0(A13.InvocationHandler invocationHandler) {
        this.invocationHandler = invocationHandler;
    }

    // 代理对象和实际对象需要实现共同的接口
    @Override
    public void foo() {

        // 下面2行，增强代码和创建对应对象的代码是灵活多变的，以及调用目标（方法）也是不固定的。
//        System.out.println("$Proxy0#foo before...");
//        new Target().foo();

        // 如何设计动态的代码
        //this.invocationHandler.invoke();


        Method foo = null;
        try {
            foo = A13.Foo.class.getMethod("foo");
            invocationHandler.invoke(foo, new Object[0]);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }


    @Override
    public void bar() {
        Method bar = null;
        try {
            bar = A13.Foo.class.getMethod("bar");
            invocationHandler.invoke(bar, new Object[0]);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
