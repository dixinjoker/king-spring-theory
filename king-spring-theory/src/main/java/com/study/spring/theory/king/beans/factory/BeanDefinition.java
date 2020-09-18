package com.study.spring.theory.king.beans.factory;

public class BeanDefinition {

    public static enum Scop{
        singleton,prototype;
    }

    private Class<?> beanClass;

    //默认单例
    private Scop scop = Scop.singleton;

    public BeanDefinition(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Scop getScop() {
        return scop;
    }

    public void setScop(Scop scop) {
        this.scop = scop;
    }
}
