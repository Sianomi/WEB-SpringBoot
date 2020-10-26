package com.example.test.controller;

import com.example.test.service.HomeService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {						// HomeController

	private final HomeService homeService;			// 요청을 처리하기 위한 HomeService

	@RequestMapping("/login")						// GET Request '/login' path Mapping
	public String login() {
		return "login"; 							// return login.jsp
	}

	@GetMapping("/")								// GET Request '/' path Mapping
	public String main(Model model) {				// Model variable 매개변수
		homeService.getAuth(model);					// 인증정보 Model 저장
		return "main";								// return main.jsp
	}

	@GetMapping("/infer")							// GET Request '/infer' path Mapping
	public String inferhome(Model model) {			// Model variable 매개변수
		return "inference";							// return inference.jsp
	}
}
