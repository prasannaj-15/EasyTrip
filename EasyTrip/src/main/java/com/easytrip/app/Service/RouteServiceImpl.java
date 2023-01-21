package com.easytrip.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytrip.app.Exception.AdminException;
import com.easytrip.app.Exception.BusException;
import com.easytrip.app.Exception.RouteException;
import com.easytrip.app.Model.Bus;
import com.easytrip.app.Model.CurrentUserSession;
import com.easytrip.app.Model.Route;
import com.easytrip.app.Repository.AdminRepository;
import com.easytrip.app.Repository.RouteDao;
import com.easytrip.app.Repository.SessionRepository;

@Service
public class RouteServiceImpl implements RouteService{
	@Autowired
	private RouteDao rDao;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	@Override
	public Route addRoute(Route route, String key) throws RouteException, AdminException{
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {

			if(route!=null) {
				Route savedRoute=rDao.save(route);
				return savedRoute;
			}else 
				throw new RouteException("Route not added.........");
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
	}

	@Override
	public Route updateBus(Route route, String key) throws RouteException,AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {

		
		Optional<Route> opt=rDao.findById(route.getRouteId());
		if(opt.isPresent()) {
			return rDao.save(route);
		}else 
			throw new RouteException("Bus not available.........");
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
	}

	@Override
	public Route removeRoute(Integer routeId, String key) throws RouteException,AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {

			Optional<Route> opt=rDao.findById(routeId);
			if(opt.isPresent()) {
				Route existingRoute=opt.get();
				rDao.delete(existingRoute);
				return existingRoute;
			}else 
				throw new RouteException("Invalid routeId");
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
	}

	@Override
	public Route searchRoute(Integer routeId, String key) throws RouteException, AdminException {
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		Optional<Route> opt=rDao.findById(routeId);
		if(opt.isPresent()) {
			Route route=opt.get();
			return route;
		}else {
			throw new RouteException("Invalid routeId...........");
		}
	}

	@Override
	public List<Route> viewRoute(String key) throws RouteException, AdminException {
		
		CurrentUserSession loggedInUser = sessionRepo.findByUuid(key);
		
		if(loggedInUser == null) {
			throw new AdminException("Login first! or Please provide a valid key");
		}
		
		if(loggedInUser.getUserType().equals("Admin")) {

			List<Route> routes=rDao.findAll();
			if(routes.size()==0) {
				throw new RouteException("Routes not found..........");
			}else 
				return routes;
		}
		else
			throw new AdminException("User is not Admin. This service is only accessable for admin.");
	}

}