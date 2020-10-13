package com.example.test.repository;

import java.util.Optional;
import com.example.test.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
  Optional<UserModel> findByeID(String eID);
}