package com.tr.minibanking.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tr.minibanking.entity.Account;
import com.tr.minibanking.enums.Message;
import com.tr.minibanking.model.ApiResponse;
import com.tr.minibanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/accounts")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @PostMapping
  public ResponseEntity<ApiResponse<Account>> createAccount(@Valid @RequestBody Account account) {
    try {
      Account createdAccount = accountService.createAccount(account);
      return buildResponse(HttpStatus.CREATED, Message.TRANSACTION_SUCCESSFUL, createdAccount);
    } catch (Exception e) {
      return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, Message.INTERNAL_ERROR, null);
    }
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<Account>>> searchAccounts(@RequestParam(required = false) String accountNumber, @RequestParam(required = false) String accountName) {
    try {
      List<Account> accounts = accountService.searchAccounts(accountNumber, accountName);
      return buildResponse(HttpStatus.OK, Message.TRANSACTION_SUCCESSFUL, accounts);
    } catch (Exception e) {
      return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, Message.INTERNAL_ERROR, null);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<Account>> updateAccount(@PathVariable UUID id, @Valid @RequestBody Account account) {
    try {
      Account updatedAccount = accountService.updateAccount(id, account);
      return buildResponse(HttpStatus.OK, Message.TRANSACTION_SUCCESSFUL, updatedAccount);
    } catch (Exception e) {
      return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, Message.INTERNAL_ERROR, null);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> deleteAccount(@PathVariable UUID id) {
    try {
      accountService.deleteAccount(id);
      return buildResponse(HttpStatus.OK, Message.TRANSACTION_SUCCESSFUL, null);
    } catch (Exception e) {
      return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, Message.INTERNAL_ERROR, null);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<Account>> getAccountDetails(@PathVariable UUID id) {
    try {
      Account account = accountService.getAccountDetails(id);
      return buildResponse(HttpStatus.OK, Message.TRANSACTION_SUCCESSFUL, account);
    } catch (Exception e) {
      return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, Message.INTERNAL_ERROR, null);
    }
  }

  private <T> ResponseEntity<ApiResponse<T>> buildResponse(HttpStatus status, Message message, T data) {
    ApiResponse<T> response = new ApiResponse<>(status, message, data);
    return new ResponseEntity<>(response, status);
  }
}
