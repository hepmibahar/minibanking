package com.tr.minibanking.service;

import com.tr.minibanking.entity.Account;
import com.tr.minibanking.enums.Message;
import com.tr.minibanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  public Account createAccount(Account account) throws Exception {
    return accountRepository.save(account);
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

