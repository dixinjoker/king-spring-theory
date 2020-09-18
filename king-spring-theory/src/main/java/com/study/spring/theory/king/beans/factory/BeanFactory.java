package com.study.spring.theory.king.beans.factory;

public interface BeanFactory {
    //提供Bean的工厂
    Object getBean(String beanName)throws Throwable;

    //当要提供提前实例化单例bean的功能时需要
    public void preInstantiateSingletons() throws Throwable;
}
