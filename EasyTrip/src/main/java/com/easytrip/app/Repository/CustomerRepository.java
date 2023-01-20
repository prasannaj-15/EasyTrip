package com.easytrip.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easytrip.app.Model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

	 public Customer findByUserEmail(String email);
}
