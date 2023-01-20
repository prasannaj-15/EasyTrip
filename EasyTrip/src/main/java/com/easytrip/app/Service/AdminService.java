package com.easytrip.app.Service;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Model.Admin;

public interface AdminService {

	public Admin createAdmin(Admin admin) throws AdminException;
	
	public Admin getAdminDetails(Integer adminId) throws AdminException;
	
	public Admin updateAdmin(Admin admin) throws AdminException;
	
	public Admin deleteAdmin(Integer adminId) throws AdminException; 
	
}