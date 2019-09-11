package com.cg.frs.dao;

import java.util.List;

import com.cg.frs.dto.Airport;

public interface IAirportDao {
    
	public void addAirport();
	
	public List<Airport> viewAirport();
	
}
