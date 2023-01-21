package com.easytrip.app.Service;

import java.util.List;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Exception.BookingException;
import com.easytrip.app.Exception.HotelException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.TripPackage;

public interface TripPackageServices {
	
	public TripPackage addTripPackage(TripPackage pack, String key) throws PackageException, AdminException;
	public TripPackage searchTripPackage(Integer packageId, String key) throws PackageException, AdminException;
	public TripPackage deleteTripPackage(Integer packageId, String key) throws PackageException, AdminException;
	public TripPackage assignBookingToTripPackage(Integer bookingId,Integer tripPackageId, String key)throws BookingException,PackageException, AdminException;
	public List<TripPackage> viewAllPackages(String key) throws PackageException, AdminException;
}
