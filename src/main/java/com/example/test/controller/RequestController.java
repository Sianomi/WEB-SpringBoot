package com.example.test.controller;

import com.example.test.aws.S3;

import com.example.test.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class RequestController {                                                            // 추론 요청을 처리하기 위한 RequestController Class

    private final RequestService requestService;                                            // 실제 처리를 위한 RequestService

    @PostMapping(value = "/originals3")                                                     // Post Request '/originals3' path Mapping
    @ResponseBody
    public int OriginalS3Upload(Model model,                                                // 추론처리와 추론기록관리를 위해 Image를 S3에 업로드하도록 요청하는 Controller
                                @RequestParam("filedata") MultipartFile request,            // 요청받은 Image File
                                @RequestParam("solution") int solution) {                   // 처리방법과 관련된 변수. 지금은 사용하지 않음.
        try {
            requestService.OriginalS3Upload(request);                                       // 요청받은 Image File을 S3에 업로드하도록 하는 requestService의 함수를 실행
        }catch (Exception e) {                                                              // 만일 오류가 발생한다면 이를 기록하고 0을 Return
            e.printStackTrace();                                                            // 이는 이후의 추론과정을 하지못하도록 하기위함
            return 0;
        }
        return 1;                                                                           // Image를 S3에 업로드하는데 성공했다면 1을 Return. 이후의 추론과정을 실행.
    }

    @PostMapping(value = "/sagemaker", produces = MediaType.IMAGE_JPEG_VALUE)               // Post Request '/sagemaker' path Mapping. Return Data가 Image임을 지정.
    @ResponseBody                                                                           // 만일 Return Data가 Image임을 명시하지 않으면 Chrome에서 에러가 발생
    public String InferenceSagemaker(Model model) throws IOException {                      // 요청된 Image에 대한 SageMaker 추론을 실행하는 Controller
        return requestService.getSagemakerInferenceImage();                                 // requestService의 SageMaker 추론을 실행하는 함수를 호출.
    }

    @PostMapping(value = "/rekognition", produces = MediaType.IMAGE_JPEG_VALUE)             // Post Request '/rekognition' path Mapping. Return Data가 Image임을 지정.
    @ResponseBody                                                                           // 만일 Return Data가 Image임을 명시하지 않으면 Chrome에서 에러가 발생
    public String InferenceRekognition(Model model) throws IOException {                    // 요청된 Image에 대한 Rekogniton 추론을 실행하는 Controller
        return requestService.getRekognitionInferenceImage();                               // requestService의 Rekognition 추론을 실행하는 함수를 호출.
    }
}
