package com.easytrip.app.Model;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class User {

	private String userType;
	private String password;
//	private String sessionId;
	
	
}
