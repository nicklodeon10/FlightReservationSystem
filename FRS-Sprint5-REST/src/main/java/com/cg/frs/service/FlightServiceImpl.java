package com.cg.frs.service;
/**
 * @author NAVYA
 *
 */
import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cg.frs.dto.Flight;
import com.cg.frs.exception.FlightExceptions;
import com.cg.frs.dao.FlightRepository;

@Service("flightService")
public class FlightServiceImpl implements FlightService {

	// private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);
	
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
		Flight searched=flightRepository.findByFlightId(flightId);
		if(searched.getFlightState()==true) {
		return flightRepository.findByFlightId(flightId);
		}
		else throw new FlightExceptions("NO FLIGHT OF THIS NAME");
	}

	@Override
	public Flight modifyFlight(Flight flight) throws FlightExceptions {			//modifying flight
		// TODO Auto-generated method stub
		
		Flight flightToBeModified=flightRepository.findByFlightId(flight.getFlightId());
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
		Flight removedFlight=flightRepository.findByFlightId(flightId);
		 if(removedFlight==null) {
				
				throw new FlightExceptions("FLIGHT DOESN'T EXISTS TO DELETE");
				
			}
		removedFlight.setFlightState(false);
		
		flightRepository.save(removedFlight);
		 return true;
	}

	

}
