package com.easytrip.app.Service;

import java.util.List;

import com.easytrip.app.Exception.TravelException;
import com.easytrip.app.Model.Travels;

public interface TravelsService {
	public Travels addTravels(Travels travels) throws TravelException;
	public List<Travels> viewTravels() throws TravelException;
	public Travels updateTravels(Travels travels) throws TravelException;
	public Travels removeTravels(Integer travelId) throws TravelException;
	public Travels searchTravels(Integer travelId) throws TravelException;
}
