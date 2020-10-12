package com.example.test.web;

import com.example.test.model.UserModel;
import com.example.test.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
    @Autowired
    UserService UserService;

    @RequestMapping("/list")
    public String list(Model model) {
        UserModel user = UserService.printUser();
        model.addAttribute("user", user);

        return "list";
    }
}
