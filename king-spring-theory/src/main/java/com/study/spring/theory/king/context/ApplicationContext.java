package com.study.spring.theory.king.context;

import com.study.spring.theory.king.beans.factory.BeanFactory;

public interface ApplicationContext extends BeanFactory {
    //为什么要增加Applicationcontext 而不是扩充BeanFactory：为了单一职责，BeanFactory内聚简洁
    //以便后续灵活扩展功能
    //实现基于XML配置的XmlApplicationContext
}
