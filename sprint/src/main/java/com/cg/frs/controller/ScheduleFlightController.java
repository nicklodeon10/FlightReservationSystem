package com.cg.frs.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FrsException;
import com.cg.frs.service.AirportService;
import com.cg.frs.service.ScheduleFlightService;
@RestController
@RequestMapping("/scheduleflight")
public class ScheduleFlightController {

		@Autowired
		ScheduleFlightService scheduleFlightService;
		
		@Autowired
		AirportService airportService;

		//To add a schedule flight
		@PostMapping("/addscheduleflight")
		public ScheduleFlight addScheduleFlight(@RequestBody ScheduleFlight scheduleflight)throws FrsException {
			return scheduleFlightService.addScheduleFlight(scheduleflight);
		}
		
		//To retrieve schedule flights by flightId
		@SuppressWarnings("unchecked")
		@GetMapping("/getscheduleflight")
		public List<ScheduleFlight> getScheduleFlights(@RequestParam("flightId")BigInteger flightId) throws FrsException{
			return (List<ScheduleFlight>) scheduleFlightService.viewScheduleFlights(flightId);
		}
		
		//To cancel a schedule flight
		@DeleteMapping("/deletescheduleflight")
		public boolean cancelScheduleFlight(@RequestParam("flightId")BigInteger flightId) throws FrsException {
			return scheduleFlightService. deleteScheduleFlight(flightId);
		}
}
