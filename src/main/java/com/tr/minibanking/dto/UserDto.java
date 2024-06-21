package com.tr.minibanking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import com.tr.minibanking.enums.Message;

@Data
public class UserDto {

  @NotBlank(message = Message.USERNAME_MANDATORY)
  @Size(min = 3, max = 50, message = Message.USERNAME_SIZE)
  private String username;

  @NotBlank(message = Message.PASSWORD_MANDATORY)
  @Size(min = 6, max = 64, message = Message.PASSWORD_SIZE)
  private String password;

  @Email
  @NotBlank(message = Message.EMAIL_MANDATORY)
  @Size(min = 10, max = 255, message = Message.EMAIL_SIZE)
  private String email;
}
