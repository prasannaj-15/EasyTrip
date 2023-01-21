package com.easytrip.app.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Exception.BusException;
import com.easytrip.app.Exception.PackageException;

import com.easytrip.app.Exception.TravelException;
import com.easytrip.app.Model.Bus;
import com.easytrip.app.Model.CurrentUserSession;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.Travels;
import com.easytrip.app.Model.TripPackage;
import com.easytrip.app.Repository.AdminRepository;
import com.easytrip.app.Repository.BusDao;
import com.easytrip.app.Repository.TravelsDao;
import com.easytrip.app.Exception.RouteException;
import com.easytrip.app.Model.Bus;
import com.easytrip.app.Model.Route;
import com.easytrip.app.Model.Bus;
import com.easytrip.app.Model.TripPackage;
import com.easytrip.app.Repository.BusDao;
import com.easytrip.app.Repository.RouteDao;
import com.easytrip.app.Repository.SessionRepository;

@Service
public class BusServiceImpl implements BusService{
	
	@Autowired
	private BusDao bDao;
	
	@Autowired
	private RouteDao rDao;

	@Autowired
	private TravelsDao tDao;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	@Override
	public Bus addBus(Bus bus, String key) throws BusException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {
		
			if(bus!=null) {
				
				Bus savedBus=bDao.save(bus);
				return savedBus;
				
			}else 	
				throw new BusException("Bus not added");	
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
			
	}

	@Override
	public Bus updateBus(Bus bus, String key) throws BusException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {
		
		
			Optional<Bus> opt=bDao.findById(bus.getBusId());
			
			if(opt.isPresent()) {
				
				return bDao.save(bus);
				
			}else 		
				throw new BusException("Bus not available.....");
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
		
	}

	@Override
	public Bus removeBus(Integer busId, String key) throws BusException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {
		
			Optional<Bus> opt=bDao.findById(busId);
			if(opt.isPresent()) {
				Bus existingBus=opt.get();
				bDao.delete(existingBus);
				return existingBus;
				
			}else 
				throw new BusException("Invalid busId............");
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
		
	}

	@Override
	public Bus searchBus(Integer busId, String key) throws BusException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		Optional<Bus> opt=bDao.findById(busId);
		
		if(opt.isPresent()) {
			Bus bus=opt.get();
			return bus;
		}
		else {
			throw new BusException("Invalid busId................");
		}
	}

	@Override
	public List<Bus> viewBus(String key) throws BusException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		List<Bus> buses=bDao.findAll();
		if(buses.size()==0) {
			throw new BusException("No Bus found......");
		}
		
		return buses;
		
		
	}

	@Override
	public Bus assignBusToTravels(Integer busId, Integer travelId, String key) throws BusException, TravelException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		Optional<Bus> optBus=bDao.findById(busId);
		Optional<Travels> optTravels=tDao.findById(travelId);
		if(optBus.isPresent()) {
			Bus bus = optBus.get();
			if(optTravels.isPresent()) {
				Travels travels =optTravels.get();
				travels.getBusSet().add(bus);
				bus.setTravels(travels);
				bDao.save(bus);
				return bus;
			}else {
				throw new TravelException("No Travel found with id--> "+travelId);
			}
			
		}else
			throw new BusException("No Bus found with id--> "+busId);
		}


	public Bus assignBusToTripRoutes(Integer busId, Integer routeId, String key) throws BusException, RouteException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
  
		Optional<Bus> optBus=bDao.findById(busId);
		Optional<Route> optRoute=rDao.findById(routeId);
		if(optBus.isPresent()) {
			Bus bus = optBus.get();
			if(optRoute.isPresent()) {
				Route route =optRoute.get();
				route.getBusses().add(bus);
				bus.setRoute(route);
				bDao.save(bus);
				return bus;
			}else {
				throw new PackageException("No Hotel found with id--> "+routeId);
			}
			
		}else{
			throw new PackageException("No Hotel found with id--> "+busId);
		}

	}
	
	
	
}
