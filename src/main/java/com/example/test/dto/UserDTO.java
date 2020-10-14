package com.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	  private String eID;
	  private String password;
	  private String name;
	  private String phonenumber;
	  private String auth;

	public UserDTO() {
	}

	public UserDTO(String eID, String password, String name, String phonenumber, String auth) {
		this.eID = eID;
		this.password = password;
		this.name = name;
		this.phonenumber = phonenumber;
		this.auth = auth;
	}
}
