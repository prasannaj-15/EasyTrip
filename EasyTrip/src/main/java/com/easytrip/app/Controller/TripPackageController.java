package com.easytrip.app.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Booking;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.TripPackage;
import com.easytrip.app.Service.HotelServices;
import com.easytrip.app.Service.TripPackageServices;


@RestController
public class TripPackageController {

	@Autowired
private TripPackageServices packageService;
	
	@PostMapping("/packages")
	public ResponseEntity<TripPackage> addPackageHandler( @RequestBody TripPackage pack, @RequestParam(required = false) String key){
	
		TripPackage registerdPackage= packageService.addTripPackage(pack,key);
	
		return new ResponseEntity<TripPackage>(registerdPackage, HttpStatus.CREATED);

	}
	
	@GetMapping("/packages/{id}")
	public ResponseEntity<TripPackage> getTripPackageByIdHandler(@PathVariable("id") Integer id){
		
		TripPackage student= packageService.searchTripPackage(id);
		
		return new ResponseEntity<TripPackage>(student, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/packages/{id}")
	public ResponseEntity<TripPackage> deleteTripPackageHandler(@PathVariable("id") Integer id){
		
		TripPackage deletedStudent= packageService.deleteTripPackage(id);
		
		return new ResponseEntity<TripPackage>(deletedStudent, HttpStatus.OK);
	}
	
	@PutMapping("/packages/{bookingId}/{tripPackageId}")
	public ResponseEntity<TripPackage> assignHotelToTripPackageHandler(@PathVariable("bookingId") Integer bookingId,@PathVariable("tripPackageId") Integer tripPackageId){
//		System.out.println(tripPackageId);
		TripPackage tripPackage=packageService.assignBookingToTripPackage(bookingId, tripPackageId);
		return new ResponseEntity<TripPackage>(tripPackage, HttpStatus.OK);
		
	}
	
	
	
	}


