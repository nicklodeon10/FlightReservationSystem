package com.cg.frs.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	HttpSession session;

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

	//Send to Website Home Page
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage() {
		return "Home";
	}

	//Send to Log in Page
	@RequestMapping(value = "/logIn", method = RequestMethod.GET)
	public String logInPage() {
		return "LogIn";
	}

	//Validate Log in and create session
	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String validateLogin(@RequestParam("user_name") String username,
			@RequestParam("user_password") String userpass) {
		BigInteger currentUser = userService.validateUser(username, userpass);
		if (userService.validateAdminWithId(currentUser)) {
			session.setAttribute("userRole", "admin");
			session.setAttribute("userId", currentUser);
			return "AdminHome";
		} else if (userService.validateCustomerWithId(currentUser)) {
			session.setAttribute("userRole", "customer");
			session.setAttribute("userId", currentUser);
			return "UserHome";
		} else {
			return "InvalidLogin";
		}
	}

	//Destroy Session
	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String logOut() {
		session.setAttribute("userRole", null);
		session.setAttribute("userId", null);
		return "LogIn";
	}

	//Send to Sign Up Page
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String signUpPage(@ModelAttribute("user") User user) {
		return "SignUp";
	}

	//Add User to Repository
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user) {
		user.setUserState(true);
		user.setUserType("customer");
		userService.addUser(user);
		return "UserAdded";
	}
	
	//Get list of User
	@RequestMapping(value = "/regUserList", method = RequestMethod.GET)
	public ModelAndView getUserListPage() {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return new ModelAndView("RegUserList", "userList", userService.viewUser());
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Send to Add Flight Page
	@RequestMapping(value = "/addFlight", method = RequestMethod.GET)
	public String getAddFlightPage(@ModelAttribute("flight") Flight flight) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return "AddFlight";
		} else {
			return "LogIn";
		}
	}

	//Add Flight
	@RequestMapping(value = "/flightAdd", method = RequestMethod.POST)
	public ModelAndView addFlight(@ModelAttribute("flight") Flight flight) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			flight.setFlightState(true);
			flightService.addFlight(flight);
			return new ModelAndView("ShowFlights", "flightList", flightService.viewFlight());
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Send to View Flight Page
	@RequestMapping(value = "/showFlights", method = RequestMethod.GET)
	public ModelAndView getShowFlightsPage() {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return new ModelAndView("ShowFlights", "flightList", flightService.viewFlight());
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Send to Search Flight Page
	@RequestMapping(value = "/searchFlight", method = RequestMethod.GET)
	public String getSearchFlightPage() {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return "SearchFlight";
		} else {
			return "LogIn";
		}
	}

	//Show searched flight
	@RequestMapping(value = "/flightSearch", method = RequestMethod.GET)
	public ModelAndView getSearchFlightsResult(@RequestParam("flight_id") BigInteger flightId) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return new ModelAndView("SearchFlight", "flight", flightService.viewFlight(flightId));
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Send to Modify Flight Page
	@RequestMapping(value = "/modifyFlight", method = RequestMethod.GET)
	public String getModifyFlightPage(@ModelAttribute("flight") Flight flight) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return "ModifyFlight";
		} else {
			return "LogIn";
		}
	}

	//Show Flight Details to edit
	@RequestMapping(value = "/flightEditSearch", method = RequestMethod.GET)
	public ModelAndView getEditFlightsSearchResult(@RequestParam("flight_id") BigInteger flightId,
			@ModelAttribute("flight") Flight flight) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return new ModelAndView("ModifyFlight", "flight", flightService.viewFlight(flightId));
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Save Modifies Flight Values
	@RequestMapping(value = "flightModify", method = RequestMethod.POST)
	public String modifyFlight(@ModelAttribute("flight") Flight flight) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			flightService.modifyFlight(flight);
			return "AdminHome";
		} else {
			return "LogIn";
		}
	}

	//Send to Remove Flight Page
	@RequestMapping(value = "/removeFlight", method = RequestMethod.GET)
	public String getRemoveFlightPage(@ModelAttribute("flight") Flight flight) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return "RemoveFlight";
		} else {
			return "LogIn";
		}
	}

	//Show Flight Details to remove
	@RequestMapping(value = "/flightRemoveSearch", method = RequestMethod.GET)
	public ModelAndView getRemoveFlightsSearchResult(@RequestParam("flight_id") BigInteger flightId,
			@ModelAttribute("flight") Flight flight) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return new ModelAndView("RemoveFlight", "flight", flightService.viewFlight(flightId));
		} else {
			return new ModelAndView("LogIn");
		}
	}

	
	//Remove Flight
	@RequestMapping(value = "/flightRemove", method = RequestMethod.POST)
	public String flightRemove(@RequestParam("flight_id") BigInteger flightId) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			flightService.deleteFlight(flightId);
			return "AdminHome";
		} else {
			return "LogIn";
		}
	}

	//Send to Schedule Flight Page
	@RequestMapping(value = "scheduleFlight", method = RequestMethod.GET)
	public String scheduleFlightPage(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return "ScheduleFlight";
		} else {
			return "LogIn";
		}
	}

	//Add a new scheduled flight
	@RequestMapping(value = "/addScheduleFlight", method = RequestMethod.POST)
	public ModelAndView addScheduleFlight(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight,
			@RequestParam("source_airport") String source, @RequestParam("destination_airport") String destination,
			@RequestParam("departure_time") String departureTime, @RequestParam("arrival_time") String arrivalTime) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
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
			return new ModelAndView("ShowScheduledFlights", "scheduledFlightList",
					scheduleFlightService.viewScheduleFlight());
		} else {
			return new ModelAndView("LogIn");
		}
	}

	
	//Show scheduled flights
	@RequestMapping(value = "/showScheduledFlights", method = RequestMethod.GET)
	public ModelAndView getScheduledFlightsPage() {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return new ModelAndView("ShowScheduledFlights", "scheduledFlightList",
					scheduleFlightService.viewScheduleFlight());
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Send to Search Scheduled Flights Page
	@RequestMapping(value = "/searchScheduledFlights", method = RequestMethod.GET)
	public String searchScheduledFlightsPage() {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return "SearchScheduledFlights";
		} else {
			return "LogIn";
		}
	}

	//Show Scheduled Flight Search results
	@RequestMapping(value = "/scheduledFlightSearch", method = RequestMethod.GET)
	public ModelAndView scheduleFlightSearchResult(@RequestParam("schedule_flight_id") BigInteger scheduleFlightId) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return new ModelAndView("SearchScheduledFlights", "scheduledFlight",
					scheduleFlightService.viewScheduleFlights(scheduleFlightId));
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Send to Modify Scheduled Flight Page
	@RequestMapping(value = "/modifyScheduledFlight", method = RequestMethod.GET)
	public String modifyScheduledFlightPage(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return "ModifyScheduledFlight";
		} else {
			return "LogIn";
		}
	}

	//Get Scheduled Flight Data to modify
	@RequestMapping(value = "/modifyScheduledFlightSearch", method = RequestMethod.GET)
	public ModelAndView modifyScheduleFlight(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight,
			@RequestParam("modify_schedule_flight_id") BigInteger scheduleFlightId) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return new ModelAndView("ModifyScheduledFlight", "scheduledFlightData",
					scheduleFlightService.viewScheduleFlights(scheduleFlightId));
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Sace scheduled flight details
	@RequestMapping(value = "/scheduledFlightModify", method = RequestMethod.POST)
	public String scheduleFlightModify(@ModelAttribute("scheduleFlight") ScheduleFlight scheduleFlight,
			@RequestParam("source_airport") String source, @RequestParam("destination_airport") String destination,
			@RequestParam("departure_time") String departureTime, @RequestParam("arrival_time") String arrivalTime) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
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
		} else {
			return "LogIn";
		}
	}

	
	//Send to Remove Scheduled Flight Page
	@RequestMapping(value = "/removeScheduledFlight", method = RequestMethod.GET)
	public String getScheduleFlightPage() {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return "RemoveScheduledFlight";
		} else {
			return "LogIn";
		}
	}

	//Show scheduled flight data to remove
	@RequestMapping(value = "/scheduledFlightRemoveSearch", method = RequestMethod.GET)
	public ModelAndView getRemoveScheduledFlightsSearchResult(
			@RequestParam("scheduled_flight_id") BigInteger scheduledFlightId) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			return new ModelAndView("RemoveScheduledFlight", "scheduledFlight",
					scheduleFlightService.viewScheduleFlights(scheduledFlightId));
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Remove Scheduled Flight
	@RequestMapping(value = "/scheduledFlightRemove", method = RequestMethod.POST)
	public String scheduledFlightRemove(@RequestParam("scheduled_flight_id") BigInteger scheduledFlightId) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("admin")) {
			scheduleFlightService.deleteScheduleFlight(scheduledFlightId);
			return "AdminHome";
		} else {
			return "LogIn";
		}
	}

	//Send to Add Booking Page
	@RequestMapping(value = "/addBooking", method = RequestMethod.GET)
	public String addBookingPage() {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("customer")) {
			return "AddBooking";
		} else {
			return "LogIn";
		}
	}

	//Show lists of flights for booking
	@RequestMapping(value = "/findFlight", method = RequestMethod.GET)
	public ModelAndView getFlights(@RequestParam("source_airport") String sourceAirport,
			@RequestParam("destination_airport") String destinationAirport, @RequestParam("doj") String doj) {
		Airport source = airportService.viewAirport(sourceAirport);
		Airport destination = airportService.viewAirport(destinationAirport);
		LocalDate journeyDate = LocalDate.parse(doj);
		return new ModelAndView("ViewFlight", "scheduleFlightList",
				scheduleFlightService.viewScheduleFlights(source, destination, journeyDate));
	}

	//Add passengers to current booking
	@RequestMapping(value = "/addPassenger", method = RequestMethod.GET)
	public ModelAndView addPassenger(@RequestParam("schedule_flight_id") BigInteger flightId,
			@ModelAttribute("passenger") Passenger passenger) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("customer")) {
			session.setAttribute("currentFlight", flightId);
			Booking booking = new Booking();
			List<Passenger> passList = new ArrayList<>();
			for (int i = 0; i < 4; i++)
				passList.add(new Passenger());
			booking.setPassengerList(passList);
			return new ModelAndView("AddPassenger", "booking", booking);
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Save Current Booking
	@RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
	public ModelAndView saveBooking(@ModelAttribute("booking") Booking booking) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("customer")) {
			List<Passenger> passList = new ArrayList<>();
			for (Passenger passenger : booking.getPassengerList()) {
				if (!passenger.getPassengerName().equals("")) {
					passenger.setPassengerState(true);
					passList.add(passenger);
				}
			}
			booking.setPassengerList(passList);
			booking.setUserId((BigInteger) session.getAttribute("userId"));
			booking.setBookingState(true);
			booking.setBookingDate(LocalDateTime.now());
			booking.setPassengerCount(passList.size());
			booking.setScheduleFlight(
					scheduleFlightService.viewScheduleFlights((BigInteger) session.getAttribute("currentFlight")));
			booking.setTicketCost((booking.getScheduleFlight().getTicketCost()) * booking.getPassengerCount());
			bookingService.addBooking(booking);
			session.setAttribute("currentFlight", null);
			return new ModelAndView("ShowBooking", "bookings",
					bookingService.viewBooking((BigInteger) session.getAttribute("userId")));
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Send to Show User Bookings Page
	@RequestMapping(value = "/showBooking", method = RequestMethod.GET)
	public ModelAndView showBookingPage() {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("customer")) {
			return new ModelAndView("ShowBooking", "bookings",
					bookingService.viewBooking((BigInteger) session.getAttribute("userId")));
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Send to cancel booking page
	@RequestMapping(value = "/deleteBooking", method = RequestMethod.GET)
	public String getBookingRemovePage(@ModelAttribute("booking") Booking booking) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("customer")) {
			return "DeleteBooking";
		} else {
			return "LogIn";
		}
	}

	//Show booking details to remove
	@RequestMapping(value = "/bookingRemoveSearch", method = RequestMethod.GET)
	public ModelAndView getBookingRemoveSearchResult(@RequestParam("booking_id") BigInteger bookingId,
			@ModelAttribute("booking") Booking booking) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("customer")) {
			return new ModelAndView("DeleteBooking", "booking", bookingService.viewBooking(bookingId).get(0));
		} else {
			return new ModelAndView("LogIn");
		}
	}

	//Remove booking
	@RequestMapping(value = "/bookingRemove", method = RequestMethod.POST)
	public String bookingRemove(@RequestParam("booking_id") BigInteger bookingId) {
		if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals("customer")) {
			bookingService.deleteBooking(bookingId);
			return "UserHome";
		} else {
			return "LogIn";
		}
	}

	//Redirect to Home
	@RequestMapping(value = "/backHome", method = RequestMethod.GET)
	public String toUserHomePage() {
		if (session.getAttribute("userRole").equals("customer"))
			return "UserHome";
		else if (session.getAttribute("userRole").equals("admin")) {
			return "AdminHome";
		} else {
			return "LogIn";
		}
	}

	//Download User Data 
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView getExcel() {
		List<User> userList = userService.viewUser();
		return new ModelAndView(new ExcelReportView(), "userList", userList);
	}

}
