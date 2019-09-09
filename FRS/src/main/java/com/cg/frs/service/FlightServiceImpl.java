package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Flight;

public class FlightServiceImpl implements FlightService{

	FlightServiceImpl dao=new FlightServiceImpl();
	
	
	public Flight addFlightService(Flight flight) {
		// TODO Auto-generated method stub
		return dao.addFlightService(flight);
	}

	public Flight modifyFlightService(Flight flight) {
		// TODO Auto-generated method stub
		return dao.modifyFlightService(flight);
	}

	public List<Flight> viewFlight() {
		// TODO Auto-generated method stub
		return dao.viewFlight();
	}

	public List<Flight> viewFlight(BigInteger flightId) {
		// TODO Auto-generated method stub
		return dao.viewFlight(flightId);
	}

	public void deleteFlight(BigInteger flightId) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}
