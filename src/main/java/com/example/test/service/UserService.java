package com.example.test.service;

import com.example.test.repository.UserRepository;
import com.example.test.dto.UserDTO;
import com.example.test.dao.UserDAO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;
	private Map<String, String> result = new HashMap<>();
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String save(Model model, UserDTO infoDto) throws JsonProcessingException {

		logger.info("Receive Registration Request. Email : " + infoDto.getEID());

		if(userRepository.findByeID(infoDto.getEID()).isPresent()){
			result.put("msg","중복된 이메일입니다.\n다른 이메일을 사용해주세요.");
			result.put("url","/signup");
			logger.warn("Already Registered. Email : " + infoDto.getEID());
		}

		else {
			logger.info("Try Registration. Email : " + infoDto.getEID());
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			infoDto.setPassword(encoder.encode(infoDto.getPassword()));

			userRepository.save(UserDAO.builder()
				.eID(infoDto.getEID())
				.auth("ROLE_USER")
				.password(infoDto.getPassword())
				.phonenumber(infoDto.getPhonenumber())
				.name(infoDto.getName()).build()).getCode();
			result.put("msg","가입되셨습니다.\n로그인부탁드립니다.");
			result.put("url","/login");
			logger.info("Registration Complete. Email : " + infoDto.getEID());
		}

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(result);
	}

	@Override
	public UserDAO loadUserByUsername(String eID) throws UsernameNotFoundException {
	return userRepository.findByeID(eID).orElseThrow(() -> new UsernameNotFoundException((eID)));
	}
}
