package com.easytrip.app.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.BusException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Exception.TravelException;
import com.easytrip.app.Model.Bus;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.Travels;
import com.easytrip.app.Model.TripPackage;
import com.easytrip.app.Repository.BusDao;
import com.easytrip.app.Repository.TravelsDao;

@Service
public class BusServiceImpl implements BusService{
	
	@Autowired
	private BusDao bDao;

	@Autowired
	private TravelsDao tDao;
	
	@Override
	public Bus addBus(Bus bus) throws BusException {
		if(bus!=null) {
			
			Bus savedBus=bDao.save(bus);
			return savedBus;
			
		}else {
			
			throw new BusException("Bus not added");
			
		}
	}

	@Override
	public Bus updateBus(Bus bus) throws BusException {
		Optional<Bus> opt=bDao.findById(bus.getBusId());
		
		if(opt.isPresent()) {
			
			return bDao.save(bus);
			
		}else {
			
			throw new BusException("Bus not available.....");
		}
		
	}

	@Override
	public Bus removeBus(Integer busId) throws BusException {
		Optional<Bus> opt=bDao.findById(busId);
		if(opt.isPresent()) {
			Bus existingBus=opt.get();
			bDao.delete(existingBus);
			return existingBus;
			
		}else {
			throw new BusException("Invalid busId............");
		}
	}

	@Override
	public Bus searchBus(Integer busId) throws BusException {
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
	public List<Bus> viewBus() throws BusException {
		List<Bus> buses=bDao.findAll();
		if(buses.size()==0) {
			throw new BusException("No Bus found......");
		}else {
			return buses;
		}
		
	}

	@Override
	public Bus assignBusToTravels(Integer busId, Integer travelId) throws BusException, TravelException {
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
			
		}else{
			throw new BusException("No Bus found with id--> "+busId);
		}

	}
	
	
	
}
