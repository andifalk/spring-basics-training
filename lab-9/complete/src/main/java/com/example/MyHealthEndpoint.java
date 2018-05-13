package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthEndpoint implements HealthIndicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyHealthEndpoint.class);

    @Override
    public Health health() {
        LOGGER.trace("Custom health endpoint called");
        return Health.up().withDetail("mykey", "value").build();
    }
}
