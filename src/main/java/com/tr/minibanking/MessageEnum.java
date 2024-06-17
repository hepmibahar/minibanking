package com.tr.minibanking;

public enum MessageEnum {
  USER_ALREADY("User is already registered!"),
  USER_EMAIL_ALREADY("User email is already registered!"),
  TRANSACTION_SUCCESSFUL("Transaction successful."),
  INCORRECT_USER_PASSWORD("Incorrect username or password.");

  private final String message;
  MessageEnum(String message) {
    this.message = message;
  }
  public String getMesaj() {
    return message;
  }

}
