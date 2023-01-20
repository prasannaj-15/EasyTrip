package com.easytrip.app.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.HotelException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.TripPackage;
import com.easytrip.app.Repository.HotelDao;
import com.easytrip.app.Repository.TripPackageDao;

@Service
public class HotelServicesImplementation implements HotelServices {

	@Autowired
	private TripPackageDao pdao;
	
	
	@Autowired
	private HotelDao hdao;

	@Override
	public Hotel addHotel(Hotel hotel) throws HotelException {
		Hotel foundhotel=hdao.findByHotelName(hotel.getHotelName());
		if(foundhotel==null) {
		if(hotel==null) {
			throw new HotelException("Supplied hotel is invalid..");
		}else {
			return	hdao.save(hotel);
			}
		}
		else {
			throw new HotelException("Supplied hotel name already exists");
		}
		}

	@Override
	public Hotel getHotelById(Integer hotelId) throws HotelException {
		Optional<Hotel> opt=hdao.findById(hotelId);
		if(opt.isPresent()) {
			return opt.get();
		}else{
			throw new HotelException("No Hotel found with given Id..."+hotelId);
		}
	}

	
	@Override
	public Hotel updateHotel(Hotel hotel) throws HotelException {
		Optional<Hotel> opt=hdao.findById(hotel.getHotelId());
		if(opt.isPresent()) {
			return 	hdao.save(hotel);
		}else{
			throw new HotelException("No Hotel found ");
		}
		
	}

	
	@Override
	public Hotel deleteHotelById(Integer hotelId) throws HotelException {
		Optional<Hotel> opt=hdao.findById(hotelId);
		if(opt.isPresent()){
			Hotel hotel =opt.get();
			TripPackage tripPackage= hotel.getTripPackage();
			if(tripPackage==null) {
				hdao.delete(hotel);
				return hotel;
			}else {
		Set<Hotel> hSet =	tripPackage.getHotelSet();
		hSet.remove(hotel);
		hdao.delete(hotel);
		return hotel;
		}}else{
			throw new HotelException("No Hotel found with given Id..."+hotelId);
		}
		
	}

	@Override
	public Set<Hotel> assignHotelToTripPackage(TripPackage tripPackage, Integer tripPackageId)
			throws HotelException, PackageException{
		Optional<TripPackage> tripPackageOpt= pdao.findById(tripPackage.getPackageId());
		
			if(tripPackageOpt.isPresent()) {
				
				TripPackage existingTripPackage = tripPackageOpt.get();
				
				Set<Hotel> setHotel=tripPackage.getHotelSet();
				
				Set<Hotel> newHotel = new HashSet<>();
				
				for(Hotel hotel:setHotel){
					
					hotel.setTripPackage(existingTripPackage);
					Hotel h1 = hdao.save(hotel);
					newHotel.add(h1);
					
//					existingTripPackage.getHotelSet().add(hotel);
					
				}
				
//				existingTripPackage.setHotelSet(Hotel);
				
				pdao.save(existingTripPackage);
				
				return setHotel;
			}else {
				throw new PackageException("No Pacakage found with given Id..."+tripPackageId);
			}
		
	}

//	@Override
//	public Hotel assignHotelToTripPackage(Integer hotelId, Integer tripPackageId)
//			throws HotelException, PackageException {
//		
//		Optional<Hotel> optHotel=hdao.findById(hotelId);
//		Optional<TripPackage> optPackage=pdao.findById(tripPackageId);
//		if(optHotel.isPresent()) {
//			Hotel hotel = optHotel.get();
//			if(optPackage.isPresent()) {
//				TripPackage tPackage =optPackage.get();
//				tPackage.getHotelSet().add(hotel);
//				hotel.setTripPackage(tPackage);
//				hdao.save(hotel);
//				return hotel;
//			}else {
//				throw new PackageException("No Hotel found with id--> "+tripPackageId);
//			}
//			
//		}else{
//			throw new PackageException("No Hotel found with id--> "+hotelId);
//		}
		
//	}


	
	
	}
	
	
	

