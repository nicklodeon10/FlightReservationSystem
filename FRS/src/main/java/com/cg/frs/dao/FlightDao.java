package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Flight;

public interface FlightDao {

	public Flight addFlight(Flight flight);
     
	public List<Flight> viewFlight();
     
	public void deleteFlight(BigInteger flightId);   
	
}
