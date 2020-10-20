package com.example.test.service;

import com.example.test.dao.UserDAO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class HomeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void getAuth(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if(principal != null && principal instanceof UserDAO) {
            String userName = ((UserDAO)principal).getName();
            String Auth = ((UserDAO)principal).getAuth();
            model.addAttribute("name", userName);
            model.addAttribute("auth", Auth);;
        }
    }
}
