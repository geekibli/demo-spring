package com.example.testspring.a09;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.example.testspring.a09")
public class A01Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(A01Application.class);

        E e = context.getBean(E.class);

        System.out.println(e.getF1());
        System.out.println(e.getF1());
        System.out.println(e.getF1());
        System.out.println(e.getF1().getClass());


        /**
         * com.example.testspring.a09.F1@4d826d77
         * com.example.testspring.a09.F1@4d826d77
         * com.example.testspring.a09.F1@4d826d77
         *
         * 单粒对象的依赖注入，仅仅发生一次 每次打印的都是第一次注入的F1对象
         *
         * 如何打印不同的f1对象呢
         * 注入时添加@Lazy注解
         *
         * com.example.testspring.a09.F1@c267ef4
         * com.example.testspring.a09.F1@31d7b7bf
         * com.example.testspring.a09.F1@5c30a9b0
         *
         * 每次调用代理对象的任何方法时 ，都生成新的对象。 getF1() 每次生成新的代理对象
         *
         *  打印类型可以看到，是代理类类型： class com.example.testspring.a09.F1$$EnhancerBySpringCGLIB$$3b0f5898
         *   使用cglib创建子类代理对象
         *
         */


        System.out.println(e.getF2());
        System.out.println(e.getF2());
        System.out.println(e.getF2());
        System.out.println(e.getF2().getClass());

        /**
         *
         * com.example.testspring.a09.F2@37afeb11
         * com.example.testspring.a09.F2@515aebb0
         * com.example.testspring.a09.F2@dd8ba08
         * class com.example.testspring.a09.F2$$EnhancerBySpringCGLIB$$402487cc
         *
         * F2上面设置 @Scope(value = "prototype" , proxyMode = ScopedProxyMode.TARGET_CLASS)
         */


        System.out.println(e.getF3());
        System.out.println(e.getF3());

        /**
         * com.example.testspring.a09.F3@79e2c065
         * com.example.testspring.a09.F3@35cabb2a
         *
         * 使用工厂的方式
         */

        System.out.println(e.getF4());
        System.out.println(e.getF4());

        /**
         * com.example.testspring.a09.F4@223d2c72
         * com.example.testspring.a09.F4@8f4ea7c
         *
         * context.getBean("")
         */

        context.close();
    }
}
