package com.easytrip.app.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Exception.BookingException;
import com.easytrip.app.Exception.CustomerException;
import com.easytrip.app.Exception.HotelException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Booking;
import com.easytrip.app.Model.CurrentUserSession;
import com.easytrip.app.Model.Customer;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.Status;
import com.easytrip.app.Model.TicketDetails;
import com.easytrip.app.Model.TripPackage;
import com.easytrip.app.Repository.BookingRepository;
import com.easytrip.app.Repository.CustomerRepository;
import com.easytrip.app.Repository.SessionRepository;
import com.easytrip.app.Repository.TicketDetailsRepository;
@Service
public class BookingServiceImpl implements BookingService {
    
	@Autowired
	public BookingRepository br;
	
	@Autowired
	public CustomerRepository customerRepository;
	

	@Autowired
	private SessionRepository sessionRepo;
	
	@Autowired
	private TicketDetailsRepository ticketRepo;
	
	@Override
	public Booking makeBooking(Booking book, String key) throws BookingException, CustomerException {
     
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		Customer customer  = customerRepository.findById(loggedInUser.getUserId()).orElseThrow(() -> new CustomerException("Customer not found.."));
		
		book.setCustomer(customer);
		book.setBookingDate(LocalDateTime.now());
		TicketDetails ticket = new TicketDetails();
		book.setTicketDetails(ticket);
		
		ticket.setTicketStatus(Status.Available);
		ticket.setBooking(book);
	
		return br.save(book);
				
	}

	@Override
	public Booking cancelBooking(Integer bookingId, String key) throws BookingException {
		
		 CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}		
		
		Optional<Booking> opt = br.findById(bookingId);
      
	      if(opt != null)
	      {
	    	  Booking booking = opt.get();
	    	  
	    	  if(booking.getCustomer().getCustomerId() == loggedInUser.getUserId() || loggedInUser.getUserType().equals("Admin")) {
	    			
	    	  
		    	 Set<TripPackage> packageSet  = booking.getPackageSet();
		    	 
		    	 for(TripPackage pack : packageSet) {
		    		 pack.setBooking(null);
		    	 }
		    	 
		    	 packageSet.clear();
		    	 booking.setPackageSet(packageSet);
		    	 
//		    	  if(booking.getTicketDetails() != null) {
//		    		  Optional<TicketDetails> ticketOpt = ticketRepo.findById(booking.getTicketDetails().getTicketId());
//		    	  
//		    	  if(ticketOpt.isPresent()) {
//		    		  
//		    		  TicketDetails ticket = ticketOpt.get();
//		    		  
//		    		  ticketRepo.delete(ticket);		 
//		    	  }
		
		    	// } 
		    	  br.deleteById(bookingId);
		    	  return booking;
	    	  
		      }
		      else 
		    	  throw new BookingException("Invalid booking details.");
	      }
	      else 
	    	  throw new BookingException("Invalid booking Id");
		
	
	}

	@Override
	public Booking viewBooking(Integer bookingId, String key) {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		  Optional<Booking> opt = br.findById(bookingId);
		  
		  if(opt !=null) {
			 
			  Booking booking = opt.get();
			  
			  if(booking.getCustomer().getCustomerId() == loggedInUser.getUserId() || loggedInUser.getUserType().equals("Admin")) {			
				  
				  return booking;
				  
			  }
			  else 
				  throw new BookingException("Access Denied!");
			  
		  }
		  else 
			  throw new BookingException("Invalid Booking details.. Please provide proper details");
		  
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
		else 
			throw new CustomerException("no booking found....");
		
	}
	
}























