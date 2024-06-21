package com.tr.minibanking.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tr.minibanking.dto.UserDto;
import com.tr.minibanking.enums.Message;
import com.tr.minibanking.entity.User;
import com.tr.minibanking.mapper.UserMapper;
import com.tr.minibanking.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserMapper userMapper;

  public Message save(UserDto userDto) {
    if (isUsernameTaken(userDto.getUsername())) {
      return Message.USER_ALREADY;
    }
    if (isEmailTaken(userDto.getEmail())) {
      return Message.USER_EMAIL_ALREADY;
    }
    User user = userMapper.toEntity(userDto);
    prepareUserForSaving(user);
    userRepository.save(user);

    return Message.TRANSACTION_SUCCESSFUL;
  }

  private boolean isUsernameTaken(String username) {
    return userRepository.findByUsername(username) != null;
  }

  private boolean isEmailTaken(String email) {
    return userRepository.findByEmail(email) != null;
  }

  private void prepareUserForSaving(User user) {
    user.setId(UUID.randomUUID());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

}