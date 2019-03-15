package com.example;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

  @Before(
      value = "execution(* com.example.beans.MyBeanOne.operationOne(..)) && args(msg)",
      argNames = "msg")
  public void logBeforeBeanOne(String msg) {
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    logger.trace("Before 'MyBeanOne.operationOne('" + msg + "')'");
  }

  @After(
      value = "execution(* com.example.beans.MyBeanOne.operationOne(..)) && args(msg)",
      argNames = "msg")
  public void logAfterBeanOne(String msg) {
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    logger.trace("After 'MyBeanOne.operationOne('" + msg + "')'");
  }

  @AfterReturning(
      value = "execution(* com.example.beans.MyBeanOne.operationOne(..)) && args(msg)",
      argNames = "msg")
  public void logAfterReturnBeanOne(String msg) {
    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    logger.trace("AfterReturning 'MyBeanOne.operationOne('" + msg + "')'");
  }

  @AfterThrowing(value = "execution(* com.example.beans.*.*(..))")
  public void logErrors() {
    LoggerFactory.getLogger(LoggingAspect.class).trace("After throwing exception");
  }
}
