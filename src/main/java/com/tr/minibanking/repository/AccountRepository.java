package com.tr.minibanking.repository;

import com.tr.minibanking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
  List<Account> findByAccountNumber(String accountNumber);
  List<Account> findByAccountName(String accountName);
}
