/**
 * 
 */
package com.cg.frs.service;

import java.util.List;

import com.cg.frs.dto.Airport;
import com.cg.frs.exception.InvalidAirportException;

/**
 * @author DEVANG
 *
 */
public interface AirportService {

	//Service Method to retrieve a list of airports
	public List<Airport> viewAirport();

	//Service Method to retrieve an airport by its code
	public Airport viewAirport(String airportCode)throws InvalidAirportException;

	//Service Method to validate if airport code is valid
	public boolean validateAirportWithCode(String airportCode)throws InvalidAirportException;

	//Service Method to check if two airports are the same
	public boolean compareAirport(Airport src, Airport dest)throws InvalidAirportException;

}
