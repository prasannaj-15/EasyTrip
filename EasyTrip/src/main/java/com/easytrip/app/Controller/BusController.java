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
import org.springframework.web.bind.annotation.RestController;

import com.easytrip.app.Exception.BusException;
import com.easytrip.app.Exception.TravelException;
import com.easytrip.app.Model.Bus;
import com.easytrip.app.Model.Travels;
import com.easytrip.app.Service.BusService;
import com.fasterxml.jackson.annotation.JsonIgnore;

@RestController
@RequestMapping("easytripapp/bus")
public class BusController {
	
	@Autowired
	private BusService bService;
	
	@PostMapping("/addBus")
	@JsonIgnore
	public ResponseEntity<Bus> addBusHandler(@RequestBody Bus bus) throws BusException{
		
		Bus addBus =bService.addBus(bus);
		
		return new ResponseEntity<Bus>(addBus,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateBus")
	@JsonIgnore
	public ResponseEntity<Bus> updateBusHandler(@RequestBody Bus bus) throws BusException{
		
		Bus updateBus =bService.addBus(bus);
		
		return new ResponseEntity<Bus>(updateBus,HttpStatus.ACCEPTED);
	}
	
	
	
	@JsonIgnore
	@DeleteMapping("/removeBus/{busId}")
	public ResponseEntity<Bus> removeBusHandler(@PathVariable("busId") Integer busId) throws BusException{
		
		Bus deletedBus =bService.removeBus(busId);
		
		return new ResponseEntity<Bus>(deletedBus,HttpStatus.OK);
	}
	
	@GetMapping("/searchBus/{busId}")
	public ResponseEntity<Bus> searchByIdBusHandler(@PathVariable("busId") Integer busId) throws BusException{
		
		Bus searchBus =bService.searchBus(busId);
		
		return new ResponseEntity<Bus>(searchBus,HttpStatus.OK);
	}
	
}
