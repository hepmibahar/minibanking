package com.tr.minibanking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "banking", name = "transaction")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
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
  private LocalDateTime transactionDate;

  @Column(name = "status", nullable = false)
  private int status;

  @PrePersist
  protected void onCreate() {
    transactionDate = LocalDateTime.now();
  }
}
