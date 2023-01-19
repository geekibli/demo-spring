package com.example.testspring.a04;

import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * https://www.bilibili.com/video/BV1P44y1N7QG?p=19&spm_id_from=pageDriver&vd_source=3ff1db20d26ee8426355e893ae553d51
 */
public class BigInAutowired {

    public static void main(String[] args) throws Throwable {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

//        beanFactory.registerSingleton("bean2", new Bean2());
        beanFactory.registerSingleton("bean3", new Bean3());
        // 解析@Value
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // 解析 ${}
        beanFactory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);

        // 查找那些属性或者方法加了 @Autowired
        AutowiredAnnotationBeanPostProcessor postProcessor = new AutowiredAnnotationBeanPostProcessor();
        // 执行依赖注入的时候，可能需要去beanFactory中找一些bean, 比如bean1 依赖 bean2，在初始化bean1的时候去beanFactory中找bean2的实例，赋值给bean1的属性
        postProcessor.setBeanFactory(beanFactory);

        Bean1 bean1 = new Bean1();
        System.out.println("bean1 : " + bean1);

        // 下面两步模拟后置处理的过程：

        Method findAutowiringMetadata = AutowiredAnnotationBeanPostProcessor.class.getDeclaredMethod("findAutowiringMetadata", String.class, Class.class, PropertyValues.class);
        findAutowiringMetadata.setAccessible(true);
        // 获取bean1上加了@Value @Autowired 注解的属性和方法 等信息
        InjectionMetadata injectionMetadata = (InjectionMetadata) findAutowiringMetadata.invoke(postProcessor, "bean1", Bean1.class, null);

        // 拿到元数据之后，执行依赖注入，注入时按照类型查找
        injectionMetadata.inject(bean1, "bean1", null);
        System.out.println("bean1 : " + bean1);

        // 按照类型查找属性值
        // 属性上面添加@Autowired
        Field bean3 = Bean1.class.getDeclaredField("bean3");
        DependencyDescriptor dd1 = new DependencyDescriptor(bean3, false);
        Object o = beanFactory.doResolveDependency(dd1, null, null, null);
        System.out.println(o);

        // 方法上面添加@Autowired
        Method setBean2 = Bean1.class.getDeclaredMethod("setBean2", Bean2.class);
        DependencyDescriptor dd2 = new DependencyDescriptor(new MethodParameter(setBean2, 0), true);
        Object o1 = beanFactory.doResolveDependency(dd2, null, null, null);
        System.out.println(o1);
    }
}
