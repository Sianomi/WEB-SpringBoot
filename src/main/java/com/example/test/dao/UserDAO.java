package com.example.test.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity																// DB임을 선언. JPA를 쓰기위한 Annotation
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="userdata")
public class UserDAO implements UserDetails {						// UserDAO Class. JPA를 통해 DB 1:1 연결
	
	@Id                                                             // Primary Key
	@GeneratedValue(strategy= GenerationType.IDENTITY)				// 자동 생성. IDENTITY Type.
	private Long code;												// Primary Key 변수
	
	@Column(unique = true)											// unique Type 선언
	private String eID;												// Email ID

	private String password;										// Password 변수
	private String name;											// 사용자 이름
	private String phonenumber;										// 핸드폰 번호
	private String auth;											// 권한

	@Builder														// UserDAO 변수 생성을 위한 Builder
	public UserDAO(String eID, String password, String name, String phonenumber, String auth) {
	this.eID = eID;
	this.password = password;
	this.name = name;
	this.phonenumber = phonenumber;
	this.auth = auth;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {			// 권한 반환을 위한 함수
	Set<GrantedAuthority> roles = new HashSet<>();
	for (String role : auth.split(",")) {
	  roles.add(new SimpleGrantedAuthority(role));
	}
	return roles;
	}


	@Override
	public String getUsername() {
	return eID;
	}									// 사용자의 id를 반환 (unique한 값)


	@Override
	public String getPassword() {
	return password;
	}							// 사용자의 password를 반환


	@Override
	public boolean isAccountNonExpired() { return true; }						// 계정 만료 여부 반환. 만료되지 않았음.


	@Override
	public boolean isAccountNonLocked() { return true; }						// 계정 잠금 여부 반환. 잠금되지 않았음.


	@Override
	public boolean isCredentialsNonExpired() { return true; } 					// 패스워드의 만료 여부 반환. 만료되지 않았음.


	@Override
	public boolean isEnabled() { return true; }									// 계정 사용 가능 여부 반환. 사용 가능.
}
