package com.example.testspring.a06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MyConfig1  {

    @Autowired
    public void setApplicationContext(ApplicationContext context){
        System.out.println("MyConfig1 @Autowired setApplicationContext");
    }

    @PostConstruct
    public void init(){
        System.out.println("MyConfig1 @PostConstruct init");
    }

    @Bean
    // 添加 beanFactory 后置处理器
    public BeanFactoryPostProcessor processor1(){

        // 导致配置类@ConfigurationMyConfig1 的 @Autowired  @PostConstruct 失效

        // context.refresh(); 工作   https://oscimg.oschina.net/oscnet/up-2c51595004603287893359b9084540a6b2d.png

        // 1.添加beanFacrory后置处理器
        // 2. 添加bean后置处理器
        // 3. 进行bean初始化

        return configurableListableBeanFactory -> {
            System.out.println("MyConfig1 processor1 ...");
        };
    }


    /**
     *
     * https://www.bilibili.com/video/BV1P44y1N7QG?p=27&spm_id_from=pageDriver&vd_source=3ff1db20d26ee8426355e893ae553d51
     *
     *   ** processor1 方法注入的时候一个BeanFactoryPostProcessor **
     *
     * 1. processor1方法被调用，前提是 MyConfig1 对象已经创建好了
     * 2.
     *
     *
     */
}
