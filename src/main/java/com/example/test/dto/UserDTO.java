package com.example.test.dto;

import lombok.Data;

@Data
public class UserDTO {					// View Controller User 정보 교환을 위한 DTO
	  private String eID;				// Email ID
	  private String password;			// Password
	  private String name;				// 사용자 이름
	  private String phonenumber;		// 핸드폰 번호
	  private String auth;				// 권한
}
