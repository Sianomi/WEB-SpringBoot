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
public class LogService {

    private final InferLogRepository inferLogRepository;
    private final S3 s3;
    private ObjectMapper mapper;

    public LogService(InferLogRepository inferLogRepository) {
        this.inferLogRepository = inferLogRepository;
        mapper = new ObjectMapper();
        s3 = new S3();
    }

    public void getLog(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDAO principal = (UserDAO) auth.getPrincipal();

        if(principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            List<InferLogDAO> test = inferLogRepository.findAllByOrderByTimestampDesc();
            model.addAttribute("logList", test);
        } else {
            List<InferLogDAO> test = inferLogRepository.findByeIDOrderByTimestampDesc(principal.getEID());
            model.addAttribute("logList", test);
        }
    }

    public String getS3Image(String s3path) throws IOException {
        Map<String, String> result = new HashMap<>();
        result.put("result",s3.getByte64ImageFromS3(s3path));
        return mapper.writeValueAsString(result);
    }
}
