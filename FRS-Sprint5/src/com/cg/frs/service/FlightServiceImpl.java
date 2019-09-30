package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dao.FlightDao;
import com.cg.frs.dto.Flight;

@Service("flightService")
@Transactional
public class FlightServiceImpl implements FlightService{

	@Autowired
	FlightDao flightDao;

	@Override
	public Flight addFlight(Flight flight) {
		return flightDao.addFlight(flight);
	}

	@Override
	public Flight modifyFlight(Flight flight) {
		return flightDao.updateFlight(flight);
	}

	@Override
	public List<Flight> viewFlight() {
		return flightDao.viewFlight();
	}

	@Override
	public Flight viewFlight(BigInteger flightId) {
		List<Flight> flightList=flightDao.viewFlight();
		for(Flight flight: flightList) {
			if(flight.getFlightNumber().equals(flightId))
				return flight;
		}
		return null;
	}

	@Override
	public boolean deleteFlight(BigInteger flightId) {
		return flightDao.deleteFlight(flightId);
	}
	
}
