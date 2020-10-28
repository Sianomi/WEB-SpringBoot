package com.example.test.controller;

import com.example.test.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {						// 일반적인 요청을 처리하기 위한 HomeController Class

	private final HomeService homeService;			// 요청을 처리하기 위한 HomeService

	@RequestMapping("/login")						// GET Request '/login' path Mapping
	public String login() {							// 로그인 화면 요청을 처리하기 위한 Controller
		return "login"; 							// return login.jsp
	}

	@GetMapping("/")								// GET Request '/' path Mapping
	public String main(Model model) {				// Main 화면 요청을 처리하기 위한 Controller
		homeService.getAuth(model);					// 인증정보 Model 저장
		return "main";								// return main.jsp
	}

	@GetMapping("/infer")							// GET Request '/infer' path Mapping
	public String inferhome(Model model) {			// 추론 페이지 요청을 처리하기 위한 Controller
		return "inference";							// return inference.jsp
	}
}
