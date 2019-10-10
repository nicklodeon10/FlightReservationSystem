package com.cg.frs.SpringBootFrs.service;
/**
 * @author Navya
 *
 */
import java.math.BigInteger;
import java.util.List;

import com.cg.frs.SpringBootFrs.dto.Flight;

public interface FlightService {

	
	
	public Flight saveFlight(Flight flight);				//Adding the flight 

	
	public List<Flight> viewAllFlight();					//To view all the flights available

	public Flight searchFlight(BigInteger flightId);		//To search flight with flight Id

	public Flight modifyFlight(Flight flight);				//To modify flight
			
	public boolean deleteFlight(BigInteger flightId);		//To remove flight 

}
