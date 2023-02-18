package com.example.testspring.a17;

/**
 * <a href="https://www.bilibili.com/video/BV1P44y1N7QG?p=53&spm_id_from=pageDriver&vd_source=3ff1db20d26ee8426355e893ae553d51">...</a>
 */
public class A17_1 {

    /**
     * 创建代理的时机
     *
     * 如果bean1 会被增强，bean1会创建代理对象，
     *
     * bean2 依赖bena1 , 那么 bean2 会注入 bean1 的 代理对象
     *
     */


    /**
     *  对象创建  （*）  依赖注入    初始化 （*）
     *
     *  代理创建的时机
     *
     *      初始化之后，无循环依赖
     *      实例创建后，依赖注入前（有循环依赖时）并暂存于二级缓存
     *
     *  依赖注入和初始化不应该被增强，但应该是施加于原始对象
     */
}
