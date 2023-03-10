package com.example.testspring.a05;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;

public class ComponentScanPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        try {
            ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
            if (componentScan != null) {
                String[] strings = componentScan.basePackages();
                for (String name : strings) {
                    System.out.println("name " + name);
                    // com.example.testspring.a05.Component ---> classpath*:com/example/testspring/a05/Component/**/*.class
                    String replace = name.replace(".", "/");

                    String path = "classpath*:" + replace + "/**/*.class";
                    System.out.println("path : " + path);

                    CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory();

                    AnnotationBeanNameGenerator generator = new AnnotationBeanNameGenerator();

                    // ??????????????????????????????????????? /target/classes ???????????????????????????????????????
                    // ????????? /Users/lei.gao/Documents/demos/test-spring/target/classes/com/example/testspring/a05/Component/Bean3.class
                    Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
                    for (Resource resource : resources) {
                        System.out.println("resource " + resource);
                        // ?????????????????????????????????????????????????????????@Component??????

                        MetadataReader reader = factory.getMetadataReader(resource);
                        System.out.println("classname : " + reader.getClassMetadata().getClassName());

                        // ??????bean????????????Component????????????????????????controller???????????????????????????false
                        boolean isComponent = reader.getAnnotationMetadata().hasAnnotation(Component.class.getName());
                        System.out.println("????????????Component : " + isComponent);

                        // ?????????@Component????????????????????????controller???????????????????????????
                        boolean isMetaComponent = reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName());
                        System.out.println("????????????Component?????? : " + isMetaComponent);

                        if (isComponent || isMetaComponent) {

                            AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(reader.getClassMetadata().getClassName()).getBeanDefinition();

                            if (configurableListableBeanFactory instanceof DefaultListableBeanFactory) {
                                BeanDefinitionRegistry beanFactory = (BeanDefinitionRegistry) configurableListableBeanFactory;
                                String beanName = generator.generateBeanName(beanDefinition, beanFactory);
                                beanFactory.registerBeanDefinition(beanName, beanDefinition);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
