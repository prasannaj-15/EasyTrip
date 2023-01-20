package com.easytrip.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.TripPackage;

@Repository
public interface HotelDao extends JpaRepository<Hotel,Integer> {

	public Hotel findByHotelName(String hotelName);
	
	
	
	
}
