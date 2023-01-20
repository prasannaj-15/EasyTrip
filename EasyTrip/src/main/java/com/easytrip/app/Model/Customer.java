package com.easytrip.app.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;


@Data
@Entity
public class Customer extends User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String customerName;
	private String customerMobileNo;
	private String customerEmail;
	
	
	@Embedded
	private Address customerAddress;
	
	@OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
	private Set<Booking> bookingSet =new HashSet<>();
	
	@OneToMany(mappedBy="customer" ,cascade=CascadeType.ALL)
	private Set<Feedback> feedbackSet =new HashSet<>();

	
	
}
