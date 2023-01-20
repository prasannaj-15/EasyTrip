package com.easytrip.app.Service;

import com.easytrip.app.Exception.BookingException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Booking;

public interface BookingService {

	  public Booking makeBooking(Booking book) throws BookingException;
	  
	  public Booking cancelBooking(Booking book)throws BookingException;
	  
	  public Booking viewBooking(Integer bookingId);
}
