package com.example.test.repository;

import java.util.Optional;
import com.example.test.dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDAO, Long> {  // 사용자 정보에 대한 Query를 보내기 위한 Repository
  Optional<UserDAO> findByeID(String eID);                              // 특정 Email ID에 대한 정보 요청 함수
}