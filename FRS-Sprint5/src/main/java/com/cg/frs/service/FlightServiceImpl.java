/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cg.frs.dto.Flight;
import com.cg.frs.exception.FlightExceptions;
import com.cg.frs.repository.FlightRepository;

/**
 * @author NAVYA
 *
 */

@Service("flightService")
@Transactional
public class FlightServiceImpl implements FlightService {

	/*
	 * Author: NAVYA 
	 * Description: service implementation
	 *  Created Date: 09/10/2019 
	 *  Last Modified:
	 * -
	 */
	

	 private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);
	
	@Autowired
	FlightRepository flightRepository;

	@Override
	// Saves the Flight and sets the states to true
	public Flight addFlight(Flight flight) {
		
logger.info("Adding Flight");
		flight.setFlightState(true);
logger.info("Flight added");
		return flightRepository.save(flight);
	}

	@Override
	// Displays all the flights available
	public List<Flight> viewAllFlight() throws FlightExceptions {
		logger.info("Viewing Flight ");
		List<Flight> flightList = flightRepository.viewAll();
		if (flightList.isEmpty()) {

			logger.error("Flight List is empty ");
			throw new FlightExceptions("NO FLIGHT IS AVAILABLE");
		}

		logger.info("Displaying Flights ");
		return flightList;
	}

	@Override
	// Searches the available flight through entered flight id
	public Flight searchFlight(BigInteger flightId) throws FlightExceptions {
		logger.info("Searching Flight ");
		Flight flightFound = flightRepository.findByFlightNumber(flightId);

		if (flightFound == null || flightFound.getFlightState() == false) {

			logger.error("No flight is there ");
			throw new FlightExceptions("NO FLIGHT HAVING THIS ID IS AVAILABLE");

		}
		logger.info("Flight found ");
		return flightFound;
	}

	@Override
	// Modifies the flight by flight id
	public Flight modifyFlight(Flight flight) throws FlightExceptions {
	
		logger.info("Searching Flight to modify ");
		Flight flightToBeModified = flightRepository.findByFlightNumber(flight.getFlightNumber());
		if (flightToBeModified == null) {
   
			logger.error("No flight is there ");
			throw new FlightExceptions("FLIGHT DOESN'T EXISTS TO MODIFY");

		} else {
			flightToBeModified.setCarrierName(flight.getCarrierName());
			flightToBeModified.setFlightModel(flight.getFlightModel());
			flightToBeModified.setSeatCapacity(flight.getSeatCapacity());
			flightToBeModified.setFlightState(true);
		}
		logger.info("Flight Modified ");
		return flightRepository.save(flightToBeModified);
	}

	@Override
	// Deletes the flight by setting state to false
	public boolean deleteFlight(BigInteger flightId) throws FlightExceptions {
		logger.info("Searching Flight  ");
		Flight removedFlight = flightRepository.findByFlightNumber(flightId);
		if (removedFlight == null) {

			logger.error("No flight is there ");
			throw new FlightExceptions("FLIGHT DOESN'T EXISTS TO DELETE");

		}
		removedFlight.setFlightState(false);
		logger.info("Flight removed ");
		flightRepository.save(removedFlight);
		return true;
	}
}
