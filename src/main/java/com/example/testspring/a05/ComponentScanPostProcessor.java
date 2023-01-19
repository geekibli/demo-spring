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

                    // 这块注意的是，扫描的路径是 /target/classes 路径下的，二进制字节码文件
                    // 比如： /Users/lei.gao/Documents/demos/test-spring/target/classes/com/example/testspring/a05/Component/Bean3.class
                    Resource[] resources = new PathMatchingResourcePatternResolver().getResources(path);
                    for (Resource resource : resources) {
                        System.out.println("resource " + resource);
                        // 能扫描到路径下所有的类，不管有没有添加@Component注解

                        MetadataReader reader = factory.getMetadataReader(resource);
                        System.out.println("classname : " + reader.getClassMetadata().getClassName());

                        // 如果bean添加的是Component的派生注解，比如controller，下面这种方式还是false
                        boolean isComponent = reader.getAnnotationMetadata().hasAnnotation(Component.class.getName());
                        System.out.println("是否加了Component : " + isComponent);

                        // 如果是@Component的派生注解，比如controller，是可以扫描到的。
                        boolean isMetaComponent = reader.getAnnotationMetadata().hasMetaAnnotation(Component.class.getName());
                        System.out.println("是否加了Component派生 : " + isMetaComponent);

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
