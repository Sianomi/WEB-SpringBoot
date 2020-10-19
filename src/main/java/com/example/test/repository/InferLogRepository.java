package com.example.test.repository;

import com.example.test.dao.InferLogDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InferLogRepository extends JpaRepository<InferLogDAO, Long> {  // InferLog에 대한 Query를 보내기 위한 Repository
  Page<InferLogDAO> findByeID(String eID, Pageable pageable);                   // 특정 Email ID에 대한 Log 요청 함수
}