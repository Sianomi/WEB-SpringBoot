package com.example.test.service;

import com.example.test.aws.S3;
import com.example.test.aws.SageMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class RequestService {
    
    private final S3 s3;

    public String getInferenceImage(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf = null;
        String ImgByte64 = null;
        if(itr.hasNext())
        {
            mpf = request.getFile(itr.next());
            String OriginalFilename = mpf.getOriginalFilename();
            System.out.println(OriginalFilename +" uploaded!");
            System.out.println(s3.OriginalUpload(mpf));
            ImgByte64 = SageMaker.predict(mpf);
        }
        return ImgByte64;
    }
}
