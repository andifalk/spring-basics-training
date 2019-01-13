package com.example.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
public class MyLifecycleBean implements SmartLifecycle {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyLifecycleBean.class);

    private boolean running;

    public MyLifecycleBean() {
        LOGGER.info("Constructor called");
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        LOGGER.info("SmartLifecycle#stop(Runnable) called: {}", this);
        callback.run();
        running = false;
    }

    @Override
    public void start() {
        running = true;
        LOGGER.info("SmartLifecycle#start() called: {}", this);
    }

    @Override
    public void stop() {
        LOGGER.info("SmartLifecycle#stop() called: {}", this);
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return 1;
    }

    public String sayHello() {
        LOGGER.info("sayHello() called: {}", this);
        return "Hello from Lifecycle";
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("running", running)
                .toString();
    }
}
