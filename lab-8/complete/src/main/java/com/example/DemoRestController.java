package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

    private final MyConfigurationProperties properties;

    @Value("${info.application.name}")
    private String appName;

    @Autowired
    public DemoRestController(MyConfigurationProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/")
    public String hello() {
        return properties.getMessage() + " from " + appName;
    }
}
