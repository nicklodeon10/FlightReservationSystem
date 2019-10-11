package com.cg.frs.service;

import java.util.List;

import com.cg.frs.dto.Airport;
import com.cg.frs.exception.FrsException;

public interface AirportService {

	public List<Airport> viewAirport();
    
	public Airport viewAirport(String airportCode) throws FrsException;
	
	public boolean validateAirportWithCode(String airportCode) throws FrsException;
	
	public boolean compareAirport(Airport src, Airport dest) throws FrsException;
	
}