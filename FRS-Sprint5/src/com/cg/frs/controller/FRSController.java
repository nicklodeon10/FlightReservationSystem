package com.cg.frs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cg.frs.service.AirportService;
import com.cg.frs.service.BookingService;
import com.cg.frs.service.FlightService;
import com.cg.frs.service.ScheduleFlightService;
import com.cg.frs.service.UserService;

@Controller
public class FRSController {
	
	@Autowired
	AirportService airportService;
	@Autowired
	BookingService bookingService;
	@Autowired
	FlightService flightService;
	@Autowired
	ScheduleFlightService scheduleFlightService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String loginPage() {
		return "Home";
	}

}
