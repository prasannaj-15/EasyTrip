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

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class TripPackage {
	
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
