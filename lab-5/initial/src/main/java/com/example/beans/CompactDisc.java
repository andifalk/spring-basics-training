package com.example.beans;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Qualifier("CD")
@Component
public class CompactDisc implements Storage {

  @Override
  public void store() {
    LoggerFactory.getLogger(this.getClass()).info("Store to CD");
  }
}
