package com.cg.frs.controller;

/**
 * @author NAVYA
*
 */


/**
 * urls for ease
 * localhost:9088/flight/add
 * localhost:9088/flight/view
 * localhost:9088/flight/search
 * localhost:9088/flight/modify
 * localhost:9088/flight/delete
 */



import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.frs.dto.Flight;
import com.cg.frs.exception.FlightExceptions;
import com.cg.frs.service.FlightService;

@RestController
public class FlightController {
	 private static final Logger logger = LoggerFactory.getLogger(FlightController.class);
	@Autowired
	FlightService flightService;
	/*
	 * Author: NAVYA 
	 * Description: Will Add The New Flight Details
	 *  Created Date: 09/10/2019 
	 *  Last Modified: 14/10/2019
	 * -
	 */

	@PostMapping(value = "/flight/add")
	public ResponseEntity<?> addData(@ModelAttribute Flight flight) throws FlightExceptions { // adding the flight
		
		logger.info("adding flight");
		Flight flightToBeAdded = flightService.saveFlight(flight);
		if (flightToBeAdded == null) {
			logger.error("flight not added");
			return new ResponseEntity("Flight not added", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			logger.info("added flight");
			return new ResponseEntity<Flight>(flightToBeAdded, HttpStatus.OK);
		}
	}

	/*
	 * Author: NAVYA 
	 * Description:Display The Available Flights
	 *  Created Date: 09/10/2019 
	 *  Last Modified: 14/10/2019
	 * -
	 */
	@GetMapping(value = "/flight/view")
	public ResponseEntity<?> getAllData() throws FlightExceptions { // showing all the flights
		logger.info("viewing flight");
		List<Flight> flightList = flightService.viewAllFlight();
		if (flightList.isEmpty()) {
			logger.error("no flight present");
			return new ResponseEntity("No Flight Present", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			logger.info("display flight");
			return new ResponseEntity<List<Flight>>(flightList, HttpStatus.OK);
		}
	}

	
	/*
	 * Author: NAVYA 
	 * Description:Will Open Page To Search Flight 
	 *  Created Date: 09/10/2019 
	 *  Last Modified: 14/10/2019
	 * -
	 */
	@GetMapping(value = "/flight/search")
	public ResponseEntity<?> searchData(@RequestParam BigInteger flightId) throws FlightExceptions { // searching flight
		logger.info("search flight");																						// by Id
		Flight flightSearched = flightService.searchFlight(flightId);

		if (flightSearched == null) {
			logger.error("no flight present");
			return new ResponseEntity("Flight not present", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			logger.info("found flight");
			return new ResponseEntity<Flight>(flightSearched, HttpStatus.OK);
		}

	}
	
	/*
	 * Author: NAVYA 
	 * Description: Will Open Page To Modify Flight 
	 *  Created Date: 09/10/2019 
	 *  Last Modified: 14/10/2019
	 * -
	 */

	@PutMapping(value = "/flight/modify")
	public ResponseEntity<Flight> modifyData(@ModelAttribute Flight flight) throws FlightExceptions { // modifying the
																										// flight

		logger.info("modifying flight");
		Flight flightToBeModified = flightService.modifyFlight(flight);

		if (flightToBeModified == null) {
			logger.error("flight not modified");
			return new ResponseEntity("Flight not modified", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			logger.info("modified flight");
			return new ResponseEntity<Flight>(flightToBeModified, HttpStatus.OK);
		}

	}


	/*
	 * Author: NAVYA 
	 * Description:Removes The Flight And Returns To show flight
	 *  Created Date: 09/10/2019 
	 *  Last Modified: 14/10/2019
	 * -
	 */
	@PostMapping(value = "/flight/delete")
	public boolean deleteData(@RequestParam BigInteger flightId) throws FlightExceptions { // removing flight
logger.info("removing flight");
		boolean flightToBeDeleted = flightService.deleteFlight(flightId);

		logger.info("flight removed");
		return flightToBeDeleted;

	}

}
