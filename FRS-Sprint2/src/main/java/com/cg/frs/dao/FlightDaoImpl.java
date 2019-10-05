package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.Flight;

public class FlightDaoImpl implements FlightDao {

	private List<Flight> flightList=new ArrayList<Flight>();
	
	public Flight addFlight(Flight flight) {		
		if(flightList.indexOf(flight)== -1) {
			flightList.add(flight);
		}else {
			int modifyIndex=flightList.indexOf(flight);
			flightList.set(modifyIndex, flight);
		}
		return flight;
	}

	public List<Flight> viewFlight() {
		return flightList;
	}

	public boolean deleteFlight(BigInteger flightId) {
         for(Flight flight:flightList) {
			if(flight.getFlightNumber().equals(flightId)) {
				flightList.remove(flight);
				return true;
			}
		}
        return false;
	}
}