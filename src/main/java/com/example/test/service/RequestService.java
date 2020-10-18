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
        result.put("sagemaker","");
        result.put("rekognition","");
        this.result = result;
        s3 = new S3();
    }

    private void init(MultipartFile request){
        if(!request.isEmpty())
        {
            mpf = request;
            String OriginalFilename = mpf.getOriginalFilename();
            System.out.println(OriginalFilename +" uploaded!");
//            System.out.println(s3.OriginalUpload(mpf));
        }
    }

    public String getInferenceImage(MultipartFile request, int solution) throws IOException {
        init(request);
        switch (solution){
            case 1:
                result.put("sagemaker",SageMaker.predict(mpf));
                break;
            case 2:
                //rekognition 추론 입력 부분
                break;
            case 3:
                result.put("sagemaker",SageMaker.predict(mpf));
                //rekogniton 추론 입력 부분
                break;
            default:
                //에러처리
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }
}
