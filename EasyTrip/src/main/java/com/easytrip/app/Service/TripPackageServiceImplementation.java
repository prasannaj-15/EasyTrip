package com.easytrip.app.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Exception.BookingException;
import com.easytrip.app.Exception.HotelException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Booking;
import com.easytrip.app.Model.CurrentUserSession;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.Route;
import com.easytrip.app.Model.Status;
import com.easytrip.app.Model.TicketDetails;
import com.easytrip.app.Model.TripPackage;
import com.easytrip.app.Repository.AdminRepository;
import com.easytrip.app.Repository.SessionRepository;
import com.easytrip.app.Repository.TicketDetailsRepository;
import com.easytrip.app.Repository.BookingRepository;
import com.easytrip.app.Repository.TripPackageDao;



@Service
public class TripPackageServiceImplementation implements TripPackageServices {

	@Autowired
	private TripPackageDao pdao;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private SessionRepository sessionRepo;
  
  @Autowired
	private BookingRepository bdao;
  
  @Autowired
 	private TicketDetailsRepository tdao;


	
  
	@Override
	public TripPackage addTripPackage(TripPackage pack, String key) throws PackageException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {
			
			Set<Hotel> hotelSet=pack.getHotelSet();
			
			if(hotelSet.size()!=0) {
				for(Hotel hotel:hotelSet) {
					hotel.setTripPackage(pack);
				}
			
				return	pdao.save(pack);
			
			}
			else 
				throw new PackageException("Supplied Hotel set is empty");
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
		}
	
	
	@Override
	public TripPackage searchTripPackage(Integer packageId, String key) throws PackageException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		Optional<TripPackage> opt=pdao.findById(packageId);
		if(opt.isPresent()) {
			return opt.get();
		}else{
			throw new PackageException("No package found with given Id..."+packageId);
		}
		
	}
	
	
	@Override
	public TripPackage deleteTripPackage(Integer packageId, String key) throws PackageException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {
		
			Optional<TripPackage> opt = pdao.findById(packageId);
			if (opt.isPresent()) {
				TripPackage tripPackage = opt.get();
				
				Set<Hotel> hSet = tripPackage.getHotelSet();
				for(Hotel h1:hSet) {
					h1.setTripPackage(null);
				}
				hSet.clear();
				tripPackage.setHotelSet(hSet);
				
				pdao.deleteById(tripPackage.getPackageId());
				return tripPackage;
	
			} else
				throw new PackageException("Package does not exist with roll :" + packageId);
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
	}

	@Override
	public TripPackage assignBookingToTripPackage(Integer bookingId, Integer tripPackageId, String key)
			throws BookingException, PackageException, AdminException {

		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		Optional<Booking> optBooking=bdao.findById(bookingId);
		Optional<TripPackage> optPackage=pdao.findById(tripPackageId);
		if(optBooking.isPresent()) {
			Booking booking = optBooking.get();
			if(optPackage.isPresent()) {
				TripPackage tPackage =optPackage.get();
				
				booking.getPackageSet().add(tPackage);
				tPackage.setBooking(booking);
				bdao.save(booking);
				return tPackage;
			}else {
				throw new PackageException("No TripPackage found with id--> "+tripPackageId);
			}
			
		}else{
			throw new PackageException("No Booking found with id--> "+bookingId);
		}
		
	}


	@Override
	public List<TripPackage> viewAllPackages(String key) throws AdminException {

		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {
		
			List<TripPackage> packageList= pdao.findAll();
			
			if(packageList.isEmpty())
				throw new PackageException("No pacages found..");
			
			return packageList;
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
		
	}


	@Override
	public TripPackage assignTicketToTripPackage(Integer ticketId, Integer tripPackageId, String key)
			throws PackageException, AdminException {
	CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		Optional<TicketDetails> optTicket=tdao.findById(ticketId);
	
		Optional<TripPackage> optPackage=pdao.findById(tripPackageId);
		if(optTicket.isPresent()) {
			
			TicketDetails ticket = optTicket.get();
			
			
			if(optPackage.isPresent()) {
				TripPackage tPackage =optPackage.get();
				tPackage.setTicket(ticket);
				Double previousTicketCost=ticket.getTotalTicketCost();
				if(previousTicketCost==null || previousTicketCost==0) {
					ticket.setTotalTicketCost(tPackage.getPackageCost());
				}
				else {
					ticket.setTotalTicketCost(previousTicketCost+tPackage.getPackageCost());
					
				}
				
				ticket.getPackageSet().add(tPackage);
				
				pdao.save(tPackage);
				return tPackage;
			}else {
				throw new PackageException("No TripPackage found with id--> "+tripPackageId);
			}
			
		}else{
			throw new HotelException("No Hotel found with id--> "+ticketId);
		}
	}


	
}
