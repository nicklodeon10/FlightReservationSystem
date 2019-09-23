package com.cg.frs.service;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dao.ScheduleFlightDao;
import com.cg.frs.dao.ScheduleFlightDaoImpl;
import com.cg.frs.dto.Airport;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FRSException;

public class ScheduleFlightServiceImpl implements ScheduleFlightService {

	ScheduleFlightDao scheduleFlightDao=new ScheduleFlightDaoImpl();

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
			if(scheduleFlight.getFlight().getFlightNumber().equals(flightId))
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
		return scheduleFlightDao.updateScheduleFlight(scheduleFlight);
	}

	@Override
	public boolean deleteScheduleFlight(BigInteger flightId) {
		return scheduleFlightDao.deleteScheduleFlight(flightId);
	}

	@Override
	public BigInteger validateScheduleFlightWithId(BigInteger flightId) throws FRSException{
		if(viewScheduleFlights(flightId).equals(null))
			throw new FRSException("InvalidFlightId.");
		return flightId;
	}
	
}
