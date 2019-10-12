/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FRSException;
import com.cg.frs.exception.FlightNotFoundException;
import com.cg.frs.repository.ScheduleFlightRepository;
import com.cg.frs.repository.ScheduleRepository;

/**
 * @author: DEVANG
 * description: Implementation of the Service interface for Scheduled Flight.
 * created date: 10/10/2019
 * modified: 10/10/2019
 */
@Service("scheduleFlightService")
@Transactional
public class ScheduleFlightServiceImpl implements ScheduleFlightService {

	@Autowired
	ScheduleFlightRepository scheduleFlightRepository;

	@Autowired
	ScheduleRepository scheduleRepository;

	@Override
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight) {
		return scheduleFlightRepository.save(scheduleflight);
	}

	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves a list of scheduled flights against the given inputs.
	 *  Input: Source Airport, Destination Airport, Date
	 *  Output: List of Scheduled Flights.
	 *  Created Date: 10/10/2019
	 *  Last Modified: 10/10/2019
	 */
	@Override
	public List<ScheduleFlight> viewScheduleFlights(Airport source, Airport destination, LocalDate flightDate)
			throws FlightNotFoundException {
		List<ScheduleFlight> scheduleFlightList=scheduleFlightRepository.findAll();
		List<ScheduleFlight> extractedFlightList=new ArrayList<>();
		for(ScheduleFlight scheduleFlight: scheduleFlightList) {
			if(scheduleFlight.getSchedule().getSourceAirport().equals(source) 
					&& scheduleFlight.getSchedule().getDestinationAirport().equals(destination)
					&& scheduleFlight.getSchedule().getDepartureDateTime().toLocalDate().equals(flightDate)) {
				extractedFlightList.add(scheduleFlight);
			}
		}
		if(extractedFlightList.size()==0)
			throw new FlightNotFoundException("No Flights Found");
		return extractedFlightList;
	}

	@Override
	public ScheduleFlight viewScheduleFlights(BigInteger flightId) throws FlightNotFoundException {
		if (flightId == null)
			throw new FlightNotFoundException("Enter flight Id");
		ScheduleFlight scheduleFlight = scheduleFlightRepository.getOne(flightId);
		if (scheduleFlight == null)
			throw new FlightNotFoundException("Enter a valid Flight Id");
		else
			return scheduleFlight;
	}

	@Override
	public List<ScheduleFlight> viewScheduleFlight() {
		return scheduleFlightRepository.findAll();
	}

	@Override
	public ScheduleFlight modifyScheduleFlight(ScheduleFlight scheduleFlight) {
		ScheduleFlight updateScheduleFlight = scheduleFlightRepository.getOne(scheduleFlight.getScheduleFlightId());
		Schedule updateSchedule = scheduleRepository.getOne(scheduleFlight.getSchedule().getScheduleId());
		updateScheduleFlight.setAvailableSeats(scheduleFlight.getAvailableSeats());
		updateScheduleFlight.setTicketCost(scheduleFlight.getTicketCost());
		updateSchedule.setSourceAirport(scheduleFlight.getSchedule().getSourceAirport());
		updateSchedule.setDestinationAirport(scheduleFlight.getSchedule().getDestinationAirport());
		updateSchedule.setArrivalDateTime(scheduleFlight.getSchedule().getArrivalDateTime());
		updateSchedule.setDepartureDateTime(scheduleFlight.getSchedule().getDepartureDateTime());
		return scheduleFlight;
	}

	@Override
	public boolean deleteScheduleFlight(BigInteger flightId) throws FRSException {
		if (flightId == null)
			throw new FRSException("Enter flight Id");
		ScheduleFlight scheduleFlight = scheduleFlightRepository.getOne(flightId);
		if (scheduleFlight == null)
			throw new FRSException("Enter a valid Flight Id");
		else
			scheduleFlightRepository.deleteById(flightId);
		return false;
	}

}
