package com.example.testspring.asm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * jdk动态代理 底层asm
 * 课程链接 https://www.bilibili.com/video/BV1P44y1N7QG?p=39&vd_source=3ff1db20d26ee8426355e893ae553d51
 *
 *
 * 动态代理 反射调用方法 性能有影响 jdk如何来优化的？
 *
 * https://www.bilibili.com/video/BV1P44y1N7QG?p=40&spm_id_from=pageDriver&vd_source=3ff1db20d26ee8426355e893ae553d51
 *
 *  GeneratedMethodAccessor2.invoke()
 *
 *  在 方法调用 16 次 调用java native的方法调用， 之后 ，改成方法的正常调用，生成了一个代理类。
 *
 *  jdk 每个方法的调用都需要一个代理对象
 *
 *  cglib 一个类的所有方法 需要 2个 代理对象
 */
public class $Proxy0 extends Proxy implements Foo {



    protected $Proxy0(InvocationHandler h) {
        super(h);
    }

    @Override
    public void foo() {
        try {
            this.h.invoke(this , foo, null);
        }catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    static Method foo;
    static {
        try {
            foo = Foo.class.getMethod("foo");
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }

}
