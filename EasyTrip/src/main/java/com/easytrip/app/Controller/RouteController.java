package com.easytrip.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easytrip.app.Exception.RouteException;
import com.easytrip.app.Exception.TravelException;
import com.easytrip.app.Model.Route;
import com.easytrip.app.Model.Travels;
import com.easytrip.app.Service.RouteService;
import com.fasterxml.jackson.annotation.JsonIgnore;

@RestController
//@RequestMapping("easytripapp/route")
public class RouteController {
	@Autowired
	private RouteService rService;
	
	@PostMapping("/route")
	public ResponseEntity<Route> addBusHandler(@RequestBody Route route) throws RouteException{
		
		Route savedRoute =rService.addRoute(route);
		
		return new ResponseEntity<Route>(savedRoute,HttpStatus.CREATED);
	}
	
	@PutMapping("/route")
	public ResponseEntity<Route> updateBusHandler(@RequestBody Route route) throws RouteException{
		
		Route updateRoute =rService.addRoute(route);
		
		return new ResponseEntity<Route>(updateRoute,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/route/{routeId}")
	public ResponseEntity<Route> removeBusHandler(@PathVariable("routeId") Integer routeId) throws RouteException{
		
		Route deletedRoute =rService.removeRoute(routeId);
		
		return new ResponseEntity<Route>(deletedRoute,HttpStatus.OK);
	}
	
	@GetMapping("/searchRoute/{routeId}")
	public ResponseEntity<Route> searchByIdBusHandler(@PathVariable("routeId") Integer routeId) throws RouteException{
		
		Route searchRoute =rService.searchRoute(routeId);
		
		return new ResponseEntity<Route>(searchRoute,HttpStatus.OK);
	}
	
	@GetMapping("/viewRoute")
	public ResponseEntity<List<Route>> viewRouteByIdHandler() throws TravelException{
		List<Route> route=rService.viewRoute();
		
		return new ResponseEntity<>(route, HttpStatus.OK);
	}

}
