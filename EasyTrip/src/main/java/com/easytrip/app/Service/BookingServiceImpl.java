package com.easytrip.app.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.BookingException;
import com.easytrip.app.Exception.CustomerException;
import com.easytrip.app.Exception.HotelException;
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

	@Override
	public Set<Booking> assignCustomertoBooking(Customer customer, Integer customerId) throws BookingException {
		
		Optional<Customer> customerOpt = customerRepository.findById(customer.getCustomerId());
		
		if(customerOpt.isPresent()) {

			
			Customer existcustomer = customerOpt.get();
			
			Set<Booking> setBooking = customer.getBookingSet();
			
		
		  Set<Booking> newBooking = new HashSet<>();
		  
		  for(Booking book: setBooking)
		  {
			  book.setCustomer(existcustomer);

             Booking b1 = br.save(book);
             
             existcustomer.getBookingSet().add(b1);
		  }
      
		  customerRepository.save(existcustomer);
		  return setBooking; 
	}
		else {
			throw new CustomerException("no booking found....");
		}
	}
	
}























