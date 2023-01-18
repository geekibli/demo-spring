package com.example.testspring.a04;

import org.springframework.context.support.GenericApplicationContext;

public class A04Application {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);

        context.refresh();

        context.close();
    }
}
