package com.example.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BackupManager {

  private Storage storage;

  @Autowired
  public void setStorage(Storage storage) {
    this.storage = storage;
  }

  public void backup() {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    logger.info("Starting backup...");
    storage.store();
    logger.info("Finished backup");
  }
}
