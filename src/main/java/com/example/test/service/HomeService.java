package com.example.test.service;

import com.example.test.dao.UserDAO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class HomeService {                                                  // 일반적인 요청을 처리하기 위한 HomeService Class

    public void getAuth(Model model) {                                      // Login User 정보를 View에 표시하기 위한 함수
        Authentication auth = SecurityContextHolder.                        // Login User의 인증정보를 불러옴
                getContext().getAuthentication();
        Object principal = auth.getPrincipal();                             // 불러온 인증정보에서 User 정보만을 불러옴
        if(principal != null && principal instanceof UserDAO) {             // 만일 User 정보가 null이 아니고 UserDAO 형태의 변수라면
            model.addAttribute("name",                          // Attribute Key "name" Value UserName 생성
                    ((UserDAO)principal).getName());
        }
    }
}
