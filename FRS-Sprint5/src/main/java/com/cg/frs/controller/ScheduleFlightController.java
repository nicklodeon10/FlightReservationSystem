package com.cg.frs.controller;

import java.math.BigInteger;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FrsException;
import com.cg.frs.exception.InvalidAirportException;
import com.cg.frs.service.AirportService;
import com.cg.frs.service.FlightService;
import com.cg.frs.service.ScheduleFlightService;

@Controller
public class ScheduleFlightController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduleFlightController.class);	
		@Autowired
		HttpSession session;

		@Autowired
		ScheduleFlightService scheduleFlightService;
		
		@Autowired
		FlightService flightService;
		
		@Autowired
		AirportService airportService;
		/*
		 * Author Surya
		 * Created on 08/10/2019
		 * Last modified on 10/10/2019
		 * send to the scheduleflight page
		 */
		@GetMapping(value="/getScheduleFlightPage")
		public String getScheduleFlightPage(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight) {
			return "ScheduleFlight";
		}

		/*
		 * Author Surya
		 * Created on 08/10/2019
		 * Last modified on 10/10/2019
		 * add a scheduleflight
		 * Input ScheduleFlight object
		 * Output ScheduleFlight object
		 */
		@RequestMapping(value = "/addScheduleFlight", method = RequestMethod.POST)
		public ModelAndView addScheduleFlight(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight,
				@RequestParam("source_airport") String source, @RequestParam("destination_airport") String destination,
				@RequestParam("departure_time") String departureTime, @RequestParam("arrival_time") String arrivalTime) {
				Schedule schedule = new Schedule();
				schedule.setScheduleId(scheduleFlight.getScheduleFlightId());
				try {
					schedule.setSourceAirport(airportService.viewAirport(source));
				} catch (InvalidAirportException e) {
					e.printStackTrace();
				}
				try {
					schedule.setDestinationAirport(airportService.viewAirport(destination));
				} catch (InvalidAirportException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				schedule.setDepartureDateTime(LocalDateTime.parse(departureTime));
				schedule.setArrivalDateTime(LocalDateTime.parse(arrivalTime));
				scheduleFlight.setFlight(flightService.searchFlight(scheduleFlight.getScheduleFlightId()));
				scheduleFlight.setSchedule(schedule);
				scheduleFlight.setAvailableSeats(scheduleFlight.getFlight().getSeatCapacity());
				scheduleFlight.setScheduleFlightState(true);
				scheduleFlightService.addScheduleFlight(scheduleFlight);
				return new ModelAndView("ShowScheduledFlights", "scheduledFlightList",
						scheduleFlightService.viewScheduleFlight());
			}
		
		/*
		 * Author Surya
		 * Created on 08/10/2019
		 * Last modified on 10/10/2019
		 * view scheduleflights
		 * Input ---
		 * Output ScheduleFlight object
		 */
		@RequestMapping(value = "/showScheduledFlights", method = RequestMethod.GET)
		public ModelAndView getScheduledFlightsPage() {
				return new ModelAndView("ShowScheduledFlights", "scheduledFlightList",
						scheduleFlightService.viewScheduleFlight());
		}
		/*
		 * Author Surya
		 * Created on 08/10/2019
		 * Last modified on 10/10/2019
		 * modify a scheduleflight
		 * Input ScheduleFlight object
		 * Output ScheduleFlight object
		 */
		
		//Send to Modify Scheduled Flight Page
		@RequestMapping(value = "/modifyScheduledFlight", method = RequestMethod.GET)
		public String modifyScheduledFlightPage(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight) {
				return "ModifyScheduledFlight";
		}
		@RequestMapping(value = "/modifyScheduledFlightSearch", method = RequestMethod.GET)
		public ModelAndView modifyScheduleFlight(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight,
				@RequestParam("modify_schedule_flight_id") BigInteger scheduleFlightId) throws FrsException {
				return new ModelAndView("ModifyScheduledFlight", "scheduledFlightData",
						scheduleFlightService.viewScheduleFlights(scheduleFlightId));
			
		}
		
		@RequestMapping(value = "/scheduledFlightModify", method = RequestMethod.POST)
		public String scheduleFlightModify(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight,
				@RequestParam("source_airport") String source, @RequestParam("destination_airport") String destination,
				@RequestParam("departure_time") String departureTime, @RequestParam("arrival_time") String arrivalTime) throws FrsException {
				Schedule schedule = new Schedule();
				schedule.setScheduleId(scheduleFlight.getScheduleFlightId());
				try {
					schedule.setSourceAirport(airportService.viewAirport(source));
				} catch (InvalidAirportException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					schedule.setDestinationAirport(airportService.viewAirport(destination));
				} catch (InvalidAirportException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				schedule.setDepartureDateTime(LocalDateTime.parse(departureTime));
				schedule.setArrivalDateTime(LocalDateTime.parse(arrivalTime));
				scheduleFlight.setFlight(flightService.searchFlight(scheduleFlight.getScheduleFlightId()));
				scheduleFlight.setSchedule(schedule);
				scheduleFlight.setAvailableSeats(scheduleFlight.getFlight().getSeatCapacity());
				scheduleFlight.setScheduleFlightState(true);
				scheduleFlightService.modifyScheduleFlight(scheduleFlight);
				return "ModifyScheduledFlight";
		}

		/*
		 * Author Surya
		 * Created on 08/10/2019
		 * Last modified on 10/10/2019
		 * remove a scheduleflight
		 * Input BigInteger
		 * Output ScheduleFlight object
		 */
		
		@RequestMapping(value = "/removeScheduledFlight", method = RequestMethod.GET)
		public String getScheduleFlightPage() {
				return "RemoveScheduledFlight";
			
		}
		@RequestMapping(value = "/scheduledFlightRemoveSearch", method = RequestMethod.GET)
		public ModelAndView getRemoveScheduledFlightsSearchResult(
				@RequestParam("scheduled_flight_id") BigInteger scheduledFlightId) throws FrsException {
				return new ModelAndView("RemoveScheduledFlight", "scheduledFlight",
						scheduleFlightService.viewScheduleFlights(scheduledFlightId));
			
		}

		
		@RequestMapping(value = "/scheduledFlightRemove", method = RequestMethod.POST)
		public String scheduledFlightRemove(@RequestParam("scheduled_flight_id") BigInteger scheduledFlightId) throws FrsException {
				scheduleFlightService.deleteScheduleFlight(scheduledFlightId);
				return "RemoveScheduledFlight";
			
		}

}
