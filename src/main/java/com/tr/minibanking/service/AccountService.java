package com.tr.minibanking.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tr.minibanking.dto.AccountDto;
import com.tr.minibanking.entity.Account;
import com.tr.minibanking.enums.Message;
import com.tr.minibanking.generator.IbanGenerator;
import com.tr.minibanking.mapper.AccountMapper;
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

  @Autowired
  private AccountMapper accountMapper;

  public AccountDto createAccount(AccountDto accountDto) throws Exception {
    Account account = accountMapper.toEntity(accountDto);
    account.setId(UUID.randomUUID());
    account.setBalance(BigDecimal.valueOf(0));
    account.setAccountNumber(generateUniqueIban());
    account.setUser(jwtTokenUtil.getAuthenticatedUser());
    Account savedAccount = accountRepository.save(account);
    return accountMapper.toDto(savedAccount);
  }

  private String generateUniqueIban() throws Exception {
    String iban;
    do {
      iban = IbanGenerator.generateIban();
    } while (!accountRepository.findByAccountNumber(iban).isEmpty());
    return iban;
  }

  public List<AccountDto> searchAccounts(String accountNumber, String accountName) {
    List<Account> accounts;
    if (accountNumber != null && !accountNumber.isEmpty()) {
      accounts = accountRepository.findByAccountNumber(accountNumber);
    } else if (accountName != null && !accountName.isEmpty()) {
      accounts = accountRepository.findByAccountName(accountName);
    } else {
      accounts = accountRepository.findAll();
    }
    return accounts.stream().map(accountMapper::toDto).collect(Collectors.toList());
  }

  public AccountDto updateAccount(UUID id, AccountDto accountDto) throws Exception {
    Account existingAccount = accountRepository.findById(id)
        .orElseThrow(() -> new Exception(Message.TRANSACTION_FAILED));
    accountMapper.updateEntityFromDto(accountDto, existingAccount);
    Account updatedAccount = accountRepository.save(existingAccount);
    return accountMapper.toDto(updatedAccount);
  }

  public void deleteAccount(UUID id) throws Exception {
    Account existingAccount = accountRepository.findById(id)
        .orElseThrow(() -> new Exception(Message.TRANSACTION_FAILED));
    accountRepository.delete(existingAccount);
  }

  public AccountDto getAccountDetails(UUID id) throws Exception {
    Account account = accountRepository.findById(id)
        .orElseThrow(() -> new Exception(Message.TRANSACTION_FAILED));
    return accountMapper.toDto(account);
  }
}
