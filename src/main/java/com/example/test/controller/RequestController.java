package com.example.test.controller;

import com.example.test.aws.S3;
import com.example.test.aws.SageMaker;

import com.example.test.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class RequestController {                                                            // 처리 요청을 받기 위한 RequestController

    private final RequestService requestService;                                            // 실제 처리를 위한 RequestService
    private final S3 s3;
    private String fileObjKeyName;

    @PostMapping(value = "/originals3")
    @ResponseBody
    public int OriginalS3Upload(Model model,
                                @RequestParam("filedata") MultipartFile request,
                                @RequestParam("solution") int solution) throws IOException {
        try {
            requestService.OriginalS3Upload(request);
        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @PostMapping(value = "/sagemaker", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public String InferenceSagemaker(Model model) throws IOException {

        return "test";
    }

    @PostMapping(value = "/rekognition", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public String InferenceRekognition(Model model) throws IOException {
        return requestService.getRekognitionInferenceImage();
    }
}
