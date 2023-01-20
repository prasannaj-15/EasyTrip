package com.easytrip.app.Model;

import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class User {

	private String userName;
	private String userMobileNo;
	private String userEmail;
	private String password;
//	private String sessionId;
	
	
}
