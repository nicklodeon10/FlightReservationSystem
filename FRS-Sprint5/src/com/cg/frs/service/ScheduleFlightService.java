package com.cg.frs.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.ScheduleFlight;

public interface ScheduleFlightService {
	
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight);
	
	public List<ScheduleFlight> viewScheduleFlights(Airport source, Airport destination, LocalDate flightDate);
    
	public ScheduleFlight viewScheduleFlights(BigInteger flightId);
    
	public List<ScheduleFlight > viewScheduleFlight();
    
	public ScheduleFlight modifyScheduleFlight(ScheduleFlight scheduleFlight);
    
	public boolean deleteScheduleFlight(BigInteger flightId);
	
}
