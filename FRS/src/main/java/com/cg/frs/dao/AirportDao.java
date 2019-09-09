package com.cg.frs.dao;

import java.util.List;

import com.cg.frs.dto.Airport;

public interface AirportDao {

	
	
	
	List<Airport> viewAirport();
    Airport viewAirport(String s);
	
}
