package com.example.testspring.a13;

/**
 * @Author gaolei
 * @Date 2023/2/7 下午11:02
 * @Version 1.0
 */
public class Target implements Foo{

    @Override
    public void foo() {
        System.out.println("Target#Foo foo ....");
    }
}
