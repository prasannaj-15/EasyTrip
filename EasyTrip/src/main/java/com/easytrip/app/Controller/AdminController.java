package com.easytrip.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easytrip.app.Model.Admin;
import com.easytrip.app.Service.AdminService;


@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	
	@PostMapping("/admin")
	public ResponseEntity<Admin> createAdminHandler(@RequestBody Admin admin){

		
		Admin savedAdmin = adminService.createAdmin(admin);
		
		return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/admin")
	public ResponseEntity<Admin> updateAdminHandler(@RequestBody Admin admin,@RequestParam(required = false) String key){

		
		Admin updatedAdmin = adminService.updateAdmin(admin,key);
		
		return new ResponseEntity<>(updatedAdmin, HttpStatus.CREATED);
	}
	
	@GetMapping("/admin/{adminId}")
	public ResponseEntity<Admin> getAdminDetailsHandler(@PathVariable("adminId") Integer adminId){

		
		Admin admin = adminService.getAdminDetails(adminId);
		
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/{adminId}")
	public ResponseEntity<Admin> deleteAdminHandler(@PathVariable("adminId") Integer adminId){

		
		Admin deletedAdmin = adminService.deleteAdmin(adminId);
		
		return new ResponseEntity<>(deletedAdmin, HttpStatus.OK);
	}
	
}
