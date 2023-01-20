package com.easytrip.app.Service;

import com.easytrip.app.Exception.PackageException;
import com.easytrip.app.Model.TripPackage;

public interface TripPackageServices {

	
	
	
	public TripPackage addTripPackage(TripPackage pack) throws PackageException;
	public TripPackage searchTripPackage(Integer packageId) throws PackageException;
	public TripPackage deleteTripPackage(Integer packageId) throws PackageException;
	
}
