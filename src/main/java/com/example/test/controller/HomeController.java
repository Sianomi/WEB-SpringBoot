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
		return homeService.getAuth(model);
	}

	@RequestMapping("/admin")
	public String admin()
	{
		return "admin";
	}
}
