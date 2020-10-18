package com.example.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import com.example.test.dto.UserDTO;
import com.example.test.service.UserService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

  private final UserService userService;
  
  @GetMapping("/signup")
  public String dispSignup() {
      return "/signup";
  }

  @PostMapping("/signup")
  public String signup(UserDTO infoDto) { // 회원 추가
    return userService.save(infoDto);
  }

  @GetMapping("/logout")
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
    return "redirect:/login";
  }
}
