package com.example.testspring.a05;

import lombok.SneakyThrows;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;

public class MapperPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @SneakyThrows
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanFactory) throws BeansException {
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("com/example/testspring/a05/mapper/**/*.class");
        CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();

        AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();

        for (Resource resource :  resources) {
            MetadataReader reader = factory.getMetadataReader(resource);
            ClassMetadata classMetadata = reader.getClassMetadata();
            if (classMetadata.isInterface()) {

                // 定义beanDefinition

                AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(MapperFactoryBean.class)
                        .addConstructorArgValue(classMetadata.getClassName())
                        .setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE)
                        .getBeanDefinition();

                AbstractBeanDefinition nameBeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(classMetadata.getClassName()).getBeanDefinition();

                String beanName = generator.generateBeanName(nameBeanDefinition, beanFactory);

                // beanName:  mapper1   className:  com.example.testspring.a05.mapper.Mapper1
                System.out.println("beanName:  " + beanName +  "   className:  " + classMetadata.getClassName());

                beanFactory.registerBeanDefinition(beanName, beanDefinition);
            }
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
