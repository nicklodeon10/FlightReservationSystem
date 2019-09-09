package com.cg.frs.service;

import java.util.List;

import com.cg.frs.dto.Airport;

public interface AirportService {

	
	
	List<Airport> viewAirport();
    Airport viewAirport(String s);
	
}
