package com.example.test.service;

import com.example.test.aws.S3;
import com.example.test.aws.SageMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class RequestService {
    
    private final S3 s3;

    private MultipartFile mpf;
    private Map<String, String> result;

    public RequestService() {
        Map<String, String> result = new HashMap<>();
        result.put("result","");
        this.result = result;
        s3 = new S3();
    }

    public String OriginalS3Upload(MultipartFile request){
        if(!request.isEmpty())
        {
            mpf = request;
            String OriginalFilename = mpf.getOriginalFilename();
            System.out.println(OriginalFilename +" uploaded!");
            return s3.OriginalUpload(mpf);
        }
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
