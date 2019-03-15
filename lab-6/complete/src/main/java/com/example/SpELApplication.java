package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.time.Instant;
import java.time.ZoneId;

public class SpELApplication {

  public static void main(String[] args) {

    Logger logger = LoggerFactory.getLogger(SpELApplication.class);

    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpELConfiguration.class);

    StandardEvaluationContext evalContext = new StandardEvaluationContext();
    evalContext.setBeanResolver(new BeanFactoryResolver(ctx));

    SpelParserConfiguration config =
        new SpelParserConfiguration(
            SpelCompilerMode.IMMEDIATE, SpELApplication.class.getClassLoader());
    SpelExpressionParser parser = new SpelExpressionParser(config);

    Expression expression = parser.parseExpression("T(java.time.Instant).now()");
    logger.info("Result: {}", expression.getValue(Instant.class).atZone(ZoneId.systemDefault()));

    expression = parser.parseExpression("@myCalculatorService.add(10,25)");
    logger.info("Result: {}", expression.getValue(evalContext));

    expression =
        parser.parseExpression(
            "'Java:' + @mySystemInfo.javaVersion "
                + "+ ', User:' + @mySystemInfo.userName "
                + "+ ', Home: ' + @mySystemInfo.userHome");
    logger.info("Result: {}", expression.getValue(evalContext));

    evalContext.setVariable("myLanguage", "Java");
    expression = parser.parseExpression("'My favourite language is ' + #myLanguage");
    logger.info("Result: {}", expression.getValue(evalContext));
  }
}
