package com.tr.minibanking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

@Data
@Entity
@Table(schema = "banking", name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User extends BaseEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @Column(name = "username", unique = true)
  @Length(max = 50)
  private String username;

  @Column(name="password")
  @Length(max = 64)
  private String password;

  @Email
  @Column(name = "email")
  @Length(max = 255)
  private String email;

}
