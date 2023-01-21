package com.easytrip.app.Service;

import java.util.List;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Exception.BusException;
import com.easytrip.app.Exception.TravelException;
import com.easytrip.app.Exception.RouteException;
import com.easytrip.app.Model.Bus;

public interface BusService {
	public Bus addBus(Bus bus, String key) throws BusException, AdminException;
	public List<Bus> viewBus(String key) throws BusException, AdminException;
	public Bus updateBus(Bus bus, String key) throws BusException, AdminException;
	public Bus removeBus(Integer busId, String key) throws BusException, AdminException;
	public Bus searchBus(Integer busId, String key) throws BusException, AdminException;
  
	public Bus assignBusToTravels(Integer busId,Integer travelId, String key) throws BusException,TravelException, AdminException;
	public Bus assignBusToTripRoutes(Integer busId,Integer routeId, String key)throws BusException,RouteException, AdminException;

}
