package com.example.testspring.a06;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;

public class MyBean implements BeanNameAware , ApplicationContextAware , InitializingBean {

    // 初始化之前调用
    @Override
    public void setBeanName(String s) {
        System.out.println("bean name : {} " + s);
        System.out.println("bean  {} " + this.getClass().getName());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applicationContext name : {} " + applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行InitializingBean。。。。。");
    }

    @Autowired
    public void aaa(ApplicationContext applicationContext) {
        System.out.println(" @Autowired ApplicationContext : " + applicationContext);
    }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct#init 。。。。。");
    }
}
