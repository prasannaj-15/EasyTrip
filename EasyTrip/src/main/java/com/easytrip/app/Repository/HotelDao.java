package com.easytrip.app.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.Status;
import com.easytrip.app.Model.TripPackage;

@Repository
public interface HotelDao extends JpaRepository<Hotel,Integer> {

	public Hotel findByHotelName(String hotelName);
	
	
//	public Set<Hotel> findByHotelStatus(Status status);

	@Query("from Hotel h where h.hotelStatus = ?1")
	public Set<Hotel> getAllHotelByStatus(Status available);
	
	
}
