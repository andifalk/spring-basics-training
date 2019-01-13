package com.example.beans;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Qualifier("HD")
@Component
public class Harddisk implements Storage {

    @Override
    public void store() {
        LoggerFactory.getLogger(this.getClass()).info("Store to HD");
    }
}
