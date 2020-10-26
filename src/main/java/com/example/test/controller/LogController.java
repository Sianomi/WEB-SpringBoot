package com.example.test.controller;

import com.example.test.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class LogController {

    private final LogService logService;

    @GetMapping("/log")								// GET Request '/log' path Mapping
    public String log(Model model) {				// Model variable 매개변수
        logService.getLog(model);
        return "log";								// return log.jsp
    }

    @RequestMapping(value="/logs3image", method= RequestMethod.POST)
    @ResponseBody
    public String logS3Image(Model model, String s3path) throws IOException {
        return logService.getS3Image(s3path);
    }
}
