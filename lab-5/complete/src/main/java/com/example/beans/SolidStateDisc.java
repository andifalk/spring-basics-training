package com.example.beans;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Qualifier("SSD")
@Profile("fast")
@Component
public class SolidStateDisc implements Storage {

    @Override
    public void store() {
        LoggerFactory.getLogger(this.getClass()).info("Store to SSD");
    }
}
