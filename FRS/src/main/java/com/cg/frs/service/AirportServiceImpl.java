package com.cg.frs.service;

import java.util.List;

import com.cg.frs.dao.AirportDao;
import com.cg.frs.dao.AirportDaoImpl;
import com.cg.frs.dto.Airport;

public class AirportServiceImpl implements AirportService {

	AirportDao airportDao=new AirportDaoImpl();
	
	@Override
	public List<Airport> viewAirport() {
		return airportDao.viewAirport();
	}

	@Override
	public Airport viewAirport(String airportCode) {
		List<Airport> airportList=airportDao.viewAirport();
		for(Airport airport: airportList) {
			if(airport.getAirportCode()==airportCode)
				return airport;
		}
		return null;
	}

	@Override
	public boolean validateAirportWithCode(String airportCode) {
		if(viewAirport(airportCode)==null)
			return false;
		return true;
	}

}
