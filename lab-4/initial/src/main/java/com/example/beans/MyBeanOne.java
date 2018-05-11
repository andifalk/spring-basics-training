package com.example.beans;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MyBeanOne {

    public String operationOne(String msg) {
        if (StringUtils.isBlank(msg)) {
            throw new IllegalArgumentException(this.getClass().getSimpleName() + ": No message");
        }
        return "One: " + msg;
    }

}
