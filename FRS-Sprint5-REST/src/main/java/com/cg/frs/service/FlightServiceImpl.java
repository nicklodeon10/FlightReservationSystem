/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.cg.frs.dto.Flight;
import com.cg.frs.exception.FlightExceptions;
import com.cg.frs.repository.FlightRepository;


/**
 * @author NAVYA
 *
 */

@Service("flightService")
@Transactional
public class FlightServiceImpl implements FlightService {

	@Autowired
	FlightRepository flightRepository;			//Making repository bean
	
	
	
	@Override
	public Flight saveFlight(Flight flight) {		//adding flight
		// TODO Auto-generated method stub
		
		
		flight.setFlightState(true);
		return flightRepository.save(flight);
	}

	@Override
	public List<Flight> viewAllFlight() throws FlightExceptions {			//showing all flights available
		// TODO Auto-generated method stub
		List<Flight> flightList=flightRepository.viewAll();
		if(flightList.isEmpty()) {
			
			throw new FlightExceptions("NO FLIGHT IS AVAILABLE");
		}
		
		return flightList;
	}

	@Override
	public Flight searchFlight(BigInteger flightId) throws FlightExceptions {		//searching flight with Id
		// TODO Auto-generated method stub
		Flight searched=flightRepository.findByFlightNumber(flightId);
		if(searched.getFlightState()==true) {
		return flightRepository.findByFlightNumber(flightId);
		}
		else throw new FlightExceptions("NO FLIGHT OF THIS NAME");
	}

	@Override
	public Flight modifyFlight(Flight flight) throws FlightExceptions {			//modifying flight
		// TODO Auto-generated method stub
		
		Flight flightToBeModified=flightRepository.findByFlightNumber(flight.getFlightNumber());
        if(flightToBeModified==null) {
			
			throw new FlightExceptions("FLIGHT DOESN'T EXISTS TO MODIFY");
			
		}else {
		flightToBeModified.setCarrierName(flight.getCarrierName());
		flightToBeModified.setFlightModel(flight.getFlightModel());
		flightToBeModified.setSeatCapacity(flight.getSeatCapacity());
		flightToBeModified.setFlightState(true);
		}
		return flightRepository.save(flightToBeModified);
		
	}

	@Override
	public boolean deleteFlight(BigInteger flightId) throws FlightExceptions {		//removing flight
		// TODO Auto-generated method stub
		Flight removedFlight=flightRepository.findByFlightNumber(flightId);
		 if(removedFlight==null) {
				
				throw new FlightExceptions("FLIGHT DOESN'T EXISTS TO DELETE");
				
			}
		removedFlight.setFlightState(false);
		
		flightRepository.save(removedFlight);
		 return true;
	}

}
