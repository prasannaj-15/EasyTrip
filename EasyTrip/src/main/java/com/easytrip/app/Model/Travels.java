package com.easytrip.app.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
//@Data
public class Travels {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer travelId;
	private String travelName;
	private String agentName;
	private String contactNumber;
	
	@OneToMany(mappedBy ="travels",cascade = CascadeType.ALL)
	private Set<Bus> busSet=new HashSet<>();

	public Travels(Integer travelId, String travelName, String agentName, String contactNumber, Set<Bus> busSet) {
		super();
		this.travelId = travelId;
		this.travelName = travelName;
		this.agentName = agentName;
		this.contactNumber = contactNumber;
		this.busSet = busSet;
	}

	public Travels() {
		super();
	}

	public Integer getTravelId() {
		return travelId;
	}

	public void setTravelId(Integer travelId) {
		this.travelId = travelId;
	}

	public String getTravelName() {
		return travelName;
	}

	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Set<Bus> getBusSet() {
		return busSet;
	}

	public void setBusSet(Set<Bus> busSet) {
		this.busSet = busSet;
	}

}
