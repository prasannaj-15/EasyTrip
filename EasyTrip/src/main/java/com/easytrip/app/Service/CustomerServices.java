package com.easytrip.app.Service;

import java.util.List;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Exception.CustomerException;
import com.easytrip.app.Model.Customer;

public interface CustomerServices {
   
	public Customer addCustomer(Customer customer) throws CustomerException;
	
	public Customer updateCustomer(Customer customer,String key) throws CustomerException;
	
	public Customer deleteCustomer(Integer customerId,String key) throws CustomerException;
	
	public Customer viewCustomer(Integer customerId, String key) throws CustomerException;

	//Admin
	public List<Customer> viewAllCustomers(String key) throws CustomerException,AdminException;
	
}
