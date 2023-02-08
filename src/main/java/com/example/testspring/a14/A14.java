package com.example.testspring.a14;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * https://www.bilibili.com/video/BV1P44y1N7QG?p=41
 */
public class A14 {

    public static void main(String[] args) {

        Proxy proxy = new Proxy();

        Target target = new Target();

        /**
         *  MethodProxy methodProxy 每次都是直接调用 就是jdk调用16后的直接调用
         */
        proxy.setMethodInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before ... ");

                // 反射调用
                // return method.invoke(target , objects);

                // return methodProxy.invoke(target, objects); 无反射

                return methodProxy.invokeSuper(o, objects);     // 无反射
            }
        });

        proxy.save();
        proxy.save(1);
        proxy.save(12);

        // 执行结果
        // before ...
        //Target#save ...
        //before ...
        //Target#save ... int: 1
        //before ...
        //Target#save ... int: 12



    }
}
