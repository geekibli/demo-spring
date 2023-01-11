package com.example.testspring.beans;

import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;

public class TestBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config", beanDefinition);

        // 给 beanFactory 添加一些常用的后置处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class)
                .values().stream()
                .forEach(beanFactoryPostProcessor -> {
                    beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
                });

        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName : " + beanDefinitionName);
        }

        beanFactory.getBeansOfType(BeanPostProcessor.class).values()
                .stream().sorted(beanFactory.getDependencyComparator())
                .forEach(beanPostProcessor -> {
                            System.out.println("beanPostProcessor...  " + beanPostProcessor.getClass().getName());
                            beanFactory.addBeanPostProcessor(beanPostProcessor);
                        }
                );

        beanFactory.preInstantiateSingletons();
        System.err.println(beanFactory.getBean(Bean1.class).getBean3());
    }

    @Configuration
    static class Config {
        @Bean(name = "bean1")
        public Bean1 getBean1() {
            return new Bean1();
        }

        @Bean(name = "bean2")
        public Bean2 getBean2() {
            return new Bean2();
        }

        @Bean(name = "bean3")
        public Bean3 getBean3() {
            return new Bean3();
        }

        @Bean(name = "bean4")
        public Bean4 getBean4() {
            return new Bean4();
        }
    }

    @Data
    static class Bean1 {
        public Bean1() {
            System.out.println("init Bean1");
        }

        @Autowired
        @Resource(name = "bean4")
        private Inner bean3;
    }

    @Data
    static class Bean2 {
        public Bean2() {
            System.out.println("init Bean2");
        }
    }

    interface Inner {

    }

    @Data
    static class Bean3 implements Inner {

        public Bean3() {
            System.out.println("init Bean3");
        }
    }

    @Data
    static class Bean4 implements Inner {

        public Bean4() {
            System.out.println("init Bean4");
        }
    }

}
