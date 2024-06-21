package com.tr.minibanking.controller;

import com.tr.minibanking.dto.TransactionDto;
import com.tr.minibanking.entity.Transaction;
import com.tr.minibanking.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

