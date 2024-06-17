package com.tr.minibanking.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import com.tr.minibanking.MessageEnum;
import com.tr.minibanking.entity.User;
import com.tr.minibanking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        new ArrayList<>());
  }

  public String save(User user){
    if(userRepository.findByUsername(user.getUsername())!=null){
      return MessageEnum.USER_ALREADY.getMesaj();
    }
    user.setId(UUID.randomUUID());
    user.setCreatedAt(LocalDateTime.now());
    userRepository.save(user);
    return MessageEnum.TRANSACTION_SUCCESSFUL.getMesaj();
  }
}
