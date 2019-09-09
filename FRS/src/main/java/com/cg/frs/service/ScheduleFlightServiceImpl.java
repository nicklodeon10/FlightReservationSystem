package com.cg.frs.service;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.frs.dto.*;

public class ScheduleFlightServiceImpl implements ScheduleFlightService {

	ScheduleFlightServiceImpl dao=new ScheduleFlightServiceImpl();
	
	

	public List<com.cg.frs.dto.ScheduleFlight> viewScheduleFlights(Airport source, Airport destination,
			LocalDate flightDate) {
		// TODO Auto-generated method stub
		return dao.viewScheduleFlights(source,destination,flightDate);
	}

	public com.cg.frs.dto.ScheduleFlight viewScheduleFlights(BigInteger flightId) {
		// TODO Auto-generated method stub
		return dao.viewScheduleFlights(flightId);
	}

	public List<com.cg.frs.dto.ScheduleFlight> viewScheduleFlight() {
		// TODO Auto-generated method stub
		return dao.viewScheduleFlight();
	}

	public com.cg.frs.dto.ScheduleFlight modifyScheduleFlight(Flight flight, Schedule schedule, Integer number) {
		// TODO Auto-generated method stub
		return dao.modifyScheduleFlight(flight, schedule, number);
	}

	public void deleteScheduleFlight(BigInteger flightId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight) {
		// TODO Auto-generated method stub
		return dao.addScheduleFlight(scheduleflight);
	}

	

	
	
	
	
	
	
	
}
