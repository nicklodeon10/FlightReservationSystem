package com.cg.frs.service;

import java.util.List;

import com.cg.frs.dto.Airport;

public interface AirportService {

	public List<Airport> viewAirport();
    
	public Airport viewAirport(String airportCode);
	
}
