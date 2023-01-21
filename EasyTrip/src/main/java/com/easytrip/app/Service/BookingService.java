package com.easytrip.app.Service;

import java.util.Set;

import com.easytrip.app.Exception.BookingException;
import com.easytrip.app.Exception.CustomerException;
import com.easytrip.app.Exception.HotelException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Booking;
import com.easytrip.app.Model.Customer;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.TripPackage;

public interface BookingService {

	  public Booking makeBooking(Booking book, String key) throws BookingException, CustomerException;
	  
	  public Booking cancelBooking(Integer bookingId, String key)throws BookingException;
	  
	  public Booking viewBooking(Integer bookingId, String key) throws BookingException;
	  
	  public Set<Booking> assignCustomertoBooking(Customer customer, Integer customerId)throws BookingException;  	  
	  	  

}
