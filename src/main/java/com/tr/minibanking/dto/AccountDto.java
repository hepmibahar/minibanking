package com.tr.minibanking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import com.tr.minibanking.enums.Message;

@Data
public class AccountDto {

  private UUID id;

  private UUID userId;

  private String accountNumber;

  @NotNull(message = Message.ACCOUNT_NAME_NOTNULL)
  private String accountName;

  private BigDecimal balance;
}

