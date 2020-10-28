package com.example.test.controller;

import com.example.test.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class LogController {                                                        // log와 관련된 요청을 처리하기 위한 LogController Class

    private final LogService logService;                                            // Log와 관련된 처리를 하기위한 Service 변수

    @GetMapping("/log")								                                // GET Request '/log' path Mapping
    public String log(Model model) {				                                // 사용 기록 조회 요청을 했을 때 호출되는 Controller
        logService.getLog(model);                                                   // logService의 사용기록 조회를 요청하는 함수를 호출
        return "log";								                                // return log.jsp
    }

    @PostMapping("/logs3image")                                                     // Post Request '/logs3image' path Mapping
    @ResponseBody
    public String logS3Image(Model model, String s3path) throws IOException {       // 사용 기록에서 Image 조회를 요청했을 때 호출되는 Controller
        return logService.getS3Image(s3path);                                       // 요청받은 Image를 불러오는 logService 함수 호출
    }
}
