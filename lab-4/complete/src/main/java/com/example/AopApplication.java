package com.example;

import com.example.beans.MyBeanOne;
import com.example.beans.MyBeanTwo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AopApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AopConfiguration.class);
        MyBeanOne beanOne = ctx.getBean(MyBeanOne.class);

        beanOne.operationOne("first");

        MyBeanTwo beanTwo = ctx.getBean(MyBeanTwo.class);

        beanTwo.operationTwo("second");

        beanOne.operationOne(null);

    }

}
