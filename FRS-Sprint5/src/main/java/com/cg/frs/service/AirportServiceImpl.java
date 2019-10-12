/**
 * 
 */
package com.cg.frs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		if(airport==null) {
			throw new InvalidAirportException("No Airport Found");
		}
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
		if (src == dest)
			throw new InvalidAirportException("Both Airports are the same.");
		return false;
	}

}
