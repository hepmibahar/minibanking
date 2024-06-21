package com.tr.minibanking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

import com.tr.minibanking.enums.Message;

@Data
@Entity
@Table(schema = "banking",name="account", uniqueConstraints = @UniqueConstraint(columnNames = "account_number"))
public class Account extends BaseEntity{

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "account_number", unique = true)
  private String accountNumber;

  @Column(name = "account_name")
  private String accountName;

  @Column(name = "balance", precision = 15, scale = 2, nullable = false)
  private BigDecimal balance;

}
