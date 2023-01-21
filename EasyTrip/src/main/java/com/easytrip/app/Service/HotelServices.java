package com.easytrip.app.Service;

import java.util.Set;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Exception.HotelException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.TripPackage;

public interface HotelServices {
	
	public Hotel addHotel(Hotel hotel, String key)throws HotelException, AdminException;
	public Hotel getHotelById(Integer hotelId, String key)throws HotelException, AdminException;
	public Hotel updateHotel(Hotel hotel, String key)throws HotelException, AdminException;
	public Hotel deleteHotelById(Integer hotelId, String key)throws HotelException, AdminException;
	public Hotel assignHotelToTripPackage(Integer hotelId,Integer tripPackageId, String key)throws HotelException,PackageException, AdminException;
	public Set<Hotel> getHotelsByPackageId(Integer tripPackageId, String key)throws HotelException, AdminException;
}
