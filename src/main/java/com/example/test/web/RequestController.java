package com.example.test.web;

import com.example.test.aws.sagemaker.endpoint;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
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

            ImgByte64 = endpoint.predict(mpf);
        }
        return ImgByte64;
    }
}
