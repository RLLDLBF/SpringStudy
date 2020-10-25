package com.atguigu.spring5.test;

import com.atguigu.spring5.aopanno.User;
import com.atguigu.spring5.aopxml.Book;
import com.atguigu.spring5.config.ConfigAop;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

    @Test
    public void testAopAnno() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean1.xml");
        User user = context.getBean("user", User.class);
        user.add();
    }

    @Test
    public void testAopXml() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean2.xml");
        Book book = context.getBean("book", Book.class);
        book.buy();
    }

    @Test
    public void testAopAnno2(){
        //加载配置类
        ApplicationContext context
                = new AnnotationConfigApplicationContext(ConfigAop.class);
        User user = context.getBean("user", User.class);
        user.add();
    }

    @Test
    public void testAopXml2(){
        //org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'book' available
        ApplicationContext context
                = new AnnotationConfigApplicationContext(ConfigAop.class);
        Book book = context.getBean("book", Book.class);
        book.buy();
    }
}
