package com.study.spring.theory.king.beans.factory;

import com.study.spring.theory.king.sample.UserService;
import com.study.spring.theory.king.sample.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DefaultBeanFactoryTest {

    DefaultBeanFactory defaultBeanFactory;

    @BeforeEach
    public void init() throws Throwable {
        defaultBeanFactory = new DefaultBeanFactory();
        BeanDefinition bd = new BeanDefinition(UserServiceImpl.class);
        defaultBeanFactory.registerBeanDefinition("userService",bd);

        //所有的beanDefintion都注册完成后，就可以提前实例化单例bean了
        this.defaultBeanFactory.preInstantiateSingletons();
    }

    @Test
    void getBean() throws Throwable {
        UserService userService = (UserService) defaultBeanFactory.getBean("userService");
        userService.fun0();

        UserService userService1 = (UserService) defaultBeanFactory.getBean("userService");
        userService.fun0();
    }
}
