package com.easytrip.app.Controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easytrip.app.Exception.HotelException;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.TripPackage;
import com.easytrip.app.Service.HotelServices;

@RestController
public class HotelController {
	
	@Autowired
	private HotelServices hotelServices;
	
	@PostMapping("/hotels")
	public ResponseEntity<Hotel> addHotelHandler( @RequestBody Hotel hotel){
		Hotel registerdHotel= hotelServices.addHotel(hotel);
		return new ResponseEntity<Hotel>(registerdHotel, HttpStatus.CREATED);
		}
	
	@GetMapping("/hotels/{id}")
	public ResponseEntity<Hotel> getHotelByIdHandler(@PathVariable("id") Integer id){
		
		Hotel hotel= hotelServices.getHotelById(id);
		
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
		
	}
	
	@PutMapping("/hotels")
	public ResponseEntity<Hotel> updateHotelHandler( @RequestBody Hotel hotel){
		Hotel registerdHotel= hotelServices.updateHotel(hotel);
		return new ResponseEntity<Hotel>(registerdHotel, HttpStatus.CREATED);
		}
	
	@DeleteMapping("/hotels/{id}")
	public ResponseEntity<Hotel> deleteHotelByIdHandler(@PathVariable("id") Integer id){
		
		Hotel hotel= hotelServices.deleteHotelById(id);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
		
	}
	
	@PutMapping("/hotels/{hotelId}/{tripPackageId}")
	public ResponseEntity<Hotel> assignHotelToTripPackageHandler(@PathVariable("hotelId") Integer hotelId,@PathVariable("tripPackageId") Integer tripPackageId){
//		System.out.println(tripPackageId);
		Hotel hotel= hotelServices.assignHotelToTripPackage(hotelId, tripPackageId);
		return new ResponseEntity<Hotel>(hotel, HttpStatus.OK);
		
	}
	
	@GetMapping("/allhotels/{id}")
	public ResponseEntity<Set<Hotel>> getallHotelByPackageIdHandler(@PathVariable("id") Integer id){
		
		Set<Hotel> hotel= hotelServices.getHotelsByPackageId(id);
		
		return new ResponseEntity<Set<Hotel>>(hotel, HttpStatus.OK);
		
	}
	
}
