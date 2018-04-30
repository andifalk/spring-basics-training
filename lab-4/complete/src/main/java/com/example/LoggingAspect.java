package com.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

  @Around("execution(* com.example.beans.*.*(..))")
  public Object tracing(ProceedingJoinPoint joinPoint) throws Throwable {
      Object retVal = null;
      Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
      logger.trace("In: " + joinPoint.getSignature().toShortString());
      retVal = joinPoint.proceed();
      logger.trace("Out: " + joinPoint.getSignature().toShortString());
      return retVal;
  }

  @Before(value = "execution(* com.example.beans.MyBeanOne.operationOne(..)) && args(msg)", argNames = "msg")
  public void logBeanOne(String msg) {
      Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
      logger.trace("Before MyBeanOne.operationOne('" + msg + "')");
  }

}
