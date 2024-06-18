package com.tr.minibanking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.tr.minibanking.enums.Message;

@Data
@Entity
@Table(schema = "banking", name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User extends BaseEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @NotBlank(message = Message.USERNAME_MANDATORY)
  @Size(min = 3, max = 50, message = Message.USERNAME_SIZE)
  @Column(name = "username", unique = true)
  @Length(max = 50)
  private String username;

  @Column(name="password")
  @NotBlank(message = Message.PASSWORD_MANDATORY)
  @Size(min = 6, max = 64, message = Message.PASSWORD_SIZE)
  @Length(max = 64)
  private String password;

  @Email
  @Column(name = "email")
  @NotBlank(message = Message.EMAIL_MANDATORY)
  @Size(min = 10, max = 255, message = Message.EMAIL_SIZE)
  @Length(max = 255)
  private String email;

}
