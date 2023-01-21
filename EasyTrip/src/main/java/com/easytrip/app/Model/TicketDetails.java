package com.easytrip.app.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
public class TicketDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ticketId;
	private String ticketStatus;
	
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

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
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

	public TicketDetails(Integer ticketId, String ticketStatus, Set<TripPackage> packageSet, Set<Route> routeSet) {
		super();
		this.ticketId = ticketId;
		this.ticketStatus = ticketStatus;
		this.packageSet = packageSet;
		this.routeSet = routeSet;
	}

	public TicketDetails() {
		super();
	}

	
}
