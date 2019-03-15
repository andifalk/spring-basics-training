package com.example.beans;

import org.springframework.beans.factory.annotation.Value;

public class MySystemInfo {

  @Value("#{ systemProperties['user.name'] }")
  private String userName;

  @Value("#{ systemProperties['user.home'] }")
  private String userHome;

  private String javaVersion;

  public String getUserName() {
    return userName;
  }

  public String getUserHome() {
    return userHome;
  }

  public void setJavaVersion(String javaVersion) {
    this.javaVersion = javaVersion;
  }

  public String getJavaVersion() {
    return javaVersion;
  }
}
