package com.tr.minibanking.controller;

import jakarta.validation.Valid;

import com.tr.minibanking.enums.Message;
import com.tr.minibanking.entity.User;
import com.tr.minibanking.model.ApiResponse;
import com.tr.minibanking.model.JwtRequest;
import com.tr.minibanking.model.JwtResponse;
import com.tr.minibanking.security.JwtTokenUtil;
import com.tr.minibanking.service.JwtUserDetailsService;
import com.tr.minibanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private JwtUserDetailsService jwtUserDetailsService;

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<JwtResponse>> authenticateUser(@Valid @RequestBody JwtRequest authenticationRequest) {
    try {
      JwtResponse jwtResponse = jwtUserDetailsService.authenticateUser(authenticationRequest);
      return buildResponse(HttpStatus.OK, Message.LOGIN_SUCCESSFUL, jwtResponse);
    } catch (BadCredentialsException e) {
      return buildResponse(HttpStatus.UNAUTHORIZED, Message.INCORRECT_USER_PASSWORD, null);
    } catch (Exception e) {
      return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, Message.INTERNAL_ERROR, null);
    }
  }

  @PostMapping("/register")
  public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody @Valid User user) {
    try {
      Message responseMessage = userService.save(user);
      if (responseMessage.equals(Message.TRANSACTION_SUCCESSFUL.getMessage())) {
        return buildResponse(HttpStatus.CREATED, responseMessage, user.getId().toString());
      } else {
        return buildResponse(HttpStatus.BAD_REQUEST, responseMessage, null);
      }
    } catch (Exception e) {
      return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, Message.INTERNAL_ERROR, null);
    }
  }

  private <T> ResponseEntity<ApiResponse<T>> buildResponse(HttpStatus status, Message message, T data) {
    ApiResponse<T> response = new ApiResponse<>(status, message, data);
    return new ResponseEntity<>(response, status);
  }
}
