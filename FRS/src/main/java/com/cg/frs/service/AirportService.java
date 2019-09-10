package com.cg.frs.service;

import java.util.List;

import com.cg.frs.dto.Airport;
import com.cg.frs.exception.FRSException;

public interface AirportService {

	public List<Airport> viewAirport();
    
	public Airport viewAirport(String airportCode);
	
	public String validateAirportWithCode(String airportCode) throws FRSException;
	
}
