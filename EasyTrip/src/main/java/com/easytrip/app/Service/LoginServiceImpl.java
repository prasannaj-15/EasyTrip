package com.easytrip.app.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.LoginException;
import com.easytrip.app.Model.Admin;
import com.easytrip.app.Model.CurrentUserSession;
import com.easytrip.app.Model.LoginDTO;
import com.easytrip.app.Repository.AdminRepository;
import com.easytrip.app.Repository.SessionRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	
	@Override
	public String logInToAccount(LoginDTO loginDto) throws LoginException {


		Admin existingAdmin = adminRepo.findByUserEmail(loginDto.getUserEmail());
		
		if(existingAdmin == null) {
			throw new LoginException("please enter valid email address.");
		}
		
		Optional<CurrentUserSession> validCustomerSessionOpt = sessionRepo.findById(existingAdmin.getAdminId());
		
		if(validCustomerSessionOpt.isPresent()) {
			throw new LoginException("User already logged In with this email");
		}
		
		if(existingAdmin.getPassword().equals(loginDto.getPassword())) {
			
			String key = RandomString.make(6);
			
			CurrentUserSession currentUserSession = new CurrentUserSession(existingAdmin.getAdminId(),key,LocalDateTime.now());
			
			sessionRepo.save(currentUserSession);
			
			return currentUserSession.toString();
			
		}
		else
			throw new LoginException("please enter valid password");
		
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {

		CurrentUserSession validUserSession = sessionRepo.findByUuid(key);
		
		if(validUserSession == null) {
			throw new LoginException("User not logged In with this email");
		}
		
		sessionRepo.delete(validUserSession);
		
		return "Logged Out !";
	}

	
}
