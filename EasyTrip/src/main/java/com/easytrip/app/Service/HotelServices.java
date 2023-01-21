package com.easytrip.app.Service;

import java.util.Set;

import com.easytrip.app.Exception.HotelException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.TripPackage;

public interface HotelServices {
	
	public Hotel addHotel(Hotel hotel)throws HotelException;
	public Hotel getHotelById(Integer hotelId)throws HotelException;
	public Hotel updateHotel(Hotel hotel)throws HotelException;
	public Hotel deleteHotelById(Integer hotelId)throws HotelException;
	public Hotel assignHotelToTripPackage(Integer hotelId,Integer tripPackageId)throws HotelException,PackageException;
	public Set<Hotel> getHotelsByPackageId(Integer tripPackageId)throws HotelException;
}
