package com.example.test.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class UserModel implements UserDetails {
	
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
	  public UserModel(String eID, String password, String name, String phonenumber, String auth) {
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
	    // 만료되었는지 확인하는 로직
	  return true; // true -> 만료되지 않았음
	  }
	
	  // 계정 잠금 여부 반환
	  @Override
	  public boolean isAccountNonLocked() {
	    // 계정 잠금되었는지 확인하는 로직
	  return true; // true -> 잠금되지 않았음
	  }
	
	  // 패스워드의 만료 여부 반환
	  @Override
	  public boolean isCredentialsNonExpired() {
	    // 패스워드가 만료되었는지 확인하는 로직
	  return true; // true -> 만료되지 않았음
	  }
	
	  // 계정 사용 가능 여부 반환
	  @Override
	  public boolean isEnabled() {
	    // 계정이 사용 가능한지 확인하는 로직
	  return true; // true -> 사용 가능
	  }
}