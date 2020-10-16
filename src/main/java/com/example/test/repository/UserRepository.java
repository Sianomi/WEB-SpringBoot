package com.example.test.repository;

import java.util.Optional;
import com.example.test.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDAO, Long> {
  Optional<UserDAO> findByeID(String eID);
}