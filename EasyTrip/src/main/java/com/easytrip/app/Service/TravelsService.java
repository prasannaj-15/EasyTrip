package com.easytrip.app.Service;

import java.util.List;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Exception.TravelException;
import com.easytrip.app.Model.Travels;

public interface TravelsService {
	public Travels addTravels(Travels travels, String key) throws TravelException, AdminException;
	public List<Travels> viewTravels(String key) throws TravelException, AdminException;
	public Travels updateTravels(Travels travels, String key) throws TravelException, AdminException;
	public Travels removeTravels(Integer travelId, String key) throws TravelException, AdminException;
	public Travels searchTravels(Integer travelId, String key) throws TravelException, AdminException;
}
