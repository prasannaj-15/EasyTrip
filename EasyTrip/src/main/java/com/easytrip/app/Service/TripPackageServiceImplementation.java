package com.easytrip.app.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.BookingException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Booking;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.Route;
import com.easytrip.app.Model.TripPackage;
import com.easytrip.app.Repository.BookingRepository;
import com.easytrip.app.Repository.TripPackageDao;



@Service
public class TripPackageServiceImplementation implements TripPackageServices {

	@Autowired
	private TripPackageDao pdao;
	
	@Autowired
	private BookingRepository bdao;
	
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

	@Override
	public TripPackage assignBookingToTripPackage(Integer bookingId, Integer tripPackageId)
			throws BookingException, PackageException {

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


	
}
