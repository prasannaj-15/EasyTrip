package com.easytrip.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.BusException;
import com.easytrip.app.Exception.RouteException;
import com.easytrip.app.Model.Bus;
import com.easytrip.app.Model.Route;
import com.easytrip.app.Repository.RouteDao;

@Service
public class RouteServiceImpl implements RouteService{
	private RouteDao rDao;
	
	@Override
	public Route addRoute(Route route) throws RouteException{
		if(route!=null) {
			Route savedRoute=rDao.save(route);
			return savedRoute;
		}else {
			throw new RouteException("Route not added.........");
		}
	}

	@Override
	public Route updateBus(Route route) throws RouteException {
		Optional<Route> opt=rDao.findById(route.getRouteId());
		if(opt.isPresent()) {
			return rDao.save(route);
		}else {
			throw new RouteException("Bus not available.........");
		}
	}

	@Override
	public Route removeRoute(Integer routeId) throws RouteException {
		Optional<Route> opt=rDao.findById(routeId);
		if(opt.isPresent()) {
			Route existingRoute=opt.get();
			rDao.delete(existingRoute);
			return existingRoute;
		}else {
			throw new RouteException("Invalid routeId");
		}
	}

	@Override
	public Route searchRoute(Integer routeId) throws RouteException {
		Optional<Route> opt=rDao.findById(routeId);
		if(opt.isPresent()) {
			Route route=opt.get();
			return route;
		}else {
			throw new RouteException("Invalid routeId...........");
		}
	}

	@Override
	public List<Route> viewRoute() throws RouteException {
		List<Route> routes=rDao.findAll();
		if(routes.size()==0) {
			throw new RouteException("Routes not found..........");
		}else {
			return routes;
		}
	}

}