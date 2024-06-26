package com.tr.minibanking.enums;

public enum Message {
  USER_ALREADY("User already exists"),
  USER_EMAIL_ALREADY("Email already in use"),
  TRANSACTION_SUCCESSFUL("Transaction successful"),
  INCORRECT_USER_PASSWORD("Incorrect username or password"),
  LOGIN_SUCCESSFUL("Login successful"),
  REGISTRATION_FAILED("User registration failed"),
  INTERNAL_ERROR("Internal server error"),
  VALIDATION_FAILED("Validation failed"),
  UNKNOWN_ENUM_VALUE("Unknown enum value"),
  ACCOUNT_ALREADY_USER("Account with this name already exists for the user"),
  INSUFFICIENT_FUNDS("Insufficent funds");

  public static final String USERNAME_MANDATORY = "Username is mandatory";
  public static final String USERNAME_SIZE = "Username must be between 3 and 50 characters";
  public static final String PASSWORD_MANDATORY = "Password is mandatory";
  public static final String PASSWORD_SIZE = "Password must be at between 6 and 64 characters";
  public static final String EMAIL_SIZE = "Username must be between 10 and 255 characters";
  public static final String EMAIL_MANDATORY = "Email is mandatory";
  public static final String EMAIL_INVALID = "Email should be valid";
  public static final String TRANSACTION_FAILED="Transaction failed";
  public static final String AUTH_FAILED="Authentication failed";
  public static final String ACCOUNT_NAME_NOTNULL="Account name is not null";

  private final String message;

  Message(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
