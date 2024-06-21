package com.tr.minibanking.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(schema = "banking", name = "transaction")
public class Transaction extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "from_account", nullable = false)
  private Account fromAccount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "to_account", nullable = false)
  private Account toAccount;

  @Column(name = "amount", precision = 15, scale = 2, nullable = false)
  private BigDecimal amount;

  @Column(name = "transaction_date", nullable = false)
  private LocalDateTime transactionDate = LocalDateTime.now();

  @Column(name = "status")
  private String status;
}
