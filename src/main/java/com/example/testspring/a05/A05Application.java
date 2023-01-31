package com.example.testspring.a05;

import lombok.SneakyThrows;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionDefaults;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

import java.util.Set;

public class A05Application {
    @SneakyThrows
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);
        // 先读取class配置类的信息
        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();
        MetadataReader reader = factory.getMetadataReader(new ClassPathResource("com/example/testspring/a05/Config.class"));

        // 获取标注@Bean的 【方法】的信息
        Set<MethodMetadata> annotatedMethods = reader.getAnnotationMetadata().getAnnotatedMethods(Bean.class.getName());

        for (MethodMetadata metadata : annotatedMethods) {
            System.out.println(metadata.getMethodName());

            // 设置工厂方法
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition();
            builder.setFactoryMethodOnBean(metadata.getMethodName(), "config");
            builder.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);

            String initMethod = metadata.getAllAnnotationAttributes(Bean.class.getName()).getFirst("initMethod").toString();
            if (initMethod != null && initMethod.length() > 0) {
                builder.setInitMethodName(initMethod);
            }

            // 拿到bean定义
            AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
            context.getDefaultListableBeanFactory().registerBeanDefinition(metadata.getMethodName(), beanDefinition);
        }

        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println("name : " + name);
        }

        context.close();
    }
}
