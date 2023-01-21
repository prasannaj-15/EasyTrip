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

import com.easytrip.app.Exception.BookingException;
import com.easytrip.app.Model.Booking;
import com.easytrip.app.Model.Customer;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.TripPackage;
import com.easytrip.app.Repository.BookingRepository;
import com.easytrip.app.Service.BookingService;

@RestController
public class BookingController {

	@Autowired
	public BookingService bookingService;
	
	@Autowired
	public BookingRepository br;
	
	
	@PostMapping("/booking")
	public ResponseEntity<Booking> addBookingHandler(@RequestBody Booking book) 
	{
		Booking bookin = bookingService.makeBooking(book);
		 return new ResponseEntity<Booking>(bookin,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/booking/{bookingId}")
	public ResponseEntity<Booking> cancilBookinHandler(@PathVariable("bookingId") Integer bookingId)
	{
		Booking book = bookingService.cancelBooking(br.findById(bookingId).get());
		
		return new ResponseEntity<Booking>(book,HttpStatus.OK);
		
	}
	
	@GetMapping("/booking/{bookingId}")
	public ResponseEntity<Booking> viewBookingHandler(@PathVariable("bookingId") Integer bookingId)
	{
		Booking book = bookingService.viewBooking(bookingId);
		
		return new ResponseEntity<Booking>(book,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
}
