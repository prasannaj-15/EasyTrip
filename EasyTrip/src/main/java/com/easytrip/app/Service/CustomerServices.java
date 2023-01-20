package com.easytrip.app.Service;

import com.easytrip.app.Exception.CustomerException;
import com.easytrip.app.Model.Customer;

public interface CustomerServices {
   
	public Customer addCustomer(Customer customer) throws CustomerException;
	
   public Customer updateCustomer(Customer customer) throws CustomerException;
	
	public Customer deleteCustomer(Customer customer) throws CustomerException;
	
	public Customer viewCustomer(Integer Customer_Id) throws CustomerException;


}
