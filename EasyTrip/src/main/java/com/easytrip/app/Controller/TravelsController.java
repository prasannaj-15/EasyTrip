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

import com.easytrip.app.Exception.TravelException;
import com.easytrip.app.Model.Travels;
import com.easytrip.app.Service.TravelsService;
import com.fasterxml.jackson.annotation.JsonIgnore;

@RestController
@RequestMapping("easytripapp/travels")
public class TravelsController {
	
	@Autowired
	private TravelsService tService;
	
	@PostMapping("/addTravels")
	@JsonIgnore
	public ResponseEntity<Travels> addTravelsHandler(@RequestBody Travels travel) throws TravelException{
		
		Travels addTravels =tService.addTravels(travel);
		
		return new ResponseEntity<Travels>(addTravels,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateTravels")
	@JsonIgnore
	public ResponseEntity<Travels> updateTravelsHandler(@RequestBody Travels travel) throws TravelException{
		
		Travels updateTravels =tService.updateTravels(travel);
		
		return new ResponseEntity<Travels>(updateTravels,HttpStatus.ACCEPTED);
	}
	
	@JsonIgnore
	@DeleteMapping("/removeTravels/{travelId}")
	public ResponseEntity<Travels> removeTravelsHandler(@PathVariable("travelId") Integer travelId) throws TravelException{
		
		Travels deletedTravels =tService.removeTravels(travelId);
		
		return new ResponseEntity<Travels>(deletedTravels,HttpStatus.OK);
	}
	
	@GetMapping("/searchTravels/{travelId}")
	public ResponseEntity<Travels> searchTravelsByIdHandler(@PathVariable("travelId") Integer travelId) throws TravelException{
		
		Travels travels =tService.searchTravels(travelId);
		
		return new ResponseEntity<Travels>(travels,HttpStatus.OK);
	}
	
	@GetMapping("travels/viewTravels")
	public ResponseEntity<List<Travels>> viewTravelsByIdHandler() throws TravelException{
		List<Travels> travels=tService.viewTravels();
		
		return new ResponseEntity<>(travels, HttpStatus.OK);
	}
}
