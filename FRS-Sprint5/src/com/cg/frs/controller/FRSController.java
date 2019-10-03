package com.cg.frs.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Flight;
import com.cg.frs.dto.Passenger;
import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.dto.User;
import com.cg.frs.service.AirportService;
import com.cg.frs.service.BookingService;
import com.cg.frs.service.ExcelReportView;
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
	private static BigInteger currentFlight;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage() {
		return "Home";
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
		currentFlight = null;
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
		return new ModelAndView("RegUserList", "userList", userService.viewUser());

	}

	@RequestMapping(value = "/addFlight", method = RequestMethod.GET)
	public String getAddFlightPage(@ModelAttribute("flight") Flight flight) {
		return "AddFlight";

	}

	@RequestMapping(value = "/flightAdd", method = RequestMethod.POST)
	public String addFlight(@ModelAttribute("flight") Flight flight) {
		flight.setFlightState(true);
		flightService.addFlight(flight);
		return "AdminHome";

	}

	@RequestMapping(value = "/showFlights", method = RequestMethod.GET)
	public ModelAndView getShowFlightsPage() {
		return new ModelAndView("ShowFlights", "flightList", flightService.viewFlight());

	}

	@RequestMapping(value = "/searchFlight", method = RequestMethod.GET)
	public String getSearchFlightPage() {
		return "SearchFlight";
	}

	@RequestMapping(value = "/flightSearch", method = RequestMethod.GET)
	public ModelAndView getSearchFlightsResult(@RequestParam("flight_id") BigInteger flightId) {
		return new ModelAndView("SearchFlight", "flight", flightService.viewFlight(flightId));
	}

	@RequestMapping(value = "/modifyFlight", method = RequestMethod.GET)
	public String getModifyFlightPage(@ModelAttribute("flight") Flight flight) {
		return "ModifyFlight";
	}

	@RequestMapping(value = "/flightEditSearch", method = RequestMethod.GET)
	public ModelAndView getEditFlightsSearchResult(@RequestParam("flight_id") BigInteger flightId,
			@ModelAttribute("flight") Flight flight) {
		return new ModelAndView("ModifyFlight", "flight", flightService.viewFlight(flightId));
	}

	@RequestMapping(value = "flightModify", method = RequestMethod.POST)
	public String modifyFlight(@ModelAttribute("flight") Flight flight) {
		flightService.modifyFlight(flight);
		return "AdminHome";
	}

	@RequestMapping(value = "/removeFlight", method = RequestMethod.GET)
	public String getRemoveFlightPage(@ModelAttribute("flight") Flight flight) {
		return "RemoveFlight";
	}

	@RequestMapping(value = "/flightRemoveSearch", method = RequestMethod.GET)
	public ModelAndView getRemoveFlightsSearchResult(@RequestParam("flight_id") BigInteger flightId,
			@ModelAttribute("flight") Flight flight) {
		return new ModelAndView("RemoveFlight", "flight", flightService.viewFlight(flightId));
	}

	@RequestMapping(value = "/flightRemove", method = RequestMethod.POST)
	public String flightRemove(@RequestParam("flight_id") BigInteger flightId) {
		flightService.deleteFlight(flightId);
		return "AdminHome";
	}

	@RequestMapping(value = "scheduleFlight", method = RequestMethod.GET)
	public String scheduleFlightPage(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight) {
		return "ScheduleFlight";
	}

	@RequestMapping(value = "/addScheduleFlight", method = RequestMethod.POST)
	public String addScheduleFlight(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight,
			@RequestParam("source_airport") String source, @RequestParam("destination_airport") String destination,
			@RequestParam("departure_time") String departureTime, @RequestParam("arrival_time") String arrivalTime) {
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

	}

	@RequestMapping(value = "/showScheduledFlights", method = RequestMethod.GET)
	public ModelAndView getScheduledFlightsPage() {
		return new ModelAndView("ShowScheduledFlights", "scheduledFlightList",
				scheduleFlightService.viewScheduleFlight());
	}

	@RequestMapping(value = "/searchScheduledFlights", method = RequestMethod.GET)
	public String searchScheduledFlightsPage() {
		return "SearchScheduledFlights";
	}

	@RequestMapping(value = "/scheduledFlightSearch", method = RequestMethod.GET)
	public ModelAndView scheduleFlightSearchResult(@RequestParam("schedule_flight_id") BigInteger scheduleFlightId) {
		return new ModelAndView("SearchScheduledFlights", "scheduledFlight",
				scheduleFlightService.viewScheduleFlights(scheduleFlightId));
	}

	@RequestMapping(value = "/modifyScheduledFlight", method = RequestMethod.GET)
	public String modifyScheduledFlightPage(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight) {
		return "ModifyScheduledFlight";
	}

	@RequestMapping(value = "/modifyScheduledFlightSearch", method = RequestMethod.GET)
	public ModelAndView modifyScheduleFlight(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight,
			@RequestParam("modify_schedule_flight_id") BigInteger scheduleFlightId) {
		return new ModelAndView("ModifyScheduledFlight", "scheduledFlightData",
				scheduleFlightService.viewScheduleFlights(scheduleFlightId));
	}

	@RequestMapping(value = "/scheduledFlightModify", method = RequestMethod.POST)
	public String scheduleFlightModify(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight,
			@RequestParam("source_airport") String source, @RequestParam("destination_airport") String destination,
			@RequestParam("departure_time") String departureTime, @RequestParam("arrival_time") String arrivalTime) {
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
		scheduleFlightService.modifyScheduleFlight(scheduleFlight);
		return "AdminHome";
	}

	@RequestMapping(value = "/removeScheduledFlight", method = RequestMethod.GET)
	public String getScheduleFlightPage() {
		return "RemoveScheduledFlight";
	}

	@RequestMapping(value = "/scheduledFlightRemoveSearch", method = RequestMethod.GET)
	public ModelAndView getRemoveScheduledFlightsSearchResult(
			@RequestParam("scheduled_flight_id") BigInteger scheduledFlightId) {
		return new ModelAndView("RemoveScheduledFlight", "scheduledFlight",
				scheduleFlightService.viewScheduleFlights(scheduledFlightId));
	}

	@RequestMapping(value = "/scheduledFlightRemove", method = RequestMethod.POST)
	public String scheduledFlightRemove(@RequestParam("scheduled_flight_id") BigInteger scheduledFlightId) {
		scheduleFlightService.deleteScheduleFlight(scheduledFlightId);
		return "AdminHome";
	}

	@RequestMapping(value = "/addBooking", method = RequestMethod.GET)
	public String addBookingPage() {
		return "AddBooking";
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

	@RequestMapping(value = "/addPassenger", method = RequestMethod.GET)
	public ModelAndView addPassenger(@RequestParam("schedule_flight_id") BigInteger flightId,
			@ModelAttribute("passenger") Passenger passenger) {
		if (currentUser == null) {
			return new ModelAndView("LoginFirst");
		} else {
			currentFlight = flightId;
			Booking booking = new Booking();
			List<Passenger> passList = new ArrayList<>();
			for (int i = 0; i < 4; i++)
				passList.add(new Passenger());
			booking.setPassengerList(passList);
			return new ModelAndView("AddPassenger", "booking", booking);
		}
	}

	@RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
	public String saveBooking(@ModelAttribute("booking") Booking booking) {
		List<Passenger> passList = new ArrayList<>();
		for (Passenger passenger : booking.getPassengerList()) {
			if (!passenger.getPassengerName().equals("")) {
				passenger.setPassengerState(true);
				passList.add(passenger);
			}
		}
		booking.setPassengerList(passList);
		booking.setUserId(currentUser);
		booking.setBookingState(true);
		booking.setBookingDate(LocalDateTime.now());
		booking.setPassengerCount(passList.size());
		booking.setScheduleFlight(scheduleFlightService.viewScheduleFlights(currentFlight));
		booking.setTicketCost((booking.getScheduleFlight().getTicketCost()) * booking.getPassengerCount());
		bookingService.addBooking(booking);
		currentFlight = null;
		return "UserHome";
	}

	@RequestMapping(value = "/showBooking", method = RequestMethod.GET)
	public ModelAndView showBookingPage() {
		return new ModelAndView("ShowBooking", "bookings", bookingService.viewBooking(currentUser));
	}

	@RequestMapping(value = "/deleteBooking", method = RequestMethod.GET)
	public String getBookingRemovePage(@ModelAttribute("booking") Booking booking) {
		return "DeleteBooking";
	}

	@RequestMapping(value = "/bookingRemoveSearch", method = RequestMethod.GET)
	public ModelAndView getBookingRemoveSearchResult(@RequestParam("booking_id") BigInteger bookingId,
			@ModelAttribute("booking") Booking booking) {
		return new ModelAndView("DeleteBooking", "booking", bookingService.viewBooking(bookingId).get(0));
	}

	@RequestMapping(value = "/bookingRemove", method = RequestMethod.POST)
	public String bookingRemove(@RequestParam("booking_id") BigInteger bookingId) {
		bookingService.deleteBooking(bookingId);
		return "UserHome";
	}

	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public ModelAndView
	 * getExcel() { List<User> userList = new ArrayList<User>(); // call the service
	 * method for getting userlist,include vaue parameter in // request mapping//
	 * return new ModelAndView(new ExcelReportView(), "userList", userList); }
	 */

}
