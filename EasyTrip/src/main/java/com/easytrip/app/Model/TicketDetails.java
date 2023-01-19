package com.easytrip.app.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class TicketDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ticketId;
	private String ticketStatus;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "ticket")
	private Set<Package> packageSet = new HashSet<>();
	
	

}
