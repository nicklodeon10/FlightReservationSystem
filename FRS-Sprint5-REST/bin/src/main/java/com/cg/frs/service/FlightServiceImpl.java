package com.cg.frs.SpringBootFrs.service;
/**
 * @author Navya
 *
 */
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.SpringBootFrs.dto.Flight;
import com.cg.frs.SpringBootFrs.repository.FlightRepository;

@Service("flightService")
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightRepository flightRepository;			//Making repository bean
	
	
	
	@Override
	public Flight saveFlight(Flight flight) {		//adding flight
		// TODO Auto-generated method stub
		return flightRepository.save(flight);
	}

	@Override
	public List<Flight> viewAllFlight() {			//showing all flights available
		// TODO Auto-generated method stub
		return flightRepository.viewAll();
	}

	@Override
	public Flight searchFlight(BigInteger flightId) {		//searching flight with Id
		// TODO Auto-generated method stub
		return flightRepository.findByFlightId(flightId);
	}

	@Override
	public Flight modifyFlight(Flight flight) {			//modifying flight
		// TODO Auto-generated method stub
		
		Flight flightToBeModified=flightRepository.findByFlightId(flight.getFlightId());
		
		flightToBeModified.setCarrierName(flight.getCarrierName());
		flightToBeModified.setFlightModel(flight.getFlightModel());
		flightToBeModified.setSeatCapacity(flight.getSeatCapacity());
		flightToBeModified.setFlightState(true);
		return flightToBeModified;
		
	}

	@Override
	public boolean deleteFlight(BigInteger flightId) {		//removing flight
		// TODO Auto-generated method stub
		
		Flight removedFlight=flightRepository.findByFlightId(flightId);
		removedFlight.setFlightState(false);
		
		 flightRepository.save(removedFlight);
		 return true;
	}

	

}
