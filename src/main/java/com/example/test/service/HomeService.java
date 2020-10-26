package com.example.test.service;

import com.example.test.dao.InferLogDAO;
import com.example.test.dao.UserDAO;
import com.example.test.repository.InferLogRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final InferLogRepository inferLogRepository;

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

    public void getLog(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDAO principal = (UserDAO) auth.getPrincipal();

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("timestamp").descending());
        if(principal.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            List<InferLogDAO> test = inferLogRepository.findAll(pageRequest).getContent();
            model.addAttribute("logList", test);
        } else {
            List<InferLogDAO> test = inferLogRepository.findByeID(principal.getEID(), pageRequest).getContent();
            model.addAttribute("logList", test);
        }
    }
}
