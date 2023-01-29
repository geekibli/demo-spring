package com.example.testspring.a07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class A07Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(A07Application.class);

        applicationContext.close();

    }


    /**
     * 执行顺序
     * 1. @PostConstruct
     *  > 一些aware在InitializingBean之前
     * 2. InitializingBean
     * 3. @Bean init3
     *
     * <p>
     * Bean1 @PostConstruct init1
     * Bean1 afterPropertiesSet ...
     * Bean1 init3 ...
     *
     * @return Bean1
     */
    @Bean(initMethod = "init3")
    public Bean1 bean1(){
        return new Bean1();
    }


    /**
     * 执行顺序
     *
     * @PreDestroy destroy1 ...
     * DisposableBean destroy ...
     * destroy3 ...
     *
     * @return
     */
    @Bean(destroyMethod = "destroy3")
    public Bean2 bean2(){
        return new Bean2();
    }

}
