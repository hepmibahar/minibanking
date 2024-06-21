package com.tr.minibanking.service;

import com.tr.minibanking.dto.TransactionDto;
import com.tr.minibanking.entity.Account;
import com.tr.minibanking.entity.Transaction;
import com.tr.minibanking.enums.TransactionStatus;
import com.tr.minibanking.repository.AccountRepository;
import com.tr.minibanking.repository.TransactionRepository;
import com.tr.minibanking.enums.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final AccountRepository accountRepository;

  public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
    this.transactionRepository = transactionRepository;
    this.accountRepository = accountRepository;
  }

  @Transactional
  public Transaction createTransaction(TransactionDto transactionDTO) throws Exception {
    Account fromAccount = accountRepository.findById(transactionDTO.getFromAccount())
        .orElseThrow(() -> new Exception("Sender account not found"));

    Account toAccount = accountRepository.findById(transactionDTO.getToAccount())
        .orElseThrow(() -> new Exception("Recipient account not found"));

    if (fromAccount.getBalance().compareTo(transactionDTO.getAmount()) < 0) {
      throw new Exception(Message.INSUFFICIENT_FUNDS.toString());
    }

    fromAccount.setBalance(fromAccount.getBalance().subtract(transactionDTO.getAmount()));
    toAccount.setBalance(toAccount.getBalance().add(transactionDTO.getAmount()));

    Transaction transaction = new Transaction();
    transaction.setFromAccount(fromAccount);
    transaction.setToAccount(toAccount);
    transaction.setAmount(transactionDTO.getAmount());
    transaction.setStatus(TransactionStatus.SUCCESS.getValue());

    accountRepository.save(fromAccount);
    accountRepository.save(toAccount);

    return transactionRepository.save(transaction);
  }

  public List<Transaction> getTransactionHistory(UUID accountId) {
    return transactionRepository.findByFromAccount_IdOrToAccount_Id(accountId, accountId);
  }
}
