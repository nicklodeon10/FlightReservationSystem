package com.cg.frs.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.Flight;
import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;

public interface ScheduleFlightDao {


	

	ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight);
	 List<ScheduleFlight> viewScheduleFlights(Airport source,Airport destination,LocalDate flightDate);
    ScheduleFlight viewScheduleFlights(BigInteger flightId);
    List<ScheduleFlight > viewScheduleFlight();
    ScheduleFlight modifyScheduleFlight(Flight flight,Schedule schedule,Integer number);
    void deleteScheduleFlight(BigInteger flightId);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
