package com.easytrip.app.Model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;

import lombok.Data;

@Data
@Entity
public class Booking {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;
	private String bookingType;
	private String bookingDescription;
	private String bookingTitle;
	@Past(message = "date can't be empty")
	private	LocalDate bookingDate;
	
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="customerId")
	private Customer customer  ;
	
	
	@OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
	private Set<Package> packageSet = new HashSet<>() ;
	
	
	

}
