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

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="userdata")
public class UserDAO implements UserDetails {
	
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long code;
	
	@Column(name = "eID", unique = true)
	private String eID;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phonenumber")
	private String phonenumber;
	
	@Column(name = "auth")
	private String auth;

	@Builder
	public UserDAO(String eID, String password, String name, String phonenumber, String auth) {
	this.eID = eID;
	this.password = password;
	this.name = name;
	this.phonenumber = phonenumber;
	this.auth = auth;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	Set<GrantedAuthority> roles = new HashSet<>();
	for (String role : auth.split(",")) {
	  roles.add(new SimpleGrantedAuthority(role));
	}
	return roles;
	}

	// 사용자의 id를 반환 (unique한 값)
	@Override
	public String getUsername() {
	return eID;
	}

	// 사용자의 password를 반환
	@Override
	public String getPassword() {
	return password;
	}

	// 계정 만료 여부 반환
	@Override
	public boolean isAccountNonExpired() {
	return true; // true -> 만료되지 않았음
	}

	// 계정 잠금 여부 반환
	@Override
	public boolean isAccountNonLocked() {
	return true; // true -> 잠금되지 않았음
	}

	// 패스워드의 만료 여부 반환
	@Override
	public boolean isCredentialsNonExpired() {
	return true; // true -> 만료되지 않았음
	}

	// 계정 사용 가능 여부 반환
	@Override
	public boolean isEnabled() {
	return true; // true -> 사용 가능
	}
}
