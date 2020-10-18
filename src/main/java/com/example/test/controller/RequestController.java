package com.example.test.controller;

import com.example.test.aws.S3;
import com.example.test.aws.SageMaker;

import com.example.test.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PostMapping(value = "/infer", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public String upload(Model model, MultipartHttpServletRequest request) throws IOException {
        return requestService.getInferenceImage(request);
    }
}
