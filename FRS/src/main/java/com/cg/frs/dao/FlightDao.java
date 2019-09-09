package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Flight;

public interface FlightDao {

	
	
	
	 Flight addFlightService(Flight flight);
     Flight modifyFlightService(Flight flight);
     List<Flight> viewFlight();
     List<Flight> viewFlight(BigInteger flightId);
     void deleteFlight(BigInteger flightId);
     
	
}
