package com.cg.frs.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.Flight;
import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.dto.User;
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

	private static BigInteger currentUser;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage() {
		return "Home";
	}

	@RequestMapping(value = "/findFlight", method = RequestMethod.GET)
	public ModelAndView getFlights(@RequestParam("source_airport") String sourceAirport,
			@RequestParam("destination_airport") String destinationAirport, @RequestParam("doj") String doj) {
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
		currentUser = userService.validateUser(username, userpass);
		if (userService.validateAdminWithId(currentUser)) {
			return "AdminHome";
		} else if (userService.validateCustomerWithId(currentUser)) {
			return "UserHome";
		} else {
			return "InvalidLogin";
		}
	}

	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String logOut() {
		currentUser = null;
		return "Home";
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
	public ModelAndView getUserListPage() {
		if (userService.validateAdminWithId(currentUser)) {
			return new ModelAndView("RegUserList", "userList", userService.viewUser());
		} else {
			return new ModelAndView("LogIn");
		}
	}

	@RequestMapping(value = "/addFlight", method = RequestMethod.GET)
	public String getAddFlightPage(@ModelAttribute("flight") Flight flight) {
		if (userService.validateAdminWithId(currentUser)) {
			return "AddFlight";
		} else {
			return "LogIn";
		}
	}

	@RequestMapping(value = "/flightAdd", method = RequestMethod.POST)
	public String addFlight(@ModelAttribute("flight") Flight flight) {
		if (userService.validateAdminWithId(currentUser)) {
			flight.setFlightState(true);
			flightService.addFlight(flight);
			return "AdminHome";
		} else {
			return "LogIn";
		}
	}

	@RequestMapping(value = "/showFlights", method = RequestMethod.GET)
	public ModelAndView getShowFlightsPage() {
		if (userService.validateAdminWithId(currentUser)) {
			return new ModelAndView("ShowFlights", "flightList", flightService.viewFlight());
		} else {
			return new ModelAndView("LogIn");
		}
	}

	@RequestMapping(value = "/searchFlight", method = RequestMethod.GET)
	public String getSearchFlightPage() {
		if (userService.validateAdminWithId(currentUser)) {
			return "SearchFlight";
		} else {
			return "LogIn";
		}
	}

	@RequestMapping(value = "/flightSearch", method = RequestMethod.GET)
	public ModelAndView getSearchFlightsResult(@RequestParam("flight_id") BigInteger flightId) {
		if (userService.validateAdminWithId(currentUser)) {
			return new ModelAndView("SearchFlight", "flight", flightService.viewFlight(flightId));
		} else {
			return new ModelAndView("LogIn");
		}
	}

	@RequestMapping(value = "/modifyFlight", method = RequestMethod.GET)
	public String getModifyFlightPage(@ModelAttribute("flight") Flight flight) {
		return "ModifyFlight";
	}

	@RequestMapping(value = "/flightEditSearch", method = RequestMethod.GET)
	public ModelAndView getEditFlightsSearchResult(@RequestParam("flight_id") BigInteger flightId,
			@ModelAttribute("flight") Flight flight) {
		if (userService.validateAdminWithId(currentUser)) {
			return new ModelAndView("ModifyFlight", "flight", flightService.viewFlight(flightId));
		} else {
			return new ModelAndView("LogIn");
		}
	}

	@RequestMapping(value = "flightModify", method = RequestMethod.POST)
	public String modifyFlight(@ModelAttribute("flight") Flight flight) {
		if (userService.validateAdminWithId(currentUser)) {
			flightService.modifyFlight(flight);
			return "AdminHome";
		} else {
			return "LogIn";
		}
	}

	@RequestMapping(value = "/removeFlight", method = RequestMethod.GET)
	public String getRemoveFlightPage(@ModelAttribute("flight") Flight flight) {
		return "RemoveFlight";
	}

	@RequestMapping(value = "/flightRemoveSearch", method = RequestMethod.GET)
	public ModelAndView getRemoveFlightsSearchResult(@RequestParam("flight_id") BigInteger flightId,
			@ModelAttribute("flight") Flight flight) {
		if (userService.validateAdminWithId(currentUser)) {
			return new ModelAndView("RemoveFlight", "flight", flightService.viewFlight(flightId));
		} else {
			return new ModelAndView("LogIn");
		}
	}

	@RequestMapping(value = "/flightRemove", method = RequestMethod.POST)
	public String flightRemove(@RequestParam("flight_id") BigInteger flightId) {
		if (userService.validateAdminWithId(currentUser)) {
			flightService.deleteFlight(flightId);
			return "AdminHome";
		} else {
			return "LogIn";
		}
	}

	@RequestMapping(value = "scheduleFlight", method = RequestMethod.GET)
	public String scheduleFlightPage(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight,
			@ModelAttribute("schedule") Schedule schedule) {
		if (userService.validateAdminWithId(currentUser)) {
			return "ScheduleFlight";
		} else {
			return "LogIn";
		}
	}

	@RequestMapping(value = "/addScheduleFlight", method = RequestMethod.POST)
	public String addScheduleFlight(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight,
			@RequestParam("source_airport") String source, @RequestParam("destination_airport") String destination,
			@RequestParam("departure_time") String departureTime, @RequestParam("arrival_time") String arrivalTime) {
		if (userService.validateAdminWithId(currentUser)) {
			Schedule schedule = new Schedule();
			schedule.setScheduleId(scheduleFlight.getScheduleFlightId());
			schedule.setSourceAirport(airportService.viewAirport(source));
			schedule.setDestinationAirport(airportService.viewAirport(destination));
			schedule.setDepartureDateTime(LocalDateTime.parse(departureTime));
			schedule.setArrivalDateTime(LocalDateTime.parse(arrivalTime));
			scheduleFlight.setFlight(flightService.viewFlight(scheduleFlight.getScheduleFlightId()));
			scheduleFlight.setSchedule(schedule);
			scheduleFlight.setAvailableSeats(scheduleFlight.getFlight().getSeatCapacity());
			scheduleFlight.setScheduleFlightState(true);
			scheduleFlightService.addScheduleFlight(scheduleFlight);
			return "AdminHome";
		} else {
			return "LogIn";
		}
	}

	@RequestMapping(value = "/showScheduledFlights", method = RequestMethod.GET)
	public ModelAndView getScheduledFlightsPage() {
		return new ModelAndView("ShowScheduledFlights","scheduledFlightList", scheduleFlightService.viewScheduleFlight());
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
