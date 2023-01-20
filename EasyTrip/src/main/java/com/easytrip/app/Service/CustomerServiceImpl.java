package com.easytrip.app.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.CustomerException;
import com.easytrip.app.Model.Customer;
import com.easytrip.app.Repository.CustomerRepository;
 
@Service
public class CustomerServiceImpl implements CustomerServices {
    
	@Autowired
	private CustomerRepository cr;
	
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
	public Customer viewCustomer(Integer Customer_Id) throws CustomerException {


		Optional<Customer> opt = cr.findById(Customer_Id);
	
		if(opt.isPresent())
		{
			return opt.get();
		}
		else {
			throw new CustomerException("Invalid Customer details. Please check teh details");
		}		
	}
		
	}

	

