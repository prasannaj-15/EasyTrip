package com.easytrip.app.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.BookingException;
import com.easytrip.app.Model.Booking;
import com.easytrip.app.Repository.BookingRepository;
@Service
public class BookingServiceImpl implements BookingService {
    
	@Autowired
	public BookingRepository br;
	
	@Override
	public Booking makeBooking(Booking book) throws BookingException {
     
		Booking booking = br.save(book);
		if(booking != null)
		{
			return booking;
		}
		else {
			throw new BookingException("sorry no booking");
		}		
	}

	@Override
	public Booking cancelBooking(Booking book) throws BookingException {

      Optional<Booking> opt = br.findById(book.getBookingId());
      if(opt != null)
      {
    	  br.deleteById(book.getBookingId());
    	  return book;
      }else {
    	  throw new BookingException("Invalid booking details.");
      }
		
	}

	@Override
	public Booking viewBooking(Integer bookingId) {
    
		  Optional<Booking> opt = br.findById(bookingId);
		  
		  if(opt !=null) {
			  return opt.get();
		  }
		  else {
			  throw new BookingException("Invalid Booking details.. Please provide proper details");
		  }

		
	}

}
