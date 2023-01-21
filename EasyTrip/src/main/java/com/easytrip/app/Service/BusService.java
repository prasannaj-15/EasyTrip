package com.easytrip.app.Service;

import java.util.List;

import com.easytrip.app.Exception.BusException;
import com.easytrip.app.Exception.TravelException;
import com.easytrip.app.Exception.RouteException;
import com.easytrip.app.Model.Bus;

public interface BusService {
	public Bus addBus(Bus bus) throws BusException;
	public List<Bus> viewBus() throws BusException;
	public Bus updateBus(Bus bus) throws BusException;
	public Bus removeBus(Integer busId) throws BusException;
	public Bus searchBus(Integer busId) throws BusException;
  
	public Bus assignBusToTravels(Integer busId,Integer travelId) throws BusException,TravelException;
	public Bus assignBusToTripRoutes(Integer busId,Integer routeId)throws BusException,RouteException;

}
