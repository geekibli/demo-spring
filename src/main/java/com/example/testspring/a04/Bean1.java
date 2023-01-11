package com.example.testspring.a04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

public class Bean1 {

    private Bean2 bean2;

    @Autowired
    public void setBean2(Bean2 bean2){
        System.out.println("@Autowired done");
        this.bean2 = bean2;
    }

    private Bean3 bean3;

    @Resource
    public void setBean3(Bean3 bean3){
        System.out.println("@Resource done");
        this.bean3 = bean3;
    }

    private String home;

    @Autowired
    public void autowire(@Value("${JAVA_HOME}") String home){
        System.out.println("@Value done");
        this.home = home;
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化 done");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁.. done");
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "bean2=" + bean2 +
                ", bean3=" + bean3 +
                ", home='" + home + '\'' +
                '}';
    }
}
