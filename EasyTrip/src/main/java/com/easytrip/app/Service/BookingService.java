package com.easytrip.app.Service;

import java.util.Set;

import com.easytrip.app.Exception.BookingException;
import com.easytrip.app.Exception.HotelException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Booking;
import com.easytrip.app.Model.Customer;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.TripPackage;

public interface BookingService {

	  public Booking makeBooking(Booking book) throws BookingException;
	  
	  public Booking cancelBooking(Booking book)throws BookingException;
	  
	  public Booking viewBooking(Integer bookingId) throws BookingException;
	  
	public Set<Booking> assignCustomertoBooking(Customer customer, Integer customerId)throws BookingException;  	  
	  	  

}
