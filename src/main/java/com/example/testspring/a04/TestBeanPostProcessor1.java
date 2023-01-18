package com.example.testspring.a04;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.Method;

public class TestBeanPostProcessor1 {
    public static void main(String[] args) throws Throwable {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 这里为了省事就不使用 beanFactory.registerBeanDefinition()方法去添加类的描述信息了
        // 直接使用 beanFactory.registerSingleton可以直接将Bean的单例对象注入进去，
        // 后面调用beanFactory.getBean()方法的时候就不会去根据Bean的定义去创建Bean的实例了，
        // 也不会有懒加载和依赖注入的初始化过程了。
        beanFactory.registerSingleton("bean2", new Bean2());
        beanFactory.registerSingleton("bean3", new Bean3());
        // 设置@Autowired注解的解析器
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        beanFactory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);

        // 1. 查找哪些属性、方法加了 @Autowired，这称之为InjectionMetadata
        // 创建后处理器
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        // 后处理器在解析@Autowired和@Value的时候需要用到其他Bean，
        // 而BeanFactory提供了需要的Bean，所以需要把BeanFactory传给这个后处理器
        processor.setBeanFactory(beanFactory);
//        // 创建Bean1
        Bean1 bean1 = new Bean1();
        System.out.println(bean1);
//        // 解析@Autowired和@Value注解，执行依赖注入
//        // PropertyValues pvs: 给注解的属性注入给定的值，这里不需要手动给定，传null即可
        processor.postProcessProperties(null, bean1, "bean1");
        System.out.println(bean1);


        Method findAutowiringMetadata = AutowiredAnnotationBeanPostProcessor.class.getDeclaredMethod("findAutowiringMetadata", String.class, Class.class, PropertyValues.class);
        findAutowiringMetadata.setAccessible(true);
        // 获取Bean1上加了@Value @Autowired注解的成员变量和方法参数信息
        InjectionMetadata metadata = (InjectionMetadata) findAutowiringMetadata.invoke(processor, "bean1", Bean1.class, null);
        System.out.println(metadata);

        // 2. metadata 注入
        metadata.inject(bean1, "bean1", null);
        System.out.println(bean1);


    }
}
