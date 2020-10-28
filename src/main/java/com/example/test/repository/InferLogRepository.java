package com.example.test.repository;

import com.example.test.dao.InferLogDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InferLogRepository extends JpaRepository<InferLogDAO, Long> {  // InferLog에 대한 Query를 보내기 위한 Repository
  List<InferLogDAO> findByeIDOrderByTimestampDesc(String eID);
  List<InferLogDAO> findAllByOrderByTimestampDesc();
}