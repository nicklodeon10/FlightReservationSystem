/**
 *	
 */
package com.cg.frs.controller;

import java.math.BigInteger;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Passenger;
import com.cg.frs.exception.FRSException;
import com.cg.frs.exception.FlightNotFoundException;
import com.cg.frs.exception.InvalidAirportException;
import com.cg.frs.exception.InvalidBookingException;
import com.cg.frs.exception.UserNotFoundException;
import com.cg.frs.service.AirportService;
import com.cg.frs.service.BookingService;
import com.cg.frs.service.ScheduleFlightService;
import com.cg.frs.service.UserService;

/**
 * @author: DEVANG
 * description: Controller for Bookings 
 * created date: 09/10/2019
 * modified: 12/10/2019
 */

@Controller
public class BookingController {

	@Autowired
	HttpSession session;

	@Autowired
	BookingService bookingService;

	@Autowired
	AirportService airportService;

	@Autowired
	ScheduleFlightService scheduleFlightService;
	
	@Autowired 
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	/*	
	 *  Author: DEVANG
	 *  Description: Sends the home page view to the client
	 *  Input: -
	 *  Output: Home Page ModelAndView
	 *  Created Date: 11/10/2019
	 *  Last Modified: -
	 */
	@GetMapping("/")
	public ModelAndView home() {
		logger.info("Returning Home View.");
		return new ModelAndView("Home", "airportList", airportService.viewAirport());
	}
	
	/*	
	 *  Author: DEVANG
	 *  Description: Sends the admin panel view to the admin
	 *  Input: -
	 *  Output: -
	 *  Created Date: 11/10/2019
	 *  Last Modified: -
	 */
	@GetMapping("/admin")
	public String adminPanel(Principal principal) {
		logger.info("Returning Admin Panel View for admin: "+principal.getName());
		return "AdminPanel";
	}
	
	/*	
	 *  Author: DEVANG
	 *  Description: Sends the user panel view to the user
	 *  Input: -
	 *  Output: -
	 *  Created Date: 11/10/2019
	 *  Last Modified: -
	 */
	@GetMapping("/user")
	public String userPanel(Principal principal) {
		logger.info("Returning User Panel View for user: "+principal.getName());
		return "UserPanel";
	}
	
	/*	
	 *  Author: DEVANG
	 *  Description: Sends the flight search view to the user
	 *  Input: -
	 *  Output: Add Booking Page ModelAndView
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@GetMapping("/booking/add")
	public ModelAndView bookingSearch() {
		logger.info("Returning Booking Search View.");
		return new ModelAndView("AddBooking", "airportList", airportService.viewAirport());
	}
	
	/*	
	 *  Author: DEVANG
	 *  Description: Shows the flights available as per the search parameters.
	 *  Input: Source Airport Code, Destination Airport Code, Date of Journey 
	 *  Output: Available Flights Page ModelAndView
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@PostMapping("/booking/find")
	public ModelAndView flightSearch(@RequestParam("source_airport") String srcCode,
			@RequestParam("destination_airport") String destCode, @RequestParam("journeydate") String doj)
			throws InvalidAirportException {
		logger.info("Preparing Flight Search Parameters.");
		Airport sourceAirport = airportService.viewAirport(srcCode);
		Airport destinationAirport = airportService.viewAirport(destCode);
		LocalDate journeyDate = LocalDate.parse(doj);
		logger.info("Prepared Flight Search Parameters.");
		airportService.compareAirport(sourceAirport, destinationAirport);
		logger.info("Returning View Flights View.");
		try {
			return new ModelAndView("ViewFlights", "scheduleFlightList",
					scheduleFlightService.viewScheduleFlights(sourceAirport, destinationAirport, journeyDate));
		} catch (FlightNotFoundException e) {
			return new ModelAndView("NoFlights");
		}
	}
	
	/*	
	 *  Author: DEVANG
	 *  Description: Shows the view of adding booking details for the selected flight
	 *  Input: Flight Id of the selected flight
	 *  Output: Add Details ModelAndView
	 *  Created Date: 09/10/2019
	 *  Last Modified: - 
	 */
	@GetMapping("/booking/addDetails")
	public ModelAndView addDetailsPage(@RequestParam("schedule_flight_id") BigInteger scheduleFlightId) {
		Booking booking = new Booking();
		List<Passenger> passList = new ArrayList<>();
		for (int i = 0; i < 4; i++)
			passList.add(new Passenger());
		booking.setPassengerList(passList);
		logger.info("Setting current flight as: "+scheduleFlightId);
		session.setAttribute("currentFlight", scheduleFlightId);
		logger.info("Returning Add Details View.");
		return new ModelAndView("AddBookingDetails", "booking", booking);
	}
	
	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves the current booking, Trims the passenger list, Sets other booking attributes and saves the booking.
	 *  Input: Booking object.
	 *  Output: Shows bookings done by the user.
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@PostMapping("/booking/save")
	public ModelAndView saveBooking(@Valid @ModelAttribute("booking") Booking booking, BindingResult result, Principal principal)
			throws InvalidBookingException, FlightNotFoundException, UserNotFoundException {
		if (result.hasErrors()) {
			logger.info("Found errors in entered details.");
			return new ModelAndView("AddBookingDetails", "booking", booking);
		} else {
			logger.info("Trimming User List.");
			List<Passenger> passList = new ArrayList<>();
			for (Passenger passenger : booking.getPassengerList()) {
				if (!passenger.getPassengerName().equals("")) {
					try {
						if (passenger.getPassengerAge() == (null) || passenger.getPassengerUIN() == (null))
							throw new FRSException("Invalid Details Entered.");
					} catch (FRSException exception) {
						return new ModelAndView("InvalidDetails");
					}
					passenger.setPassengerState(true);
					passList.add(passenger);
				}
			}
			logger.info("Trimmed User List.");
			booking.setPassengerList(passList);
			booking.setUserId(userService.getUserIdFromName(principal.getName()));
			booking.setBookingState(true);
			booking.setBookingDate(LocalDateTime.now());
			booking.setPassengerCount(passList.size());
			booking.setScheduleFlight(
					scheduleFlightService.viewScheduleFlights((BigInteger) session.getAttribute("currentFlight")));
			booking.setTicketCost((booking.getScheduleFlight().getTicketCost()) * booking.getPassengerCount());
			logger.info("Adding Booking.");
			bookingService.addBooking(booking);
			logger.info("Added Booking.");
			session.setAttribute("currentFlight", null);
			logger.info("Resetting current flight parameter.");
			logger.info("Returning show booking view.");
			return new ModelAndView("ShowBooking", "bookings",
					bookingService.viewBookingsByUser(userService.getUserIdFromName(principal.getName())));
		}
	}
	
	/*	
	 *  Author: DEVANG
	 *  Description: Shows the list of bookings made by current user
	 *  Input: -
	 *  Output:  Shows bookings done by the user.
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@GetMapping("/booking/view")
	public ModelAndView showBooking(Principal principal) throws InvalidBookingException, UserNotFoundException {
		logger.info("Returning show booking view.");
		return new ModelAndView("ShowBooking", "bookings",
				bookingService.viewBookingsByUser(userService.getUserIdFromName(principal.getName())));
	}
	
	/*	
	 *  Author: DEVANG
	 *  Description: Shows the cancel booking view to the user
	 *  Input: -
	 *  Output: Returns the Cancel Booking Page.
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@GetMapping("/booking/cancel")
	public String cancelBookingPage(@ModelAttribute("booking") Booking booking) {
		logger.info("Returning cancel booking view.");
		return "CancelBooking";
	}
	
	/*	
	 *  Author: DEVANG
	 *  Description: Shows the booking details before cancelling.
	 *  Input: Booking Id
	 *  Output: Returns the booking with the search id provided.
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@GetMapping("/booking/cancelview")
	public ModelAndView viewBookingToCancel(@RequestParam("booking_id") BigInteger bookingId,
			@ModelAttribute("booking") Booking booking) throws InvalidBookingException {
		logger.info("Retrieving Booking and returning cancel booking view.");
		return new ModelAndView("CancelBooking", "booking", bookingService.viewBooking(bookingId));
	}
	
	/*	
	 *  Author: DEVANG
	 *  Description: Confirms the booking cancellation.
	 *  Input: Booking Id
	 *  Output: Shows bookings done by the user.
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@PostMapping("/booking/confirmcancel")
	public ModelAndView confirmCancel(@RequestParam("booking_id") BigInteger bookingId, Principal principal) throws InvalidBookingException, UserNotFoundException {
		logger.info("Cancelling Booking: "+bookingId);
		bookingService.deleteBooking(bookingId);
		logger.info("Booking Cancelled.");
		logger.info("Returning show booking view.");
		return new ModelAndView("ShowBooking", "bookings",
				bookingService.viewBookingsByUser(userService.getUserIdFromName(principal.getName())));
	}
}
