package com.easytrip.app.Service;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.TripPackage;

public interface TripPackageServices {
	
	public TripPackage addTripPackage(TripPackage pack, String key) throws PackageException, AdminException;
	public TripPackage searchTripPackage(Integer packageId) throws PackageException;
	public TripPackage deleteTripPackage(Integer packageId) throws PackageException;
	
}
