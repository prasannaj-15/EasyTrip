package com.easytrip.app.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
public class TicketDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ticketId;
	private Double totalTicketCost;
	
	@Enumerated(value = EnumType.STRING)
	private Status ticketStatus;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "ticket")
	private Set<TripPackage> packageSet = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ticketDetails")	
	private Set<Route> routeSet = new HashSet<>();
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="bookingID")
	@JsonIgnore
	private Booking booking;
	


	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}



	public Status getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(Status ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Set<TripPackage> getPackageSet() {
		return packageSet;
	}

	public void setPackageSet(Set<TripPackage> packageSet) {
		this.packageSet = packageSet;
	}

	public Set<Route> getRouteSet() {
		return routeSet;
	}

	public void setRouteSet(Set<Route> routeSet) {
		this.routeSet = routeSet;
	}

	

	

	public Double getTotalTicketCost() {
		return totalTicketCost;
	}

	public void setTotalTicketCost(Double totalTicketCost) {
		this.totalTicketCost = totalTicketCost;
	}

	public TicketDetails(Integer ticketId, Double totalTicketCost, Status ticketStatus, Set<TripPackage> packageSet,
			Set<Route> routeSet, Booking booking) {
		super();
		this.ticketId = ticketId;
		this.totalTicketCost = totalTicketCost;
		this.ticketStatus = ticketStatus;
		this.packageSet = packageSet;
		this.routeSet = routeSet;
		this.booking = booking;
	}

	public TicketDetails() {
		super();
	}

	
}
