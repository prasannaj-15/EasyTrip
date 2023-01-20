package com.easytrip.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easytrip.app.Model.Admin;
import com.easytrip.app.Model.LoginDTO;
import com.easytrip.app.Service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUserHandler(@RequestBody LoginDTO loginDto){

		
		String result = loginService.logInToAccount(loginDto);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@PostMapping("/logout")
	public ResponseEntity<String> logOutUserHandler(@RequestParam(required = false) String key){

		
		String result = loginService.logOutFromAccount(key);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
