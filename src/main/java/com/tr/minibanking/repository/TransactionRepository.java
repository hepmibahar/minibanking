package com.tr.minibanking.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tr.minibanking.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findByFromAccount_IdOrToAccount_Id(UUID fromAccountId, UUID toAccountId);
}
