package com.example.test.service;

import com.example.test.aws.S3;
import com.example.test.aws.SageMaker;
import com.example.test.dao.UserDAO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class RequestService {
    
    private final S3 s3;

    private MultipartFile mpf;
    private Map<String, String> result;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RequestService() {
        Map<String, String> result = new HashMap<>();
        result.put("result","");
        this.result = result;
        s3 = new S3();
    }

    public String OriginalS3Upload(MultipartFile request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDAO principal = (UserDAO) auth.getPrincipal();
        logger.info("Try Original Image S3 Upload. Email : " + principal.getEID());
        if(!request.isEmpty())
        {
            mpf = request;
            String OriginalFilename = mpf.getOriginalFilename();
            logger.info(OriginalFilename +" uploaded! Email : " + principal.getEID());
            return s3.OriginalUpload(mpf);
        }
        logger.error("Can't Receive Original Image File. Email : " + principal.getEID());
        return "";
    }

    public String getSagemakerInferenceImage(String originalS3path) throws IOException {
        // SageMaker 추론 입력 및 결과
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }

    public String getRekognitionInferenceImage(String originalS3path) throws IOException {
        // Rekognition 추론 입력 및 결과
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }
}
