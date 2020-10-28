package com.example.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;

import com.example.test.dto.UserDTO;
import com.example.test.service.UserService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class UserController {                                                         // User 정보를 Controll하기 위한 UserController

  private final UserService userService;                                              // 실제 처리를 위한 UserService 변수
  
  @GetMapping("/signup")                                                              // Get Request '/signup' path Mapping
  public String dispSignup() {                                                        // 회원가입 페이지 요청을 처리하기 위한 Controller
    return "/signup";                                                                 // return signup.jsp
  }

  @PostMapping("/signup")                                                             // POST Request '/signup' path Mapping
  @ResponseBody                                                                     
  public String signup(Model model, UserDTO infoDto) throws JsonProcessingException { // 회원가입 요청을 처리하기 위한 Controller
    return userService.save(model, infoDto);
  }

  @GetMapping("/logout")                                                                // GET Request '/logout' path Mapping
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  // 로그아웃을 처리하기 위한 Controller
    new SecurityContextLogoutHandler().logout(request, response,
            SecurityContextHolder.getContext().getAuthentication());
    return "redirect:/login";                                                           // login page Redirect
  }
}
