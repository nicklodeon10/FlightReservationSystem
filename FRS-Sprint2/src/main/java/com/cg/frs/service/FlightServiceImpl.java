package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dao.FlightDao;
import com.cg.frs.dao.FlightDaoImpl;
import com.cg.frs.dto.Flight;
import com.cg.frs.exception.FRSException;

public class FlightServiceImpl implements FlightService{

	FlightDao flightDao=new FlightDaoImpl();

	@Override
	public Flight addFlight(Flight flight) {
		return flightDao.addFlight(flight);
	}

	@Override
	public Flight modifyFlight(Flight flight) {
		return flightDao.addFlight(flight);
	}

	@Override
	public List<Flight> viewFlight() {
		return flightDao.viewFlight();
	}

	@Override
	public Flight viewFlight(BigInteger flightId) throws FRSException {
		List<Flight> flightList=flightDao.viewFlight();
		for(Flight flight: flightList) {
			if(flight.getFlightNumber().equals(flightId))
				return flight;
		}
		throw new FRSException("Flight Not Found.");
	}

	@Override
	public boolean deleteFlight(BigInteger flightId) {
		return flightDao.deleteFlight(flightId);
	}

	@Override
	public BigInteger validateFlightWithId(BigInteger flightId) throws FRSException{
		if(viewFlight(flightId).equals(null))
			throw new FRSException("InvalidFlightId.");
		return flightId;
	}
	
}
