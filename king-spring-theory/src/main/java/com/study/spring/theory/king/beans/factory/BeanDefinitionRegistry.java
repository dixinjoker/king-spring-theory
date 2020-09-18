package com.study.spring.theory.king.beans.factory;

public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName,BeanDefinition beanDefinition);
}
