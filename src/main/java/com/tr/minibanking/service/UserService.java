package com.tr.minibanking.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tr.minibanking.MessageEnum;
import com.tr.minibanking.entity.User;
import com.tr.minibanking.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String save(User user) {
    if (isUsernameTaken(user.getUsername())) {
      return MessageEnum.USER_ALREADY.getMesaj();
    }
    if (isEmailTaken(user.getEmail())) {
      return MessageEnum.USER_EMAIL_ALREADY.getMesaj();
    }

    prepareUserForSaving(user);
    userRepository.save(user);

    return MessageEnum.TRANSACTION_SUCCESSFUL.getMesaj();
  }

  private boolean isUsernameTaken(String username) {
    return userRepository.findByUsername(username) != null;
  }

  private boolean isEmailTaken(String email) {
    return userRepository.findByEmail(email) != null;
  }

  private void prepareUserForSaving(User user) {
    user.setId(UUID.randomUUID());
    user.setCreatedAt(LocalDateTime.now());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
  }
}