package com.tr.minibanking.repository;

import com.tr.minibanking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
  List<Transaction> findByFromAccount_IdOrToAccount_Id(UUID fromAccountId, UUID toAccountId);
}
