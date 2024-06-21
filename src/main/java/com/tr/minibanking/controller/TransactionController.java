package com.tr.minibanking.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tr.minibanking.dto.TransactionDto;
import com.tr.minibanking.entity.Transaction;
import com.tr.minibanking.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping("/transfer")
  public ResponseEntity<?> initiateMoneyTransfer(@RequestBody TransactionDto transactionDTO) {
    try {
      Transaction transaction = transactionService.createTransaction(transactionDTO);
      return ResponseEntity.ok(transaction);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/account/{accountId}")
  public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable UUID accountId) {
    List<Transaction> transactions = transactionService.getTransactionHistory(accountId);
    return ResponseEntity.ok(transactions);
  }
}

