package com.example.test.repository;

import com.example.test.dao.InferLogDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InferLogRepository extends JpaRepository<InferLogDAO, Long> {
  Page<InferLogDAO> findByeID(String eID, Pageable pageable);
}