/**
 * 
 */
package com.cg.frs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.frs.dto.Airport;
import com.cg.frs.exception.InvalidAirportException;
import com.cg.frs.service.AirportService;

/**
 * @author nicklodeon10
 *
 */

@RestController
@RequestMapping("/airport")
@CrossOrigin(origins = "http://localhost:4200")
public class AirportController {

	@Autowired
	AirportService airportService;
	
	private static final Logger logger = LoggerFactory.getLogger(AirportController.class);
	
	@GetMapping("/getall")
	public ResponseEntity<List<Airport>> getAllAirports(){
		logger.info("Retrieving all Airports.");
		return new ResponseEntity<List<Airport>>(airportService.viewAirport(), HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<Airport> getAirportWithCode(@RequestParam("airportCode")String airportCode){
		try {
			logger.info("Retrieving Airport with code: "+airportCode);
			return new ResponseEntity<Airport>(airportService.viewAirport(airportCode), HttpStatus.OK);
		} catch (InvalidAirportException e) {
			logger.error("Airport Not Found.");
			return new ResponseEntity("Airport Not Found.", HttpStatus.BAD_REQUEST);
		}
	}
	
}
