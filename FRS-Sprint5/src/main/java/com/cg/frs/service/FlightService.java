/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Flight;



public interface FlightService {

	
public  Flight addFlight(Flight flight);
	
	public List<Flight> viewAllFlight();
	
	public Flight searchFlight(BigInteger flightId);
	
	public Flight modifyFlight(Flight flight);
	
	public boolean deleteFlight(BigInteger flightId);
	
	
	
}
