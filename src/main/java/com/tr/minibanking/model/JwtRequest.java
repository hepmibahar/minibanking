package com.tr.minibanking.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

import com.tr.minibanking.enums.Message;

public class JwtRequest implements Serializable {

  private static final long serialVersionUID = 5926468583005150707L;

  @NotBlank(message = Message.USERNAME_MANDATORY)
  @Size(min = 3, max = 50, message = Message.USERNAME_SIZE)
  private String username;

  @NotBlank(message = Message.PASSWORD_MANDATORY)
  @Size(min = 6, max = 64, message = Message.PASSWORD_SIZE)
  private String password;

  public JwtRequest() {

  }

  public JwtRequest(String username, String password) {
    this.setUsername(username);
    this.setPassword(password);
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
