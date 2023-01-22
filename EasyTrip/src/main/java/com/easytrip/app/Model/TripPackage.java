package com.easytrip.app.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
public class TripPackage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer packageId;
private String packageName;
private String packageDescription;
private String packageType;
private Double packageCost;

@JsonIgnore
@ManyToOne(cascade = CascadeType.ALL)
private Booking booking; 


@OneToMany(mappedBy="tripPackage",cascade = CascadeType.ALL)
private Set<Hotel> hotelSet = new HashSet<>(); 

@JsonIgnore
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "ticket_id")
private TicketDetails ticket;

public Integer getPackageId() {
	return packageId;
}

public void setPackageId(Integer packageId) {
	this.packageId = packageId;
}

public String getPackageName() {
	return packageName;
}

public void setPackageName(String packageName) {
	this.packageName = packageName;
}

public String getPackageDescription() {
	return packageDescription;
}

public void setPackageDescription(String packageDescription) {
	this.packageDescription = packageDescription;
}

public String getPackageType() {
	return packageType;
}

public void setPackageType(String packageType) {
	this.packageType = packageType;
}

public Double getPackageCost() {
	return packageCost;
}

public void setPackageCost(Double packageCost) {
	this.packageCost = packageCost;
}

public Booking getBooking() {
	return booking;
}

public void setBooking(Booking booking) {
	this.booking = booking;
}

public Set<Hotel> getHotelSet() {
	return hotelSet;
}

public void setHotelSet(Set<Hotel> hotelSet) {
	this.hotelSet = hotelSet;
}

public TicketDetails getTicket() {
	return ticket;
}

public void setTicket(TicketDetails ticket) {
	this.ticket = ticket;
}

public TripPackage() {
	super();
}


public TripPackage(Integer packageId, String packageName, String packageDescription, String packageType,
		Double packageCost, Booking booking, Set<Hotel> hotelSet, TicketDetails ticket) {
	super();
	this.packageId = packageId;
	this.packageName = packageName;
	this.packageDescription = packageDescription;
	this.packageType = packageType;
	this.packageCost = packageCost;
	this.booking = booking;
	this.hotelSet = hotelSet;
	this.ticket = ticket;
} 





}
