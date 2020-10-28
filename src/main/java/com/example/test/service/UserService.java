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
public class UserService implements UserDetailsService {									// 사용자 인증과 관련된 요청을 처리하기 위한 UserSerivce
	
	private final UserRepository userRepository;											// UserDB를 조회하기 위한 userRepository 변수
	private Map<String, String> result = new HashMap<>();									// 요청 결과를 Return하기 위한 변수
	private final Logger logger = LoggerFactory.getLogger(this.getClass());					// System Log를 기록하기 위한 변수

	public String save(Model model, UserDTO infoDto) throws JsonProcessingException {		// 회원가입 요청을 받았을 때 처리하기 위한 함수

		logger.info("Receive Registration Request. Email : " + infoDto.getEID());			// 회원가입 요청을 받았음을 System Log에 기록

		if(userRepository.findByeID(infoDto.getEID()).isPresent()){							// 만일 해당 Email이 이미 있다면
			result.put("msg","중복된 이메일입니다.\n다른 이메일을 사용해주세요.");					// 중복된 Email임을 알리는 내용을 Key "msg"에 저장
			result.put("url","/signup");													// Redirect Path를 Key "url"에 저장
			logger.warn("Already Registered. Email : " + infoDto.getEID());					// System Log에 해당 내용을 기록
		} else {																			// 중복된 Email이 없다면
			logger.info("Try Registration. Email : " + infoDto.getEID());					// 회원가입을 시작함을 System Log에 기록
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();					// 비밀번호 암호화를 위한 변수
			infoDto.setPassword(encoder.encode(infoDto.getPassword()));						// 비밀번호를 암호화하여 비밀번호 변수에 다시 저장함

			userRepository.save(UserDAO.builder()											// UserDB에 저장하는 과정을 수행
				.eID(infoDto.getEID())														// Email
				.auth("ROLE_USER")															// 권한. 기본 권한은 사용자
				.password(infoDto.getPassword())											// 암호화된 Password
				.phonenumber(infoDto.getPhonenumber())										// 핸드폰 번호
				.name(infoDto.getName()).build());											// 사용자 이름
			result.put("msg","가입되셨습니다.\n로그인부탁드립니다.");								// 회원가입 요청을 성공했음을 알리는 메시지를 Key "msg"에 저장
			result.put("url","/login");														// Redirect Path를 Key "url"에 저장
			logger.info("Registration Complete. Email : " + infoDto.getEID());				// 회원가입 성공을 System Log에 저장
		}

		ObjectMapper mapper = new ObjectMapper();											// MAP 변수를 JSON String으로 만들기 위한 mapper 변수
		return mapper.writeValueAsString(result);											// MAP 변수를 JSON String 으로 변환한 뒤 Return
	}

	@Override
	public UserDAO loadUserByUsername(String eID) throws UsernameNotFoundException {		// 로그인 요청을 수행할 때 기본적으로 호출되는 함수
	return userRepository.findByeID(eID).													// Email을 UserDB에서 찾아 해당 결과를 Return
			orElseThrow(() -> new UsernameNotFoundException((eID)));						// 만일 없다면 예외를 발생
	}
}
