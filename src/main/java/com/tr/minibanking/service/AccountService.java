package com.tr.minibanking.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tr.minibanking.entity.Account;
import com.tr.minibanking.enums.Message;
import com.tr.minibanking.generator.IbanGenerator;
import com.tr.minibanking.repository.AccountRepository;
import com.tr.minibanking.security.JwtTokenUtil;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private IbanGenerator ibanGenerator;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  public Account createAccount(Account account) throws Exception {
    account.setId(UUID.randomUUID());
    account.setBalance(BigDecimal.valueOf(0));
    account.setAccountNumber(generateUniqueIban());
    account.setUser(jwtTokenUtil.getAuthenticatedUser());
    return accountRepository.save(account);
  }

  private String generateUniqueIban() throws Exception {
    String iban;
    do {
      iban = IbanGenerator.generateIban();
    }
    while (!accountRepository.findByAccountNumber(iban).isEmpty());
    return iban;
  }

  public List<Account> searchAccounts(String accountNumber, String accountName) {
    if (accountNumber != null && !accountNumber.isEmpty()) {
      return accountRepository.findByAccountNumber(accountNumber);
    } else if (accountName != null && !accountName.isEmpty()) {
      return accountRepository.findByAccountName(accountName);
    } else {
      return accountRepository.findAll();
    }
  }

  public Account updateAccount(UUID id, Account account) throws Exception {
    Account existingAccount = accountRepository.findById(id).orElseThrow(() -> new Exception(Message.TRANSACTION_FAILED));
    existingAccount.setAccountName(account.getAccountName());
    existingAccount.setAccountNumber(account.getAccountNumber());
    existingAccount.setBalance(account.getBalance());
    return accountRepository.save(existingAccount);
  }

  public void deleteAccount(UUID id) throws Exception {
    Account existingAccount = accountRepository.findById(id).orElseThrow(() -> new Exception(Message.TRANSACTION_FAILED));
    accountRepository.delete(existingAccount);
  }

  public Account getAccountDetails(UUID id) throws Exception {
    return accountRepository.findById(id).orElseThrow(() -> new Exception(Message.TRANSACTION_FAILED));
  }
}

