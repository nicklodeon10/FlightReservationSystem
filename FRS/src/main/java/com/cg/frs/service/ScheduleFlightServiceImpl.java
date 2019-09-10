package com.cg.frs.service;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.ScheduleFlight;

public class ScheduleFlightServiceImpl implements ScheduleFlightService {

	ScheduleFlightServiceImpl scheduleFlightDao=new ScheduleFlightServiceImpl();

	@Override
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight) {
		return scheduleFlightDao.addScheduleFlight(scheduleflight);
	}

	@Override
	public List<ScheduleFlight> viewScheduleFlights(Airport source, Airport destination, LocalDate flightDate) {
		List<ScheduleFlight> scheduleFlightList=scheduleFlightDao.viewScheduleFlight();
		List<ScheduleFlight> extractedFlightList=new ArrayList<ScheduleFlight>();
		for(ScheduleFlight scheduleFlight: scheduleFlightList) {
			if(scheduleFlight.getSchedule().getSourceAirport().equals(source) 
					&& scheduleFlight.getSchedule().getDestinationAirport().equals(destination)
					&& scheduleFlight.getSchedule().getDepartureDateTime().toLocalDate().equals(flightDate)) {
				extractedFlightList.add(scheduleFlight);
			}
		}
		return extractedFlightList;
	}

	@Override
	public ScheduleFlight viewScheduleFlights(BigInteger flightId) {
		List<ScheduleFlight> scheduleFlightList=scheduleFlightDao.viewScheduleFlight();
		for(ScheduleFlight scheduleFlight: scheduleFlightList) {
			if(scheduleFlight.getFlight().getFlightNumber()==flightId)
				return scheduleFlight;
		}
		return null;
	}

	@Override
	public List<ScheduleFlight> viewScheduleFlight() {
		return scheduleFlightDao.viewScheduleFlight();
	}

	@Override
	public ScheduleFlight modifyScheduleFlight(ScheduleFlight scheduleFlight) {
		return scheduleFlightDao.addScheduleFlight(scheduleFlight);
	}

	@Override
	public void deleteScheduleFlight(BigInteger flightId) {
		scheduleFlightDao.deleteScheduleFlight(flightId);
	}
	
}
