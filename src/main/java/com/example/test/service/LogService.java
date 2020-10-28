package com.example.test.service;

import com.example.test.aws.S3;
import com.example.test.dao.InferLogDAO;
import com.example.test.dao.UserDAO;
import com.example.test.repository.InferLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogService {                                                   // 사용 기록과 관련된 요청을 처리하기 위한 LogService Class

    private final InferLogRepository inferLogRepository;                    // 사용 기록이 저장된 DB를 사용하기 위한 JPA
    private final S3 s3;                                                    // AWS S3와 관련된 요청을 처리하기 위한 s3 변수
    private ObjectMapper mapper;                                            // MAP <-> JSON 변환을 위한 변수

    public LogService(InferLogRepository inferLogRepository) {              // LogService 생성자. InferLogReposirory를 매개변수로 받음.
        this.inferLogRepository = inferLogRepository;                       // JAVA Bean에서 불러온 JPA 정보를 저장
        mapper = new ObjectMapper();                                        // MAP <-> JSON 변환을 위한 변수 생성
        s3 = new S3();                                                      // S3 변수 생성
    }

    public void getLog(Model model){                                                                        // 사용 기록 정보를 조회하기 위한 함수
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();                       // 사용자 인증 정보를 불러와서 저장
        UserDAO principal = (UserDAO) auth.getPrincipal();                                                  // 인증 정보에서 사용자 정보만을 불러와서 저장

        if(principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {            // 만일 요청한 사람이 관리자라면
            List<InferLogDAO> test = inferLogRepository.findAllByOrderByTimestampDesc();                    // 모든 사람의 사용 기록 정보를 불러와서 저장
            model.addAttribute("logList", test);                                                // 불러온 사용 기록 정보를 Key "logList"에 저장
        } else {                                                                                            // 만일 요청한 사람이 일반 사용자라면
            List<InferLogDAO> test = inferLogRepository.findByeIDOrderByTimestampDesc(principal.getEID());  // 해당 사용자의 기록만을 불러와서 저장
            model.addAttribute("logList", test);                                                // 불러온 사용 기록 정보를 Key "logList"에 저장
        }
    }

    public String getS3Image(String s3path) throws IOException {                                            // 사용 기록의 Image 조회를 처리하기 위한 함수
        Map<String, String> result = new HashMap<>();                                                       // Base64 Image Data를 Return 하기 위한 변수
        result.put("result",s3.getBase64ImageFromS3(s3path));                                               // 매개변수로 받은 s3Path의 이미지를 조회하여 result 변수에 Key "result"로 저장
        return mapper.writeValueAsString(result);                                                           // result 변수를 JSON String으로 변환한 뒤 Return
    }
}
