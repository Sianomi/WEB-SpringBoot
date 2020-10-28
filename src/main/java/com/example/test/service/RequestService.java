package com.example.test.service;

import com.example.test.aws.Lambda;
import com.example.test.aws.S3;
import com.example.test.dao.InferLogDAO;
import com.example.test.dao.UserDAO;
import com.example.test.repository.InferLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class RequestService {                                           // 추론요청을 처리하기 위한 RequestService Class
    
    private final S3 s3;                                                // S3관련 요청을 수행하기 위한 s3 변수
    private final Lambda lambda;                                        // AWS Lambda 요청을 수행하기 위한 lambda 변수
    private final Logger logger;                                        // System Log 작성을 위한 logger 변수
    private final InferLogRepository inferLogRepository;                // 사용기록 작성을 위한 JPA 변수

    private String originalS3path;                                      // 원본 이미지 저장 S3 Path를 기록하기 위한 변수
    private Authentication authentication;                              // 사용자 인증정보를 저장하기 위한 변수
    private UserDAO principal;                                          // User정보를 저장하기 위한 변수
    private ObjectMapper mapper;                                        // MAP <-> JSON 변환을 위한 변수

    public RequestService(InferLogRepository inferLogRepository) {      // RequestService 생성자. JPA Repository를 매개변수로 받음
        this.inferLogRepository = inferLogRepository;                   // Bean에서 받은 inferLogRepository 정보를 Class 내 변수에 저장
        s3 = new S3();                                                  // S3 Class 변수 생성
        lambda = new Lambda();                                          // Lambda Class 변수 생성
        logger = LoggerFactory.getLogger(this.getClass());              // System Log 변수 생성
        mapper = new ObjectMapper();                                    // Mapper 변수 생성
    }

    public void OriginalS3Upload(MultipartFile request) throws FileNotFoundException {              // 원본 Image를 S3에 업로드하기 위한 함수
        authentication = SecurityContextHolder.getContext().getAuthentication();                    // 사용자 인증정보를 불러와 저장
        principal = (UserDAO) authentication.getPrincipal();                                        // 인증정보에서 User 정보만 불러와 저장
        logger.info("Try Original Image S3 Upload. Email : " + principal.getEID());                 // S3 Upload 요청이 있었음을 System Log에 기록
        if(!request.isEmpty())                                                                      // 만일 요청받은 Image File이 비어있지 않다면
        {
            String OriginalFilename = request.getOriginalFilename();                                // Image File Name을 저장
            logger.info(OriginalFilename +" uploaded! Email : " + principal.getEID());              // S3 Upload 시작을 System Log에 기록
            originalS3path = s3.OriginalUpload(request);                                            // S3에 업로드를 수행하고 Key Path를 저장
            return;                                                                                 // Return
        }
        logger.error("Can't Receive Original Image File. Email : " + principal.getEID());           // 만일 요청받은 Image File이 비어있다면 이를 System Log에 기록
        throw new FileNotFoundException();                                                          // FileNotFound 예외 발생
    }

    public String getSagemakerInferenceImage() throws IOException {                                 // SageMaker 추론을 수행하는 함수
        Map<String, String> result = new HashMap<>();                                               // Base64 Image Data를 Return 하기 위한 변수
        Map<String, Object> Json = new HashMap<>();                                                 // AWS Lambda와 정보교환을 위한 변수

        result.put("result","");                                                                    // Key "result"인 빈 값 생성. 요청실패시 빈 값이 Return
        Json.put("originalS3Path", originalS3path);                                                 // 원본 Image S3 Key Path를 Json 변수에 Key "originalS3Path"로 저장
        Json.put("saveS3Path", s3.getSageMakerS3Path());                                            // 결과 Image를 저장하기 위한 S3 Path를 Json 변수에 Key "saveS3Path"로 저장

        String invokeResult = lambda.invokeRequest("sagemaker_inference",               // 추론 수행. 추론은 AWS Lambda를 호출하는 방식으로 이루어짐.
                mapper.writeValueAsString(Json));
        logger.info(invokeResult);                                                                  // 추론 결과를 System Log에 기록
        Json = mapper.readValue(invokeResult, Map.class);                                           // 추론 결과는 Json String 타입. 이를 MAP으로 읽어들임.

        if((int) Json.get("statusCode") == 200){                                                    // 만일 요청이 성공했다면
            writeLog(Json, "SageMaker");                                                  // 추론 기록을 DB에 기록
            result.put("result", s3.getBase64ImageFromS3((String) Json.get("saveS3Path")));         // S3에 저장된 결과 Image를 불러와 Base64로 인코딩한 결과를 result 변수에 Key "result"로 저장
        }

        return mapper.writeValueAsString(result);                                                   // result 변수를 String 으로 변환한 뒤 Return
    }

    public String getRekognitionInferenceImage() throws IOException {                               // Rekognition 추론을 수행하는 함수
        Map<String, String> result = new HashMap<>();                                               // Base64 Image Data를 Return 하기 위한 변수
        Map<String, Object> Json = new HashMap<>();                                                 // AWS Lambda와 정보교환을 위한 변수


        result.put("result","");                                                                    // Key "result"인 빈 값 생성. 요청실패시 빈 값이 Return
        Json.put("originalS3Path", originalS3path);                                                 // 원본 Image S3 Key Path를 Json 변수에 Key "originalS3Path"로 저장
        Json.put("saveS3Path", s3.getRekognitionS3Path());                                          // 결과 Image를 저장하기 위한 S3 Path를 Json 변수에 Key "saveS3Path"로 저장

        String invokeResult = lambda.invokeRequest("rekognition_inference",             // 추론 수행. 추론은 AWS Lambda를 호출하는 방식으로 이루어짐.
                mapper.writeValueAsString(Json));
        logger.info(invokeResult);                                                                  // 추론 결과를 System Log에 기록
        Json = mapper.readValue(invokeResult, Map.class);                                           // 추론 결과는 Json String 타입. 이를 MAP으로 읽어들임.

        if((int) Json.get("statusCode") == 200){                                                    // 만일 요청이 성공했다면
            writeLog(Json, "Rekognition");                                                // 추론 기록을 DB에 기록
            result.put("result", s3.getBase64ImageFromS3((String) Json.get("saveS3Path")));         // S3에 저장된 결과 Image를 불러와 Base64로 인코딩한 결과를 result 변수에 Key "result"로 저장
        }

        return mapper.writeValueAsString(result);                                                   // result 변수를 String 으로 변환한 뒤 Return
    }

    private void writeLog(Map<String, Object> Json, String usedService) {                           // 추론 기록을 저장하기 위한 함수. 매개변수로 추론 정보가 저장된 Json 변수와 사용된 서비스명을 받음.
        inferLogRepository.save(InferLogDAO.builder()                                               // JPA save를 수행. 이는 SQL Insert를 수행하는 함수임
                .bucketname("mgt-web-test")                                                         // Image Save S3 Bucket Name
                .eID(principal.getEID())                                                            // 사용자 Email
                .inferimagepath((String) Json.get("saveS3Path"))                                    // 추론 결과 Image가 저장된 S3 Key Path
                .originalimagepath(originalS3path)                                                  // 원본 Image가 저장된 S3 Key Path
                .usedservice(usedService)                                                           // 사용된 서비스명
                .result((String) Json.get("result")).build());                                      // 추론 결과. 정확도와 Bounding Box정보는 Image에 이미 저장되어 있기 때문에 여기선 Detect된 Name만 저장.
    }
}
