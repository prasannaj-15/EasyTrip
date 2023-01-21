package com.easytrip.app.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.BookingException;
import com.easytrip.app.Exception.CustomerException;
import com.easytrip.app.Exception.HotelException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Booking;
import com.easytrip.app.Model.Customer;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.TripPackage;
import com.easytrip.app.Repository.BookingRepository;
import com.easytrip.app.Repository.CustomerRepository;
@Service
public class BookingServiceImpl implements BookingService {
    
	@Autowired
	public BookingRepository br;
	
	@Autowired
	public CustomerRepository customerRepository;
	
	
	
	@Override
	public Booking makeBooking(Booking book) throws BookingException {

       
		Set<TripPackage> tripset=book.getPackageSet();
		if(tripset.size()!=0) {
		for(TripPackage pack:tripset) {
			pack.setBooking(book);
		}
		


		}
		  return br.save(book);			
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























