package com.easytrip.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.TravelException;
import com.easytrip.app.Model.Travels;
import com.easytrip.app.Repository.TravelsDao;

@Service
public class TravelsServiceImpl implements TravelsService{
	@Autowired
	private TravelsDao tdao;
	
	@Override
	public Travels addTravels(Travels travels) throws TravelException{
		if(travels!=null) {
			Travels savedTravels =tdao.save(travels);
			
			return savedTravels;
		}else {
			throw new TravelException("Travels not added");
		}
		
	}

	@Override
	public Travels updateTravels(Travels travels) throws TravelException {
		Optional<Travels> opt=tdao.findById(travels.getTravelId());
		if(opt.isPresent()) {
			
			Travels updatedTravels=tdao.save(travels);
			return updatedTravels;
			
		}else {
			
			throw new TravelException("Invalid travels details");
			
		}
	}

	@Override
	public Travels removeTravels(Integer travelId) throws TravelException {
		Optional<Travels> opt=tdao.findById(travelId);
		if(opt.isPresent()) {
			Travels existingTravels=opt.get();
			tdao.delete(existingTravels);
			return existingTravels;
		}else {
			throw new TravelException("Travels not available..........");
		}
	}

	@Override
	public Travels searchTravels(Integer travelId) throws TravelException {
		Optional<Travels>opt=tdao.findById(travelId);
		
		if(opt.isPresent()) {
			Travels travels=opt.get();
			
			return travels;
			
		}else {
			
			throw new TravelException("Travels not found with the travel id: "+travelId);
		
		}
	}

	@Override
	public List<Travels> viewTravels() throws TravelException {
		List<Travels> travels=tdao.findAll();
		if(travels.size()==0) {
			throw new TravelException("No travels found");
		}
		else {
			return travels;
		}
	}

}	