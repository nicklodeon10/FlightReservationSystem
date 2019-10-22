package com.cg.frs.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FlightExceptions;
import com.cg.frs.exception.FrsException;
import com.cg.frs.exception.InvalidAirportException;
import com.cg.frs.service.AirportService;
import com.cg.frs.service.FlightService;
import com.cg.frs.service.ScheduleFlightService;
import com.cg.frs.service.UserService;

@RestController
@RequestMapping("/scheduleFlight")
@CrossOrigin(origins = "http://localhost:4200")
public class ScheduleFlightController {

		@Autowired
		ScheduleFlightService scheduleFlightService;
		
		@Autowired
		FlightService flightService;
		
		@Autowired
		AirportService airportService;
		@Autowired
		UserService userService;
		/*
		 * Author Surya
		 * Created on 08/10/2019
		 * Last modified on 10/10/2019
		 * To add a schedule flight
		 * Input ScheduleFlight object
		 * Output ScheduleFlight object
		 */
		@PostMapping(value = "/add")
		public ResponseEntity<ScheduleFlight> addScheduleFlight(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight,
				@RequestParam("source_airport") String source, @RequestParam("destination_airport") String destination,
				@RequestParam("departure_time") String departureTime, @RequestParam("arrival_time") String arrivalTime) {
			Schedule schedule = new Schedule();
			schedule.setScheduleId(scheduleFlight.getScheduleFlightId());
			try {
				schedule.setSourceAirport(airportService.viewAirport(source));
			} catch (InvalidAirportException e) {
				return new ResponseEntity("Airport Not Found", HttpStatus.BAD_REQUEST);
			}
			try {
				schedule.setDestinationAirport(airportService.viewAirport(destination));
			} catch (InvalidAirportException e) {
				return new ResponseEntity("Airport Not Found", HttpStatus.BAD_REQUEST);
			}
			schedule.setDepartureDateTime(LocalDateTime.parse(departureTime));
			schedule.setArrivalDateTime(LocalDateTime.parse(arrivalTime));
			try {
				scheduleFlight.setFlight(flightService.searchFlight(scheduleFlight.getScheduleFlightId()));
			} catch (FlightExceptions e1) {
				return new ResponseEntity("Flight Not Found", HttpStatus.BAD_REQUEST);
			}
			scheduleFlight.setSchedule(schedule);
			scheduleFlight.setAvailableSeats(scheduleFlight.getFlight().getSeatCapacity());
			scheduleFlight.setScheduleFlightState(true);
			try {
				return new ResponseEntity<ScheduleFlight>(scheduleFlightService.addScheduleFlight(scheduleFlight), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity("Error adding Flight."+e, HttpStatus.BAD_REQUEST);
			}
		}

		
		/*
		 * Author Surya
		 * Created on 08/10/2019
		 * Last modified on 10/10/2019
		 * To retrieve list of schedule flights
		 * Input BigInteger
		 * Output ScheduleFlight list
		 */
		
		@GetMapping(value = "/showdata")
		public ResponseEntity<List<ScheduleFlight>> getScheduleFlights() {							

			List<ScheduleFlight> sFlightList = scheduleFlightService.viewScheduleFlight();
			if (sFlightList.isEmpty()) {
				return new ResponseEntity("No Data Present", HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<List<ScheduleFlight>>(sFlightList, HttpStatus.OK);
			}
		}
		/*
		 * Author Surya
		 * Created on 08/10/2019
		 * Last modified on 10/10/2019
		 * To search a schedule flight
		 * Input BigInteger
		 * Output ScheduleFlight list
		 */
		@GetMapping(value="/search")
		public ResponseEntity<ScheduleFlight> searchScheduleFlight(@RequestParam BigInteger flightId) throws FrsException{					
			ScheduleFlight searchSFlight=scheduleFlightService.viewScheduleFlights(flightId);
			
			if (searchSFlight == null) {
				return new ResponseEntity("Flight not present", HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<ScheduleFlight>(searchSFlight, HttpStatus.OK);
			}
			
		}
		
		/*
		 * Author Surya
		 * Created on 08/10/2019
		 * Last modified on 10/10/2019
		 * To modify a schedule flight
		 * Input BigInteger
		 * Output ScheduleFlight list
		 */
		
		@PutMapping(value="/modify")
		public ResponseEntity<ScheduleFlight> modifyScheduleFlight(@ModelAttribute ScheduleFlight scheduleFlight){			
			
			ScheduleFlight modifySFlight=scheduleFlightService.modifyScheduleFlight(scheduleFlight);
			
			if (modifySFlight == null) {
				return new ResponseEntity("Flight not modified", HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<ScheduleFlight>(modifySFlight, HttpStatus.OK);
			}
			
		}

		
		/*
		 * Author Surya
		 * Created on 08/10/2019
		 * Last modified on 10/10/2019
		 * To cancel a schedule flight
		 * Input BigInteger
		 * Output boolean
		 */
		@PostMapping(value="/delete")
		public boolean deleteScheduleFlight(@RequestParam BigInteger flightId) throws FrsException{					//removing flight
			
			boolean deleteSFlight=scheduleFlightService.deleteScheduleFlight(flightId);
			
			return deleteSFlight;
			
		}
}