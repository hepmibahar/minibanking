package com.tr.minibanking.repository;

import java.util.UUID;

import com.tr.minibanking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  @Query(value = "select * from banking.user where username=:username",nativeQuery = true)
  User findByUsername(String username);
}
