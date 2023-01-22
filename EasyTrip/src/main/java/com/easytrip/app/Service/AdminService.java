package com.easytrip.app.Service;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Model.Admin;

public interface AdminService {

	public Admin createAdmin(Admin admin) throws AdminException;
	
	public Admin getAdminDetails(Integer adminId, String key) throws AdminException;
	
	public Admin updateAdmin(Admin admin,String key) throws AdminException;
	
	public Admin deleteAdmin(Integer adminId,String key) throws AdminException; 
	
}
