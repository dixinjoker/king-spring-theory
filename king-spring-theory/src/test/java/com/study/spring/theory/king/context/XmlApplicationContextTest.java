package com.study.spring.theory.king.context;

import com.study.spring.theory.king.sample.UserService;
import org.junit.jupiter.api.Test;


class XmlApplicationContextTest {

    @Test
    void getBean() throws Throwable {
        XmlApplicationContext context = new XmlApplicationContext("/application.xml");

        UserService userService = (UserService) context.getBean("userService");
        userService.fun0();
    }
}
