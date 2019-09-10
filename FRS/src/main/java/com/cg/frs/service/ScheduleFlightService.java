package com.cg.frs.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FRSException;

public interface ScheduleFlightService {
	
	ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight);
	
	List<ScheduleFlight> viewScheduleFlights(Airport source, Airport destination, LocalDate flightDate);
    
	ScheduleFlight viewScheduleFlights(BigInteger flightId);
    
	List<ScheduleFlight > viewScheduleFlight();
    
	ScheduleFlight modifyScheduleFlight(ScheduleFlight scheduleFlight);
    
	void deleteScheduleFlight(BigInteger flightId);
	
	BigInteger validateScheduleFlightWithId(BigInteger flightId) throws FRSException;
	
}
