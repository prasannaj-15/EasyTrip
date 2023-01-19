package com.easytrip.app.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Route {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer routeId;
	private String routeFrom;
	private String routeTo;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private LocalDate dateOfJourney;
	private String pickupPoint;
	private double fare;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "route")
	private Set<Bus> busses= new HashSet<>();
}
