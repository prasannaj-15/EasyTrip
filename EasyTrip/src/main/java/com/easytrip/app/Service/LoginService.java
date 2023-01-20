package com.easytrip.app.Service;

import com.easytrip.app.Exception.LoginException;
import com.easytrip.app.Model.LoginDTO;

public interface LoginService {

	public String logInToAccount(LoginDTO loginDto) throws LoginException;
	
	public String logOutFromAccount(String key) throws LoginException;
	
}
