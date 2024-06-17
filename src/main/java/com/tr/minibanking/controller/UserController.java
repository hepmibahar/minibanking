package com.tr.minibanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tr.minibanking.MessageEnum;
import com.tr.minibanking.entity.User;
import com.tr.minibanking.model.JwtRequest;
import com.tr.minibanking.model.JwtResponse;
import com.tr.minibanking.security.JwtTokenUtil;
import com.tr.minibanking.service.JwtUserDetailsService;
import com.tr.minibanking.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private JwtUserDetailsService userDetailsService;

  @Autowired
  private UserService userService;

  @Autowired
  private JwtUserDetailsService jwtUserDetailsService;

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> authenticateUser(@RequestBody JwtRequest authenticationRequest) throws Exception {
    JwtResponse jwtResponse = jwtUserDetailsService.authenticateUser(authenticationRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(jwtResponse);
  }

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody User user) {
    String responseMessage = userService.save(user);
    if (responseMessage.equals(MessageEnum.TRANSACTION_SUCCESSFUL.getMesaj())) {
      return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
    }
  }
}
