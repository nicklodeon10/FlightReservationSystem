/**
 *	
 */
package com.cg.frs.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.cg.frs.service.AirportService;
import com.cg.frs.service.BookingService;
import com.cg.frs.service.ScheduleFlightService;

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
	public String adminPanel() {
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
	public String userPanel() {
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
	@GetMapping("/add")
	public ModelAndView bookingSearch() {
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
	@PostMapping("/find")
	public ModelAndView flightSearch(@RequestParam("source_airport") String srcCode,
			@RequestParam("destination_airport") String destCode, @RequestParam("journeydate") String doj)
			throws InvalidAirportException {
		Airport sourceAirport = airportService.viewAirport(srcCode);
		Airport destinationAirport = airportService.viewAirport(destCode);
		LocalDate journeyDate = LocalDate.parse(doj);
		airportService.compareAirport(sourceAirport, destinationAirport);
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
	@GetMapping("/addDetails")
	public ModelAndView addDetailsPage(@RequestParam("schedule_flight_id") BigInteger scheduleFlightId) {
		Booking booking = new Booking();
		List<Passenger> passList = new ArrayList<>();
		for (int i = 0; i < 4; i++)
			passList.add(new Passenger());
		booking.setPassengerList(passList);
		session.setAttribute("currentFlight", scheduleFlightId);
		session.setAttribute("userId", BigInteger.valueOf(12345L)); // Remove after adding login
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
	@PostMapping("/save")
	public ModelAndView saveBooking(@Valid @ModelAttribute("booking") Booking booking, BindingResult result)
			throws InvalidBookingException, FlightNotFoundException {
		if (result.hasErrors()) {
			return new ModelAndView("AddBookingDetails", "booking", booking);
		} else {
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
					bookingService.viewBookingsByUser((BigInteger) session.getAttribute("userId")));
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
	@GetMapping("/view")
	public ModelAndView showBooking() throws InvalidBookingException {
		session.setAttribute("userId", BigInteger.valueOf(12345L)); // Remove after adding login
		return new ModelAndView("ShowBooking", "bookings",
				bookingService.viewBookingsByUser((BigInteger) session.getAttribute("userId")));
	}
	
	/*	
	 *  Author: DEVANG
	 *  Description: Shows the cancel booking view to the user
	 *  Input: -
	 *  Output: Returns the Cancel Booking Page.
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@GetMapping("/cancel")
	public String cancelBookingPage(@ModelAttribute("booking") Booking booking) {
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
	@GetMapping("/cancelview")
	public ModelAndView viewBookingToCancel(@RequestParam("booking_id") BigInteger bookingId,
			@ModelAttribute("booking") Booking booking) throws InvalidBookingException {
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
	@PostMapping("/confirmcancel")
	public ModelAndView confirmCancel(@RequestParam("booking_id") BigInteger bookingId) throws InvalidBookingException {
		bookingService.deleteBooking(bookingId);
		session.setAttribute("userId", BigInteger.valueOf(12345L)); // Remove after adding login
		return new ModelAndView("ShowBooking", "bookings",
				bookingService.viewBookingsByUser((BigInteger) session.getAttribute("userId")));
	}
}
