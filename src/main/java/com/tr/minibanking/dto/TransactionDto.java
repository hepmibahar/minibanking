package com.tr.minibanking.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class TransactionDto {

  private UUID fromAccount;
  private UUID toAccount;
  private BigDecimal amount;
}
