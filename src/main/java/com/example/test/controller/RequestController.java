package com.example.test.controller;

import com.example.test.aws.S3;
import com.example.test.aws.SageMaker;

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
public class RequestController {
    @PostMapping(value = "/infer", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public String upload(Model model, MultipartHttpServletRequest request) throws IOException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf = null;
        String ImgByte64 = null;
        if(itr.hasNext())
        {
            mpf = request.getFile(itr.next());
            String OriginalFilename = mpf.getOriginalFilename();
            System.out.println(OriginalFilename +" uploaded!");
            S3 s3 = new S3();
            System.out.println(s3.OriginalUpload(mpf));
            ImgByte64 = SageMaker.predict(mpf);
        }
        return ImgByte64;
    }
}
