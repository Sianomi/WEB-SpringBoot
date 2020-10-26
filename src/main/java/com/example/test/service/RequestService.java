package com.example.test.service;

import com.example.test.aws.Lambda;
import com.example.test.aws.S3;
import com.example.test.dao.InferLogDAO;
import com.example.test.dao.UserDAO;
import com.example.test.repository.InferLogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import org.json.JSONArray;
import org.json.JSONObject;

@Service
public class RequestService {
    
    private final S3 s3;
    private final Lambda lambda;
    private final Logger logger;
    private final InferLogRepository inferLogRepository;

    private MultipartFile mpf;
    private String originalS3path;

    private Authentication authentication;
    private UserDAO principal;
    private ObjectMapper mapper;

    public RequestService(InferLogRepository inferLogRepository) {
        this.inferLogRepository = inferLogRepository;
        s3 = new S3();
        lambda = new Lambda();
        logger = LoggerFactory.getLogger(this.getClass());
        mapper = new ObjectMapper();
    }

    public void OriginalS3Upload(MultipartFile request) throws FileNotFoundException {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        principal = (UserDAO) authentication.getPrincipal();
        logger.info("Try Original Image S3 Upload. Email : " + principal.getEID());
        if(!request.isEmpty())
        {
            mpf = request;
            String OriginalFilename = mpf.getOriginalFilename();
            logger.info(OriginalFilename +" uploaded! Email : " + principal.getEID());
            originalS3path = s3.OriginalUpload(mpf);
            return;
        }
        logger.error("Can't Receive Original Image File. Email : " + principal.getEID());
        throw new FileNotFoundException();
    }

    public String getSagemakerInferenceImage() throws IOException {
        Map<String, String> result = new HashMap<>();
        Map<String, String> Json = new HashMap<>();

        result.put("result","");
        Json.put("originalS3Path", originalS3path);
        Json.put("saveS3Path", s3.getSageMakerS3Path());

        return mapper.writeValueAsString(result);
    }

    public String getRekognitionInferenceImage() throws IOException {
        Map<String, String> result = new HashMap<>();
        Map<String, Object> Json = new HashMap<>();

        result.put("result","");
        Json.put("originalS3Path", originalS3path);
        Json.put("saveS3Path", s3.getRekognitionS3Path());

        String invokeResult = lambda.invokeRequest("rekognition_inference", mapper.writeValueAsString(Json));

        Json = mapper.readValue(invokeResult, Map.class);

        JSONObject json = new JSONObject((String) Json.get("result"));
        String nameList =  new String();
        for(Object test : json.getJSONArray("CustomLabels")){
            JSONObject CustomLabel = (JSONObject)test;
            nameList += ( CustomLabel.getString("Name") + ", " );
        }
        Json.put("resultName", nameList);
        writeLog(Json, "Rekognition");

        result.put("result", s3.getByte64ImageFromS3((String) Json.get("saveS3Path")));

        return mapper.writeValueAsString(result);
    }

    private void writeLog(Map<String, Object> Json, String usedService) throws JsonProcessingException {
        inferLogRepository.save(InferLogDAO.builder()
                .bucketname("mgt-web-test")
                .eID(principal.getEID())
                .inferimagepath((String) Json.get("saveS3Path"))
                .originalimagepath(originalS3path)
                .usedservice(usedService)
                .result((String) Json.get("resultName")).build());
    }

}
