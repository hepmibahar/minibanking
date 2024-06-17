package com.tr.minibanking;

public enum MessageEnum {
  USER_ALREADY("User is already registered!"),
  TRANSACTION_SUCCESSFUL("Transaction successful.");

  private final String message;
  MessageEnum(String message) {
    this.message = message;
  }
  public String getMesaj() {
    return message;
  }

}
