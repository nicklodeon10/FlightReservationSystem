package com.cg.frs.service;
import com.cg.frs.dto.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.frs.dto.Flight;

public interface ScheduleFlightService {

	
	ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight);
	 List<ScheduleFlight> viewScheduleFlights(Airport source,Airport destination,LocalDate flightDate);
    ScheduleFlight viewScheduleFlights(BigInteger flightId);
    List<ScheduleFlight > viewScheduleFlight();
    ScheduleFlight modifyScheduleFlight(Flight flight,Schedule schedule,Integer number);
    void deleteScheduleFlight(BigInteger flightId);
	
}
