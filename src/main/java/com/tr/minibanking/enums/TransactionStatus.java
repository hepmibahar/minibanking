package com.tr.minibanking.enums;

import java.util.HashMap;
import java.util.Map;

public enum TransactionStatus {
  SUCCESS(1),
  FAILED(2),
  PENDING(3);

  private final int value;
  private static final Map<Integer, TransactionStatus> valueToStatusMap = new HashMap<>();

  static {
    for (TransactionStatus status : TransactionStatus.values()) {
      valueToStatusMap.put(status.value, status);
    }
  }

  TransactionStatus(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static TransactionStatus fromValue(int value) {
    TransactionStatus status = valueToStatusMap.get(value);
    if (status == null) {
      throw new IllegalArgumentException(Message.UNKNOWN_ENUM_VALUE.getMessage());
    }
    return status;
  }
}
