package com.easytrip.app.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Package {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer packageId;
private String packageName;
private String packageDescription;
private String packageType;
private Double packageCost;


@ManyToOne(cascade = CascadeType.ALL)
private Booking booking; 


@OneToMany(mappedBy="tripPackage",cascade = CascadeType.ALL)
private Set<Hotel> hotelSet = new HashSet<>(); 

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "ticket_id")
private TicketDetails ticket; 



}
