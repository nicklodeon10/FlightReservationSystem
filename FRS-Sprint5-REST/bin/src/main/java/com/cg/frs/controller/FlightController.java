package com.cg.frs.SpringBootFrs.controller;
/**
 * @author Navya
 *
 */
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.frs.SpringBootFrs.dto.Flight;
import com.cg.frs.SpringBootFrs.service.FlightService;

@RestController
public class FlightController {

	@Autowired
	FlightService flightService;

	@PostMapping(value = "/add")
	public ResponseEntity<Flight> addData(@ModelAttribute Flight flight) {		//adding the flight
		Flight flightToBeAdded = flightService.saveFlight(flight);

		if (flightToBeAdded == null) {
			return new ResponseEntity("Flight not added", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<Flight>(flightToBeAdded, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/show/data")
	public ResponseEntity<List<Flight>> getAllData() {							//showing all the flights

		List<Flight> flightList = flightService.viewAllFlight();
		if (flightList.isEmpty()) {
			return new ResponseEntity("No Data Present", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<List<Flight>>(flightList, HttpStatus.OK);
		}
	}

	@GetMapping(value="/search")
	public ResponseEntity<Flight> searchData(@RequestParam BigInteger flightId){		//searching flight by Id
		
		Flight flightSearched=flightService.searchFlight(flightId);
		
		if (flightSearched == null) {
			return new ResponseEntity("Flight not present", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<Flight>(flightSearched, HttpStatus.OK);
		}
		
	}

	@PutMapping(value="/modify")
	public ResponseEntity<Flight> modifyData(@ModelAttribute Flight flight){			//modifying the flight
		
Flight flightToBeModified=flightService.modifyFlight(flight);
		
		if (flightToBeModified == null) {
			return new ResponseEntity("Flight not modified", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<Flight>(flightToBeModified, HttpStatus.OK);
		}
		
	}
	
	
	@PostMapping(value="/delete")
	public boolean deleteData(@RequestParam BigInteger flightId){					//removing flight
		
boolean flightToBeDeleted=flightService.deleteFlight(flightId);
		
		return flightToBeDeleted;
		
	}
	
	
	
}
