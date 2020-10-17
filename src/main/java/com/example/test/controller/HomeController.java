package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import com.example.test.dao.UserDAO;

@Controller
public class HomeController {
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/")
	public String main(Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String name = "";
		if(principal != null && principal instanceof UserDAO){
			model.addAttribute("name", ((UserDAO)principal).getName());
			model.addAttribute("auth", ((UserDAO)principal).getAuth());
		}
		return "main";
	}

	@RequestMapping("/admin")
	public String admin()
	{
		return "admin";
	}
}
