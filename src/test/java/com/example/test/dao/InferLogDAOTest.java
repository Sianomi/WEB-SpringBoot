package com.example.test.dao;

import com.example.test.TestApplication;
import com.example.test.repository.InferLogRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@SpringBootTest(classes= TestApplication.class)
public class InferLogDAOTest {

    @Autowired
    InferLogRepository inferLogRepository;

    @Test
    public void builder() {
        InferLogDAO test = InferLogDAO.builder()
                .eID("8shkim@naver.com")
                .bucketname("mgt-web-test")
                .inferimagepath("s3 path")
                .originalimagepath("s32 path")
                .result("json")
                .usedservice("Rekognition").build();
        inferLogRepository.save(test);
    }
}