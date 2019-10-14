package com.cg.frs.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.FlightReservationSystemApplication;
import com.cg.frs.dto.Airport;
import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FlightNotFoundException;
import com.cg.frs.exception.FrsException;
import com.cg.frs.exception.InvalidBookingException;
import com.cg.frs.repository.ScheduleFlightRepository;
import com.cg.frs.repository.ScheduleRepository;

@Service("scheduleflightservice")
public class ScheduleFlightServiceImpl implements ScheduleFlightService {
	
	@Autowired
	ScheduleFlightRepository scheduleFlightRepository;
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Autowired
	BookingService bookingService;
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduleFlightServiceImpl.class);

	/*
	 * Author Surya Created on 08/10/2019 Last modified on 10/10/2019 add a
	 * scheduleflight Input ScheduleFlight object Output ScheduleFlight object
	 */
	@Override
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight) throws Exception {
		return scheduleFlightRepository.save(scheduleflight);
	}

	/*
	 * Author: SURYA,DEVANG Description: Retrieves a list of scheduled flights against the
	 * given inputs. Input: Source Airport, Destination Airport, Date Output: List
	 * of Scheduled Flights. Created Date: 10/10/2019 Last Modified: 10/10/2019
	 */
	@Override
	public List<ScheduleFlight> viewScheduleFlights(Airport source, Airport destination, LocalDate flightDate)
			throws FlightNotFoundException {
		List<ScheduleFlight> scheduleFlightList=scheduleFlightRepository.findAll();
		logger.info("Retrieved list of all scheduled flights.");
		List<ScheduleFlight> extractedFlightList=new ArrayList<>();
		for(ScheduleFlight scheduleFlight: scheduleFlightList) {
			if(scheduleFlight.getSchedule().getSourceAirport().equals(source) 
					&& scheduleFlight.getSchedule().getDestinationAirport().equals(destination)
					&& scheduleFlight.getSchedule().getDepartureDateTime().toLocalDate().equals(flightDate)) {
				extractedFlightList.add(scheduleFlight);
			}
		}
		logger.info("Extracted list of scheduled flights as per parameters.");
		if(extractedFlightList.size()==0) {
			logger.error("No Flights Found.");
			throw new FlightNotFoundException("No Flights Found");
		}
		logger.info("Returning list of scheduled flights.");
		return extractedFlightList;
	}

	/*
	 * Author Surya Created on 08/10/2019 Last modified on 10/10/2019 view a
	 * scheduleflight Input BigInteger Output ScheduleFlight object
	 */
	@Override
	public ScheduleFlight viewScheduleFlights(BigInteger flightId) throws FrsException {
		if (flightId == null)
			throw new FrsException("Enter flight Id");
		ScheduleFlight scheduleFlight = scheduleFlightRepository.getOne(flightId);
		if (scheduleFlight == null)
			throw new FrsException("Enter a valid Flight Id");
		else
			return scheduleFlight;
	}

	/*
	 * Author Surya Created on 08/10/2019 Last modified on 10/10/2019 view
	 * scheduleflights Input --- Output ScheduleFlight list
	 */
	@Override
	public List<ScheduleFlight> viewScheduleFlight() {
		return scheduleFlightRepository.findAll();
	}

	/*
	 * Author Surya Created on 08/10/2019 Last modified on 10/10/2019 modify a
	 * scheduleflight Input ScheduleFlight object Output ScheduleFlight object
	 */
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

	/*
	 * Author Surya Created on 08/10/2019 Last modified on 10/10/2019 delete a
	 * scheduleflight Input BigInteger Output boolean
	 */
	@Override
	public boolean deleteScheduleFlight(BigInteger flightId) throws FrsException {
 		if(flightId==null)
			throw new FrsException("Enter flight Id");
		ScheduleFlight scheduleFlight=scheduleFlightRepository.findById(flightId).get();
		if(scheduleFlight==null)
			throw new FrsException("Enter a valid Flight Id");
		else {
			try {
				cancelBookings(flightId);
			} catch (InvalidBookingException e) {
				logger.info("No Bookings Found");
			}
			logger.info("Retrieved Scheduled Flight by Flight id: " + flightId);
			scheduleFlight.setScheduleFlightState(false);;
			scheduleFlightRepository.save(scheduleFlight);
			logger.info("Cancelled Scheduled Flight.");
		}
		return true;
	}

	@Override
	public boolean cancelBookings(BigInteger flightId)throws InvalidBookingException {
		List<Booking> bookingList=bookingService.viewBooking();
		for(Booking booking: bookingList) {
			if(booking.getScheduleFlight().getScheduleFlightId().equals(flightId)) {
				bookingService.deleteBooking(booking.getBookingId());
			}
		}
		return true;
	}
	
	

}
