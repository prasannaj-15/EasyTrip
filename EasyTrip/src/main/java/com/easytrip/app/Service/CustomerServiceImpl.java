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
	public Customer updateCustomer(Customer customer) throws CustomerException {

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

	@Override
	public Customer deleteCustomer(Customer customer) throws CustomerException {


		Optional<Customer> opt = cr.findById(customer.getCustomerId());
		 if(opt.isPresent())
		 {
			  Customer cust = opt.get();
			 cr.delete(customer);
			 return customer;
		 }
		 else {
			 throw new CustomerException("Customer not found");
		 }		
	}

	@Override
	public Customer viewCustomer(Integer Customer_Id) throws CustomerException,AdminException {


		Optional<Customer> opt = cr.findById(Customer_Id);
	
		if(opt.isPresent())
		{
			return opt.get();
		}
		else {
			throw new CustomerException("Invalid Customer details. Please check teh details");
		}		
	}
	@Override
	public List<Customer> viewAllCustomers(String key) throws CustomerException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Please provide a valid key to update a customer");
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

	

