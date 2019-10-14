/**
 * 
 */
package com.cg.frs.service;

import java.util.List;

import com.cg.frs.dto.Airport;
import com.cg.frs.exception.InvalidAirportException;

/**
 * @author: DEVANG, SURYA description: Service Interface for Airport created
 *          date: 09/10/2019 modified: -
 */
public interface AirportService {

	/*
	 * Author: DEVANG, SURYA Description: Retrieves a list of all airports. Input: -
	 * Output: Airport List Created Date: 09/10/2019 Last Modified: -
	 */
	public List<Airport> viewAirport() throws Exception;

	/*
	 * Author: DEVANG, SURYA Description: Retrieves an Airport by its code. Input:
	 * Airport Code String. Output: Airport Object Created Date: 09/10/2019 Last
	 * Modified: -
	 */
	public Airport viewAirport(String airportCode) throws InvalidAirportException;

	/*
	 * Author: DEVANG, SURYA Description: Validates an airport by its code Input:
	 * Airport Code String Output: True if found, else throws Exception. Created
	 * Date: 09/10/2019 Last Modified: 10/10/2019
	 */
	public boolean validateAirportWithCode(String airportCode) throws InvalidAirportException;

	/*
	 * Author: DEVANG, SURYA Description: Checks if two airports are the same.
	 * Input: Airport objects for two airports. Output: True if same, else throws
	 * Exception. Created Date: 09/10/2019 Last Modified 10/10/2019
	 */
	public boolean compareAirport(Airport src, Airport dest) throws InvalidAirportException;

}