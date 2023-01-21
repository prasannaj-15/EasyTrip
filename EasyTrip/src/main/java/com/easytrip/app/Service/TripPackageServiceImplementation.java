package com.easytrip.app.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Booking;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.Route;
import com.easytrip.app.Model.TripPackage;
import com.easytrip.app.Repository.TripPackageDao;



@Service
public class TripPackageServiceImplementation implements TripPackageServices {

	@Autowired
	private TripPackageDao pdao;
	
	
	@Override
	public TripPackage addTripPackage(TripPackage pack) throws PackageException {
		
		Set<Hotel>	hotelSet=pack.getHotelSet();
		if(hotelSet.size()!=0) {
		for(Hotel hotel:hotelSet) {
			hotel.setTripPackage(pack);
		}
	return	pdao.save(pack);
		}else {
			throw new PackageException("Supplied Hotel set is empty");
		}
		}
	
	@Override
	public TripPackage searchTripPackage(Integer packageId) throws PackageException {
	Optional<TripPackage> opt=pdao.findById(packageId);
	if(opt.isPresent()) {
		return opt.get();
	}else{
		throw new PackageException("No package found with given Id..."+packageId);
	}
	}
	@Override
	public TripPackage deleteTripPackage(Integer packageId) throws PackageException {
		Optional<TripPackage> opt = pdao.findById(packageId);
		if (opt.isPresent()) {
			TripPackage tripPackage = opt.get();
			pdao.delete(tripPackage);
			return tripPackage;

		} else
			throw new PackageException("Student does not exist with roll :" + packageId);

	}


	
}
