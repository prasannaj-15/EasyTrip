package com.easytrip.app.Service;

import java.util.List;

import com.easytrip.app.Exception.RouteException;
import com.easytrip.app.Model.Route;

public interface RouteService {
	public Route addRoute(Route route) throws RouteException;
	public List<Route> viewRoute() throws RouteException;
	public Route updateBus(Route route) throws RouteException;
	public Route removeRoute(Integer routeId) throws RouteException;
	public Route searchRoute(Integer routeId) throws RouteException;

}
