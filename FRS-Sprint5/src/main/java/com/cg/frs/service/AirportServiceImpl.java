/**
 * 
 */
package com.cg.frs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.FlightReservationSystemApplication;
import com.cg.frs.dto.Airport;
import com.cg.frs.exception.InvalidAirportException;
import com.cg.frs.repository.AirportRepository;

/**
 * @author: DEVANG
 * description: Implementation of Airport Service Interface
 * created date: 09/10/2019
 * modified: -
 */
@Service("airportService")
@Transactional
public class AirportServiceImpl implements AirportService {

	@Autowired
	AirportRepository airportDao;
	
	private static final Logger logger = LoggerFactory.getLogger(AirportServiceImpl.class);

	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves a list of all airports.
	 *  Input: -
	 *  Output: Airport List
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@Override
	public List<Airport> viewAirport() {
		logger.info("Retrieving all airports.");
		return airportDao.findAll();
	}

	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves an Airport by its code.
	 *  Input: Airport Code String.
	 *  Output: Airport Object
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@Override
	public Airport viewAirport(String airportCode) throws InvalidAirportException {
		Airport airport=airportDao.findById(airportCode).get();
		logger.info("Retrieved Airport for code: "+airportCode);
		if(airport==null) {
			logger.error("No Airport Found for code: "+airportCode);
			throw new InvalidAirportException("No Airport Found");
		}
		logger.info("Returning found Airport.");
		return airport;
	}

	/*	
	 *  Author: DEVANG
	 *  Description: Validates an airport by its code
	 *  Input: Airport Code String
	 *  Output: True if found, else throws Exception.
	 *  Created Date: 09/10/2019
	 *  Last Modified: 10/10/2019 
	 */
	@Override
	public boolean validateAirportWithCode(String airportCode)throws InvalidAirportException {
		viewAirport(airportCode);
		logger.info("Airport Validated.");
		return true;
	}

	/*	
	 *  Author: DEVANG
	 *  Description: Checks if two airports are the same.
	 *  Input: Airport objects for two airports.
	 *  Output: True if same, else throws Exception.
	 *  Created Date: 09/10/2019
	 *  Last Modified 10/10/2019
	 */
	@Override
	public boolean compareAirport(Airport src, Airport dest) throws InvalidAirportException {
		if (src == dest) {
			logger.error("Airports Compared. Same.");
			throw new InvalidAirportException("Both Airports are the same.");
		}
		logger.info("Airports Compared. Different.");
		return false;
	}

}
