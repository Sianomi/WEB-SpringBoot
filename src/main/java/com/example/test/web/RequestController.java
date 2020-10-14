package com.example.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {

    @GetMapping("/upload")
    public String GetUpload()
    {
        return "imageTest";
    }

    @PostMapping("/upload")
    @ResponseBody
    public void upload(MultipartHttpServletRequest request)
    {
        Iterator<String> itr = request.getFileNames();
        if(itr.hasNext())
        {
            MultipartFile mpf = request.getFile(itr.next());
            System.out.println(mpf.getOriginalFilename() +" uploaded!");
            try
            { //just temporary save file info into ufile
                System.out.println("file length : " + mpf.getBytes().length);
                System.out.println("file name : " + mpf.getOriginalFilename());
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
