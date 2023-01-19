package com.easytrip.app.Model;

import javax.persistence.Entity;

import lombok.Data;

//@Entity
@Data
public class Admin extends User {

	private Integer adminId;
	private String adminName;
	private String mobileNo;
	private String Email; 
	
}
