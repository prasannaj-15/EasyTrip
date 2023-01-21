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
import javax.persistence.OneToOne;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Set<TripPackage> packageSet = new HashSet<>() ;
	
	@OneToOne(cascade = CascadeType.ALL)
	private TicketDetails ticketDetails;

	public Booking(Integer bookingId, String bookingType, String bookingDescription, String bookingTitle,
			@Past(message = "date can't be empty") LocalDate bookingDate, Customer customer,
			Set<TripPackage> packageSet, TicketDetails ticketDetails) {
		super();
		this.bookingId = bookingId;
		this.bookingType = bookingType;
		this.bookingDescription = bookingDescription;
		this.bookingTitle = bookingTitle;
		this.bookingDate = bookingDate;
		this.customer = customer;
		this.packageSet = packageSet;
		this.ticketDetails = ticketDetails;
	}

	public Booking() {
		super();
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public String getBookingDescription() {
		return bookingDescription;
	}

	public void setBookingDescription(String bookingDescription) {
		this.bookingDescription = bookingDescription;
	}

	public String getBookingTitle() {
		return bookingTitle;
	}

	public void setBookingTitle(String bookingTitle) {
		this.bookingTitle = bookingTitle;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<TripPackage> getPackageSet() {
		return packageSet;
	}

	public void setPackageSet(Set<TripPackage> packageSet) {
		this.packageSet = packageSet;
	}

	public TicketDetails getTicketDetails() {
		return ticketDetails;
	}

	public void setTicketDetails(TicketDetails ticketDetails) {
		this.ticketDetails = ticketDetails;
	}
	
	
	

}
