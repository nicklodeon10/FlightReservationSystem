package com.cg.frs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dao.AirportRepository;
import com.cg.frs.dto.Airport;
import com.cg.frs.exception.FrsException;
@Service("airportservice")
public class AirportServiceImpl implements AirportService{
	@Autowired
	AirportRepository airportRepository;

	@Override
	public List<Airport> viewAirport() {
		return airportRepository.findAll();
	}

	@Override
	public Airport viewAirport(String airportCode) throws FrsException {
		if(airportCode==null)
			throw new FrsException("Enter Airport Code");
		Airport airport=airportRepository.getOne(airportCode);
		if(airport==null)
			throw new FrsException("Enter Airport Code");
		else
			return airport;
	}

	@Override
	public boolean validateAirportWithCode(String airportCode) throws FrsException {
		if(airportCode==null)
			throw new FrsException("Enter Airport Code");
		return airportRepository.existsById(airportCode);
	}

	@Override
	public boolean compareAirport(Airport src, Airport dest) throws FrsException  {
		if(src.equals(dest))
			throw new FrsException("Enter Valid Source and Destination Airports");
		return false;
	}

}
