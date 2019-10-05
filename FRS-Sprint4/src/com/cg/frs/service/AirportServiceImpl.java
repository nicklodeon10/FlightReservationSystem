package com.cg.frs.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dao.AirportDao;
import com.cg.frs.dto.Airport;

@Service("airportService")
@Transactional
public class AirportServiceImpl implements AirportService {

	@Autowired
	AirportDao airportDao;
	
	@Override
	public List<Airport> viewAirport() {
		return airportDao.viewAirport();
	}

	@Override
	public Airport viewAirport(String airportCode) {
		List<Airport> airportList=airportDao.viewAirport();
		for(Airport airport: airportList) {
			if(airport.getAirportCode().equals(airportCode))
				return airport;
		}
		return null;
	}

	@Override
	public boolean validateAirportWithCode(String airportCode) {
		if(viewAirport(airportCode)==(null))
			return false;
		return true;
	}

	@Override
	public boolean compareAirport(Airport src, Airport dest) {
		if(src==dest)
			return false;
		return true;
	}

}
