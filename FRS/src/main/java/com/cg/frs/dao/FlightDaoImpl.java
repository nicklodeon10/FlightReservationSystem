package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.Flight;

public class FlightDaoImpl implements FlightDao {

	private List<Flight> flightList=new ArrayList<Flight>();
	
	public Flight addFlight(Flight flight) {		
		flightList.add(flight);
		return flight;
	}

	public List<Flight> viewFlight() {
		return flightList;
	}

	public void deleteFlight(BigInteger flightId) {
         for(Flight flight:flightList) {
			if(flight.getFlightNumber()==flightId) {
				flightList.remove(flight);
				break;
			}
		}
	}
}