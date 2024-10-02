package com.ravi.fissionLabs.model;

import java.time.LocalDateTime;

public class ErrorDetails {
  String message;
  LocalDateTime time;
  String description;

  public ErrorDetails(String message, LocalDateTime time, String description) {
    this.message = message;
    this.time = time;
    this.description = description;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
