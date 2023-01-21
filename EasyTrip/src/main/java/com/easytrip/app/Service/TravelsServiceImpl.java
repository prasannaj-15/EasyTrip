package com.easytrip.app.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Exception.TravelException;
import com.easytrip.app.Model.Bus;
import com.easytrip.app.Model.CurrentUserSession;
import com.easytrip.app.Model.Travels;
import com.easytrip.app.Repository.AdminRepository;
import com.easytrip.app.Repository.SessionRepository;
import com.easytrip.app.Repository.TravelsDao;

@Service
public class TravelsServiceImpl implements TravelsService{
	@Autowired
	private TravelsDao tdao;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	@Override
	public Travels addTravels(Travels travels,String key) throws TravelException, AdminException{
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {
		
		
			Set<Bus> busSet=travels.getBusSet();
			if(busSet.size()!=0) {
				for(Bus bus:busSet) {
					bus.setTravels(travels);
				}
				return tdao.save(travels);
			}
			else 
				throw new TravelException("Travels not added");
		}
			else
				throw new AdminException("User is not Admin. This service is only accessable for admin.");
	}

	@Override
	public Travels updateTravels(Travels travels, String key) throws TravelException,AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {
		
			Optional<Travels> opt=tdao.findById(travels.getTravelId());
			if(opt.isPresent()) {
				
				Travels updatedTravels=tdao.save(travels);
				return updatedTravels;
				
			}
			else 
				throw new TravelException("Invalid travels details");
			
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
	}

	@Override
	public Travels removeTravels(Integer travelId, String key) throws TravelException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {
			
			Optional<Travels> opt=tdao.findById(travelId);
			if(opt.isPresent()) {
				Travels existingTravels=opt.get();
				tdao.delete(existingTravels);
				return existingTravels;
			}else 
				throw new TravelException("Travels not available..........");
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
	}

	@Override
	public Travels searchTravels(Integer travelId, String key) throws TravelException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		
		Optional<Travels>opt=tdao.findById(travelId);
		
		if(opt.isPresent()) {
			Travels travels=opt.get();
			
			return travels;
			
		}else {
			
			throw new TravelException("Travels not found with the travel id: "+travelId);
		
		}
	}

	@Override
	public List<Travels> viewTravels(String key) throws TravelException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {
		
			List<Travels> travels=tdao.findAll();
			if(travels.size()==0) {
				throw new TravelException("No travels found");
			}
			
				return travels;
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
	}

}	