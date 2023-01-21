package com.easytrip.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Exception.CustomerException;
import com.easytrip.app.Model.CurrentUserSession;
import com.easytrip.app.Model.Customer;
import com.easytrip.app.Repository.AdminRepository;
import com.easytrip.app.Repository.CustomerRepository;
import com.easytrip.app.Repository.SessionRepository;
 
@Service
public class CustomerServiceImpl implements CustomerServices {
    
	@Autowired
	private CustomerRepository cr;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {

		  Customer cus = cr.findByUserEmail(customer.getUserEmail());
		  if(cus == null) {
			  Customer addcustomer = cr.save(customer);
			    
			   return addcustomer;
			   	
		  }
		  else {
			  throw new CustomerException("Customer is not added");
		  }	 
		
		
	}
	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {

		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(customer.getCustomerId() == loggedInUser.getUserId() || loggedInUser.getUserType().equals("Admin")) {
		
		 Optional<Customer> opt = cr.findById(customer.getCustomerId());
			
			if(opt.isPresent())
			{
				Customer existCustomer = opt.get();
				
				return cr.save(customer);
				
			}
			else {
				throw new CustomerException("Customer not found..");
			}
			
		}
		else 
			throw new CustomerException("Access Denied !");
	}

	@Override
	public Customer deleteCustomer(Integer customerId, String key) throws CustomerException {


		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(customerId == loggedInUser.getUserId() || loggedInUser.getUserType().equals("Admin")) {
		
		
			Optional<Customer> opt = cr.findById(customerId);
			 if(opt.isPresent())
			 {
				  Customer cust = opt.get();
				 cr.delete(cust);
				 sessionRepo.delete(loggedInUser);
				 return cust;
			 }
			 else 
				 throw new CustomerException("Customer not found");
			 
		}
		else 
			throw new CustomerException("Access Denied !");
	}

	@Override
	public Customer viewCustomer(Integer customerId, String key) throws CustomerException,AdminException {

		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(customerId == loggedInUser.getUserId() || loggedInUser.getUserType().equals("Admin")) {
		
		
			Optional<Customer> opt = cr.findById(customerId);
		
			if(opt.isPresent())
			{
				return opt.get();
			}
			else 
				throw new CustomerException("Invalid Customer details. Please check the details");
				
		}
		else 
			throw new CustomerException("Access Denied !");
		
	}
	
	
	@Override
	public List<Customer> viewAllCustomers(String key) throws CustomerException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {
			
		List<Customer> customers = cr.findAll();
		
		if(customers.isEmpty())
			throw new CustomerException("No customer is present in database.");
		
		return customers;
		}
		
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
	}
		
}

	

