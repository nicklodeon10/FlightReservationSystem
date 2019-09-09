package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Flight;

public class FlightServiceImpl implements FlightService{

	FlightServiceImpl flightDao=new FlightServiceImpl();

	@Override
	public Flight addFlightService(Flight flight) {
		return flightDao.addFlightService(flight);
	}

	@Override
	public Flight modifyFlightService(Flight flight) {
		return flightDao.addFlightService(flight);
	}

	@Override
	public List<Flight> viewFlight() {
		return flightDao.viewFlight();
	}

	@Override
	public Flight viewFlight(BigInteger flightId) {
		List<Flight> flightList=flightDao.viewFlight();
		for(Flight flight: flightList) {
			if(flight.getFlightNumber()==flightId)
				return flight;
		}
		return null;
	}

	@Override
	public void deleteFlight(BigInteger flightId) {
		flightDao.deleteFlight(flightId);
	}
	
}
