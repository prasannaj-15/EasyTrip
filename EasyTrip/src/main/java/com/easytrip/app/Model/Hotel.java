package com.easytrip.app.Model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity

public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer hotelId;
	private String hotelType;
	private String hotelDescription;
	private String hotelName;
	private	Double hotelRent;
	private String hotelStatus;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="packageId")
	private TripPackage tripPackage;

	public Hotel(Integer hotelId, String hotelType, String hotelDescription, String hotelName, Double hotelRent,
			String hotelStatus, TripPackage tripPackage) {
		super();
		this.hotelId = hotelId;
		this.hotelType = hotelType;
		this.hotelDescription = hotelDescription;
		this.hotelName = hotelName;
		this.hotelRent = hotelRent;
		this.hotelStatus = hotelStatus;
		this.tripPackage = tripPackage;
	}

	public Hotel() {
		super();
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelType() {
		return hotelType;
	}

	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}

	public String getHotelDescription() {
		return hotelDescription;
	}

	public void setHotelDescription(String hotelDescription) {
		this.hotelDescription = hotelDescription;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public Double getHotelRent() {
		return hotelRent;
	}

	public void setHotelRent(Double hotelRent) {
		this.hotelRent = hotelRent;
	}

	public String getHotelStatus() {
		return hotelStatus;
	}

	public void setHotelStatus(String hotelStatus) {
		this.hotelStatus = hotelStatus;
	}

	public TripPackage getTripPackage() {
		return tripPackage;
	}

	public void setTripPackage(TripPackage tripPackage) {
		this.tripPackage = tripPackage;
	}
	
}
