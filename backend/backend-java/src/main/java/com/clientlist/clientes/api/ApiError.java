package com.clientlist.clientes.api;

import java.time.Instant;

public class ApiError {

  private Instant timestamp;
  private int status;
  private String error;
  private Object message;
  private String path;

  public ApiError() {}

  public ApiError(int status, String error, Object message, String path) {
    this.timestamp = Instant.now();
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public int getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public Object getMessage() {
    return message;
  }

  public String getPath() {
    return path;
  }
}
