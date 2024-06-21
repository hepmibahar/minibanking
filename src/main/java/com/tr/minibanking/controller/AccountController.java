package com.tr.minibanking.controller;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tr.minibanking.dto.AccountDto;
import com.tr.minibanking.enums.Message;
import com.tr.minibanking.model.ApiResponse;
import com.tr.minibanking.service.AccountService;

@RestController
@CrossOrigin
@RequestMapping("/api/accounts")
public class AccountController {

  @Lazy
  @Autowired
  private AccountService accountService;

  @PostMapping
  public ResponseEntity<ApiResponse<AccountDto>> createAccount(@Valid @RequestBody AccountDto accountDTO) {
    try {
      AccountDto createdAccount = accountService.createAccount(accountDTO);
      return buildResponse(HttpStatus.CREATED, Message.TRANSACTION_SUCCESSFUL, createdAccount);
    } catch (Exception e) {
      return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, Message.INTERNAL_ERROR, null);
    }
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<AccountDto>>> searchAccounts(
      @RequestParam(required = false) String accountNumber,
      @RequestParam(required = false) String accountName) {
    try {
      List<AccountDto> accounts = accountService.searchAccounts(accountNumber, accountName);
      return buildResponse(HttpStatus.OK, Message.TRANSACTION_SUCCESSFUL, accounts);
    } catch (Exception e) {
      return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, Message.INTERNAL_ERROR, null);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<AccountDto>> updateAccount(
      @PathVariable UUID id,
      @Valid @RequestBody AccountDto accountDTO) {
    try {
      AccountDto updatedAccount = accountService.updateAccount(id, accountDTO);
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
  public ResponseEntity<ApiResponse<AccountDto>> getAccountDetails(@PathVariable UUID id) {
    try {
      AccountDto account = accountService.getAccountDetails(id);
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
