/**
 * 
 */
package com.cg.frs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dao.AirportDao;
import com.cg.frs.dto.Airport;
import com.cg.frs.exception.InvalidAirportException;

/**
 * @author DEVANG
 *
 */

@Service("airportService")
@Transactional
public class AirportServiceImpl implements AirportService {

	@Autowired
	AirportDao airportDao;

	//Service Method to retrieve a list of airports
	@Override
	public List<Airport> viewAirport() {
		return airportDao.findAll();
	}

	//Service Method to retrieve an airport by its code
	@Override
	public Airport viewAirport(String airportCode) throws InvalidAirportException {
		Airport airport=airportDao.findById(airportCode).get();
		if(airport==null) {
			throw new InvalidAirportException("No Airport Found");
		}
		return airport;
	}

	//Service Method to validate if airport code is valid
	@Override
	public boolean validateAirportWithCode(String airportCode)throws InvalidAirportException {
		viewAirport(airportCode);
		return true;
	}

	//Service Method to check if two airports are the same
	@Override
	public boolean compareAirport(Airport src, Airport dest) throws InvalidAirportException {
		if (src == dest)
			throw new InvalidAirportException("Both Airports are the same.");
		return false;
	}

}
