package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class InitialApplication {

  public static void main(String[] args) {
    SpringApplication.run(InitialApplication.class, args);
  }

  @RestController
  public static class DemoController {

    @GetMapping("/")
    public String hello() {
      return "Hello Spring Boot";
    }

  }
}
