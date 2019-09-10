package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Flight;

public interface FlightService {   
   
	Flight addFlight(Flight flight);
    
	Flight modifyFlight(Flight flight);
    
	List<Flight> viewFlight();
    
	Flight viewFlight(BigInteger flightId);
    
	void deleteFlight(BigInteger flightId);
	
	boolean validateFlightWithId(BigInteger flightId);
      
}
