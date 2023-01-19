package com.easytrip.app.Model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Bus {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer busId;
	private String busType;
	private String busNumber;
	private Integer busCapacity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="travelId")
	private Travels travels;	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="routeId")
	private Route route;
}
