package com.tr.minibanking.model;

import org.springframework.http.HttpStatus;

import com.tr.minibanking.enums.Message;

public class ApiResponse<T> {
  private HttpStatus status;
  private String message;
  private T data;

  public ApiResponse(HttpStatus status, Message message, T data) {
    this.status = status;
    this.message = message.getMessage();
    this.data = data;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
