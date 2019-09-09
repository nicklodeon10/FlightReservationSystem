package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Flight;

public interface FlightService {   
   
	Flight addFlightService(Flight flight);
    
	Flight modifyFlightService(Flight flight);
    
	List<Flight> viewFlight();
    
	Flight viewFlight(BigInteger flightId);
    
	void deleteFlight(BigInteger flightId);
      
}
