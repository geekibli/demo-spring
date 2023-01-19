package com.example.testspring.a05;

import lombok.SneakyThrows;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

import java.util.Set;

public class A05Application1 {
    @SneakyThrows
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("config", Config.class);

        context.registerBean(AtBeanPostProcessor.class);
        context.registerBean(MapperPostProcessor.class);

        context.refresh();

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println("name : " + name);
        }

        context.close();
    }
}
