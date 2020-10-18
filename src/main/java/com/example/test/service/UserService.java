package com.example.test.service;

import com.example.test.repository.UserRepository;
import com.example.test.dto.UserDTO;
import com.example.test.dao.UserDAO;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;

	public String save(UserDTO infoDto) {
		if(userRepository.findByeID(infoDto.getEID())!=null){
			return "redirect:/signup";
		}
		else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			infoDto.setPassword(encoder.encode(infoDto.getPassword()));

			userRepository.save(UserDAO.builder()
				.eID(infoDto.getEID())
				.auth("ROLE_USER")
				.password(infoDto.getPassword())
				.phonenumber(infoDto.getPhonenumber())
				.name(infoDto.getName()).build()).getCode();
			return "redirect:/login";
		}
	}


	@Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 User로 반환 타입 지정 (자동으로 다운 캐스팅됨)
		public UserDAO loadUserByUsername(String eID) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
		return userRepository.findByeID(eID).orElseThrow(() -> new UsernameNotFoundException((eID)));
	}
}
