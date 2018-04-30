package com.example.beans;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MyBeanTwo {

    public String operationTwo(String msg) {
        if (StringUtils.isBlank(msg)) {
            throw new IllegalArgumentException("No message");
        }

        return "Two: " + msg;
    }

}
