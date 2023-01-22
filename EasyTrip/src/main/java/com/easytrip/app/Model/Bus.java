package com.easytrip.app.Model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
public class Bus {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer busId;
	private String busType;
	private String busNumber;
	private Integer busCapacity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="travelId")
	@JsonIgnore
	private Travels travels;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="routeId")
	private Route route;

	
	public Bus(Integer busId, String busType, String busNumber, Integer busCapacity, Travels travels, Route route) {
		super();
		this.busId = busId;
		this.busType = busType;
		this.busNumber = busNumber;
		this.busCapacity = busCapacity;
		this.travels = travels;
		this.route = route;
	}

	public Bus() {
		super();
	}



	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public Integer getBusCapacity() {
		return busCapacity;
	}

	public void setBusCapacity(Integer busCapacity) {
		this.busCapacity = busCapacity;
	}

	public Travels getTravels() {
		return travels;
	}

	public void setTravels(Travels travels) {
		this.travels = travels;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	
}
