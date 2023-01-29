package com.example.testspring.a06;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

/**
 *
 * <a href="https://www.bilibili.com/video/BV1P44y1N7QG?p=26&vd_source=3ff1db20d26ee8426355e893ae553d51">课程地址</a>
 */
public class A06Application {

    public static void main(String[] args) {

        /**
         * 1. Aware 接口用户注入一些于容器相关的信息
         * 比如
         *  a. BeanNAmeAware 注入 bean 的名字
         *  b. BeanFactoryAware 注入beanFactory容器
         *  c. ApplicationContextAware 注入ApplicationContext 容器
         *  d. EmbeddedValueResoulverAware ${}
         */

        GenericApplicationContext context = new GenericApplicationContext();
//        context.registerBean("mybean?",  MyBean.class);
        context.registerBean("myConfig1", MyConfig1.class);

        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);
        context.registerBean(CommonAnnotationBeanPostProcessor.class);
        context.registerBean(ConfigurationClassPostProcessor.class);

        context.refresh();
        context.close();


        /**
         * Aware接口 和 InitializingBean 是Spring内置的功能
         *
         * 而后置处理器，属于外置的功能
         */

    }
}
