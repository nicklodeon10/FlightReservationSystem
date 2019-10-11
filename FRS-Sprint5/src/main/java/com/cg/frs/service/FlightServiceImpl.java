/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.frs.dao.FlightDao;
import com.cg.frs.dto.Flight;

/**
 * @author Navya
 *
 */

@Service("flightService")
@Transactional
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightDao flightDao;
	
	
	@Override
	public Flight addFlight(Flight flight) {
		// TODO Auto-generated method stub
		return flightDao.save(flight);
	}

	@Override
	public List<Flight> viewAllFlight() {
		// TODO Auto-generated method stub
		return flightDao.findAll();
	}

	@Override
	public Flight searchFlight(BigInteger flightId) {
		// TODO Auto-generated method stub
		return flightDao.findByFlightNumber(flightId);
	}

	@Override
	public Flight modifyFlight(Flight flight) {
		// TODO Auto-generated method stub
		
		Flight flightToBeModified=flightDao.findByFlightNumber(flight.getFlightNumber());
		flightToBeModified.setCarrierName(flight.getCarrierName());
		flightToBeModified.setFlightModel(flight.getFlightModel());
		flightToBeModified.setSeatCapacity(flight.getSeatCapacity());
		flightToBeModified.setFlightState(true);
		
		return flightToBeModified;
	}

	@Override
	public boolean deleteFlight(BigInteger flightId) {
		// TODO Auto-generated method stub
		Flight removedFlight=flightDao.findByFlightNumber(flightId);
		removedFlight.setFlightState(false);
		
		 flightDao.save(removedFlight);
		 return true;
	}

}
