package com.example.testspring.a13;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author gaolei
 * @Date 2023/2/7 下午11:03
 * @Version 1.0
 */
public class A13Application {


    public static void main(String[] args) {

        Target target = new Target();

        ClassLoader classLoader = A13Application.class.getClassLoader();

        // 底层使用字节码 动态生成代理类的字节码，看不到对应代理类的java代码
        Foo proxy = (Foo) Proxy.newProxyInstance(classLoader, new Class[]{Foo.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before ....");
                Object result = method.invoke(target, args);
                System.out.println("after ....  ");
                return result;
            }
        });

        proxy.foo();


    }
}
