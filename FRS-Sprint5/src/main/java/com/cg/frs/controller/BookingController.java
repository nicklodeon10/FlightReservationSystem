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
 * @author DEVANG
 *
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

	@GetMapping("/booking/add")
	public ModelAndView bookingSearch() {
		return new ModelAndView("AddBooking", "airportList", airportService.viewAirport());
	}

	@PostMapping("/booking/find")
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

	@GetMapping("/booking/addDetails")
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

	@PostMapping("/booking/save")
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

	@GetMapping("/booking/view")
	public ModelAndView showBooking() throws InvalidBookingException {
		session.setAttribute("userId", BigInteger.valueOf(12345L)); // Remove after adding login
		return new ModelAndView("ShowBooking", "bookings",
				bookingService.viewBookingsByUser((BigInteger) session.getAttribute("userId")));
	}

	@GetMapping("/booking/cancel")
	public String cancelBookingPage(@ModelAttribute("booking") Booking booking) {
		return "CancelBooking";
	}

	@GetMapping("/booking/cancelview")
	public ModelAndView viewBookingToCancel(@RequestParam("booking_id") BigInteger bookingId,
			@ModelAttribute("booking") Booking booking) throws InvalidBookingException {
		return new ModelAndView("CancelBooking", "booking", bookingService.viewBooking(bookingId));
	}

	@PostMapping("/booking/confirmcancel")
	public ModelAndView confirmCancel(@RequestParam("booking_id") BigInteger bookingId) throws InvalidBookingException {
		bookingService.deleteBooking(bookingId);
		session.setAttribute("userId", BigInteger.valueOf(12345L)); // Remove after adding login
		return new ModelAndView("ShowBooking", "bookings",
				bookingService.viewBookingsByUser((BigInteger) session.getAttribute("userId")));
	}
}