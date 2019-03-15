package com.example;

import com.example.beans.MyLifecycleBean;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringLifecycleApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx =
        new AnnotationConfigApplicationContext(SpringLifecycleConfiguration.class);
    ctx.registerShutdownHook();
    MyLifecycleBean lifecycleBean = ctx.getBean(MyLifecycleBean.class);
    LoggerFactory.getLogger(SpringLifecycleApplication.class).info(lifecycleBean.sayHello());
  }
}
