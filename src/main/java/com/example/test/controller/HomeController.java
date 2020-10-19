package com.example.test.controller;

import com.example.test.service.HomeService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {

	private final HomeService homeService;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/")
	public String main(Model model) {
		homeService.getAuth(model);
		return "main";
	}

	@RequestMapping("/infer")
	public String inferhome(Model model) {
		homeService.getAuth(model);
		return "inference";
	}

	@RequestMapping("/log")
	public String log(Model model) {
		homeService.getAuth(model);
		return "log";
	}
}
