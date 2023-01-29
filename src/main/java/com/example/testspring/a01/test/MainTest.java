package com.example.testspring.a01.test;

import java.lang.reflect.Field;

public class MainTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field name = Parent.class.getDeclaredField("name");
        name.setAccessible(true);
        Son son = new Son();
        son.setName("hahah");
        Object o = name.get(son);
        System.out.println(o);
    }
}
