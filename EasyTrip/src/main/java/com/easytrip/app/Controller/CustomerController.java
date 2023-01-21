package com.easytrip.app.Controller;

import java.util.List;

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

import com.easytrip.app.Model.Customer;
import com.easytrip.app.Service.CustomerServices;

@RestController
public class CustomerController {

    @Autowired
   	private CustomerServices customerService;	
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomerHandler(@RequestBody Customer customer)
	{
		Customer addcustomer = customerService.addCustomer(customer);
		
		return new ResponseEntity<>(addcustomer,HttpStatus.CREATED);
	}
	
	
	
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestBody Customer customer, @RequestParam(required = false) String key)
	{
	   Customer updateCustomer = customerService.updateCustomer(customer,key);
	   
	   return new ResponseEntity<Customer>(updateCustomer,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<Customer> deleteCustomerBycustomerHandler(@PathVariable("id") Integer customerId,@RequestParam(required = false) String key)
	{
		Customer custom = customerService.deleteCustomer(customerId, key);
		return new ResponseEntity<Customer>(custom,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> viewCustomerbyIdHandler(@PathVariable("id") Integer customer_Id, @RequestParam(required = false) String key)
	{
		Customer  custom = customerService.viewCustomer(customer_Id, key);
		
		return new ResponseEntity<Customer>(custom,HttpStatus.OK);
	}
	
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewAllCustomersHandler(@RequestParam(required = false) String key)
	{
		List<Customer>  customers = customerService.viewAllCustomers(key);
		
		return new ResponseEntity<>(customers,HttpStatus.OK);
	}
	
	
	
	
}
