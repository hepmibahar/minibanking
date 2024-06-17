package com.tr.minibanking.enums;

public enum Message {
  USER_ALREADY("User already exists"),
  USER_EMAIL_ALREADY("Email already in use"),
  TRANSACTION_SUCCESSFUL("Transaction successful"),
  INCORRECT_USER_PASSWORD("Incorrect username or password"),
  LOGIN_SUCCESSFUL("Login successful"),
  REGISTRATION_FAILED("User registration failed"),
  INTERNAL_ERROR("Internal server error"),
  UNKNOWN_ENUM_VALUE("Unknown enum value");

  private final String message;
  Message(String message) {
    this.message = message;
  }
  public String getMesaj() {
    return message;
  }

}
