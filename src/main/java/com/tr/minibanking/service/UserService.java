package com.tr.minibanking.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tr.minibanking.MessageEnum;
import com.tr.minibanking.entity.User;
import com.tr.minibanking.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public String save(User user){
    if(userRepository.findByUsername(user.getUsername())!=null){
      return MessageEnum.USER_ALREADY.getMesaj();
    }
    if(userRepository.findByEmail(user.getEmail())!=null){
      return MessageEnum.USER_EMAIL_ALREADY.getMesaj();
    }
    user.setId(UUID.randomUUID());
    user.setCreatedAt(LocalDateTime.now());
    userRepository.save(user);
    return MessageEnum.TRANSACTION_SUCCESSFUL.getMesaj();
  }
}
