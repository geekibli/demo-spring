package com.example.testspring.test3;

import java.util.ArrayList;
import java.util.List;

public class TestMethodTemplatePattern {
    public static void main(String[] args) {
        MyBeanFactory beanFactory = new MyBeanFactory();
        beanFactory.addBeanPostProcessor(bean -> System.out.println("解析 @Autowired"));
        beanFactory.addBeanPostProcessor(bean -> System.out.println("解析 @Resource"));
        beanFactory.getBean();
    }

    static class MyBeanFactory {
        public Object getBean() {
            Object bean = new Object();
            System.out.println("构造：" + bean);
            System.out.println("依赖注入：" + bean);
            for (BeanPostProcessor processor : processors) {
                processor.inject(bean);
            }
            System.out.println("初始化：" + bean);
            return bean;
        }

        private List<BeanPostProcessor> processors = new ArrayList<>();

        public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
            processors.add(beanPostProcessor);
        }
    }

    interface BeanPostProcessor {
        void inject(Object bean);
    }
}
