package com.example.test.repository;

import com.example.test.dao.InferLogDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InferLogRepository extends JpaRepository<InferLogDAO, Long> {  // InferLog에 대한 Query를 보내기 위한 Repository
  List<InferLogDAO> findByeIDOrderByTimestampDesc(String eID);                  // Email ID를 이용해 Log를 불러오고 Timestamp 내림차순으로 정렬
  List<InferLogDAO> findAllByOrderByTimestampDesc();                            // 모든 사용자에 대한 Log를 불러오고 Timestamp 내림차순으로 정렬
}