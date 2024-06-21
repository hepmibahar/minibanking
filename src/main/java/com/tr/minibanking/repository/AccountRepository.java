package com.tr.minibanking.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tr.minibanking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, UUID> {
  List<Account> findByAccountNumber(String accountNumber);
  List<Account> findByAccountName(String accountName);
}
