package com.easytrip.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easytrip.app.Model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	
	public Admin findByUserEmail(String email); 
	
}
