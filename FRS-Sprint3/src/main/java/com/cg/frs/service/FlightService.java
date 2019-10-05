package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Flight;
import com.cg.frs.exception.FRSException;

public interface FlightService {   
   
	public Flight addFlight(Flight flight);
    
	public Flight modifyFlight(Flight flight);
    
	public List<Flight> viewFlight();
    
	public Flight viewFlight(BigInteger flightId) throws FRSException;
    
	public boolean deleteFlight(BigInteger flightId);
	
	public BigInteger validateFlightWithId(BigInteger flightId) throws FRSException;
      
}
