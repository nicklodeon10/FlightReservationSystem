package com.cg.frs.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.User;
import com.cg.frs.exception.FRSException;
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
	public String homePage() {
		return "Home";
	}

	@RequestMapping(value = "/searchFlight", method = RequestMethod.GET)
	public ModelAndView getFlights(@RequestParam("source_airport") String sourceAirport,
			@RequestParam("destination_airport") String destinationAirport, @RequestParam("doj") String doj)
			throws FRSException {
		Airport source = airportService.viewAirport(sourceAirport);
		Airport destination = airportService.viewAirport(destinationAirport);
		LocalDate journeyDate = LocalDate.parse(doj);
		return new ModelAndView("ViewFlight", "scheduleFlightList",
				scheduleFlightService.viewScheduleFlights(source, destination, journeyDate));
	}

	@RequestMapping(value = "/logIn", method = RequestMethod.GET)
	public String logInPage() {
		return "LogIn";
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String validateLogin(@RequestParam("user_name") String username,
			@RequestParam("user_password") String userpass) {
		if (username.equals("admin")) {
			return "AdminHome";
		} else {
			return "UserHome";
		}
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUpPage(@ModelAttribute("user") User user) {
		return "SignUp";
	}

	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user) {
		user.setUserState(true);
		user.setUserType("customer");
		userService.addUser(user);
		return "LogIn";
	}

	@RequestMapping(value = "/regUserList", method = RequestMethod.GET)
	public String getUserListPage() {
		return "RegUserList";
	}

	@RequestMapping(value = "/addFlight", method = RequestMethod.GET)
	public String getAddFlightPage() {
		return "AddFlight";
	}

	@RequestMapping(value = "/showFlights", method = RequestMethod.GET)
	public String getShowFlightsPage() {
		return "ShowFlights";
	}

	@RequestMapping(value = "searchFlight", method = RequestMethod.GET)
	public String getSearchFlightPage() {
		return "SearchFlight";
	}

	@RequestMapping(value = "/modifyFlight", method = RequestMethod.GET)
	public String getModifyFlightPage() {
		return "ModifyFlight";
	}

	@RequestMapping(value = "/removeFlight", method = RequestMethod.GET)
	public String getRemoveFlightPage() {
		return "RemoveFlight";
	}
	
	@RequestMapping(value = "/scheduleFlight", method = RequestMethod.GET)
	public String addScheduleFlightPage() {
		return "ScheduleFlight";
	}
	
	@RequestMapping(value = "/showScheduledFlights", method = RequestMethod.GET)
	public String getScheduledFlightsPage() {
		return "ShowScheduledFlights";
	}
	
	@RequestMapping(value = "/searchScheduledFlights", method = RequestMethod.GET)
	public String searchScheduledFlightsPage() {
		return "SearchScheduledFlights";
	}
	
	@RequestMapping(value = "/modifyScheduledFlight", method = RequestMethod.GET)
	public String modifyScheduledFlightPage() {
		return "ModifyScheduledFlight";
	}
	
	@RequestMapping(value = "/removeScheduledFlight", method = RequestMethod.GET)
	public String getScheduleFlightPage() {
		return "RemoveScheduledFlight";
	}

}
