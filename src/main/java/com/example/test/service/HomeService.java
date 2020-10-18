package com.example.test.service;

import com.example.test.dao.UserDAO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class HomeService {
    public String getAuth(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        String name = "";
        if(principal != null && principal instanceof UserDAO){
            model.addAttribute("name", ((UserDAO)principal).getName());
            model.addAttribute("auth", ((UserDAO)principal).getAuth());
        }
        return "main";
    }
}
