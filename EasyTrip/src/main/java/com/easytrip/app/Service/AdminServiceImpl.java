package com.easytrip.app.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Model.Admin;
import com.easytrip.app.Model.CurrentUserSession;
import com.easytrip.app.Repository.AdminRepository;
import com.easytrip.app.Repository.SessionRepository;


@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	
	@Override
	public Admin createAdmin(Admin admin) throws AdminException {
		
		Admin presentAdmin = adminRepo.findByUserEmail(admin.getUserEmail());
		
		
		if(presentAdmin == null) {
			return adminRepo.save(admin);
			
			  } else throw new
			  AdminException("Admin Already present with email : "+admin.getUserEmail());
			 
	}


	@Override
	public Admin updateAdmin(Admin admin,String key) throws AdminException {

		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Please provide a valid key to update a admin");
		}
		
		if(admin.getAdminId() == loggedInUser.getUserId()) {
			
		Admin loggedAdmin = adminRepo.findById(admin.getAdminId()).orElseThrow(()-> new AdminException("Admin not Log In. please Log In first."));
		
		admin.setAdminId(loggedAdmin.getAdminId());
		
		return adminRepo.save(admin);
		
		}
		else
			throw new AdminException("Invalid Customer Details, please login first");
	}


	@Override
	public Admin getAdminDetails(Integer adminId) throws AdminException {

		Admin admin = adminRepo.findById(adminId).orElseThrow(()-> new AdminException("Admin not present with AdminId : "+adminId));
		

		return admin;
	}


	@Override
	public Admin deleteAdmin(Integer adminId) throws AdminException {

		Admin admin = adminRepo.findById(adminId).orElseThrow(()-> new AdminException("Admin not present with AdminId : "+adminId));
		
		adminRepo.deleteById(adminId);
		
		return admin; 
	}
	
	
}
