package com.easytrip.app.Controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easytrip.app.Exception.BusException;
import com.easytrip.app.Exception.TravelException;
import com.easytrip.app.Model.Bus;
import com.easytrip.app.Model.Hotel;
import com.easytrip.app.Model.Travels;
import com.easytrip.app.Service.BusService;
import com.fasterxml.jackson.annotation.JsonIgnore;

@RestController
//@RequestMapping("easytripapp/bus")
public class BusController {
	
	@Autowired
	private BusService bService;
	
	@PostMapping("/bus")
	public ResponseEntity<Bus> addBusHandler(@RequestBody Bus bus, @RequestParam(required = false) String key) throws BusException{
		
		Bus addBus =bService.addBus(bus, key);
		
		return new ResponseEntity<Bus>(addBus,HttpStatus.CREATED);
	}
	
	@PutMapping("/bus")
	public ResponseEntity<Bus> updateBusHandler(@RequestBody Bus bus, @RequestParam(required = false) String key) throws BusException{
		
		Bus updateBus =bService.addBus(bus, key);
		
		return new ResponseEntity<Bus>(updateBus,HttpStatus.ACCEPTED);
	}
	
	
	
	@DeleteMapping("/bus/{busId}")
	public ResponseEntity<Bus> removeBusHandler(@PathVariable("busId") Integer busId, @RequestParam(required = false) String key) throws BusException{
		
		Bus deletedBus =bService.removeBus(busId, key);
		
		return new ResponseEntity<Bus>(deletedBus,HttpStatus.OK);
	}
	
	@GetMapping("/bus/{busId}")
	public ResponseEntity<Bus> searchByIdBusHandler(@PathVariable("busId") Integer busId, @RequestParam(required = false) String key) throws BusException{
		
		Bus searchBus =bService.searchBus(busId, key);
		
		return new ResponseEntity<Bus>(searchBus,HttpStatus.OK);
	}
	
	
	@PutMapping("/assignbustotravel/{busId}/{travelId}")
	public ResponseEntity<Bus> assignBusToTravelHandler(@PathVariable("busId") Integer busId,@PathVariable("travelId") Integer travelId, @RequestParam(required = false) String key){
		
    Bus bus= bService.assignBusToTravels(busId, travelId, key);
    return new ResponseEntity<Bus>(bus, HttpStatus.OK);
    
    }
    
	@PutMapping("/assignbustoroute/{busId}/{routeId}")
	public ResponseEntity<Bus> assignBusToRouteHandler(@PathVariable("busId") Integer busId,@PathVariable("routeId") Integer routeId, @RequestParam(required = false) String key){
//		System.out.println(tripPackageId);
		Bus bus= bService.assignBusToTripRoutes(busId, routeId, key);

		return new ResponseEntity<Bus>(bus, HttpStatus.OK);
		
	}
	

}
