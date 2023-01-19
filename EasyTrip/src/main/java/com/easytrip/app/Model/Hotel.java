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

import lombok.Data;

@Entity
@Data
public class Hotel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer hotelId;
	private String hotelType;
	private String hotelDescription;
	private String hotelName;
	private	Double hotelRent;
	private String hotelStatus;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="packageId")
	private Package tripPackage;
	
}
