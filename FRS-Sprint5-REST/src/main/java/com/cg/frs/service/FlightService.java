/**
 *  @author NAVYA
 *
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;


import com.cg.frs.dto.Flight;
import com.cg.frs.exception.FlightExceptions;

public interface FlightService {


	public Flight saveFlight(Flight flight);				//Adding the flight 

	
	public List<Flight> viewAllFlight()throws FlightExceptions;					//To view all the flights available

	public Flight searchFlight(BigInteger flightId) throws FlightExceptions;		//To search flight with flight Id

	public Flight modifyFlight(Flight flight)throws FlightExceptions;				//To modify flight
			
	public boolean deleteFlight(BigInteger flightId)throws FlightExceptions;		//To remove flight 
}
