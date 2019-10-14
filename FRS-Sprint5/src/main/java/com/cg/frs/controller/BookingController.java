/**
 *	
 */
package com.cg.frs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.cg.frs.exception.FlightNotFoundException;
import com.cg.frs.exception.FrsException;
import com.cg.frs.exception.InvalidAirportException;
import com.cg.frs.exception.InvalidBookingException;
import com.cg.frs.exception.UserNotFoundException;
import com.cg.frs.service.AirportService;
import com.cg.frs.service.BookingService;
import com.cg.frs.service.ScheduleFlightService;
import com.cg.frs.service.TicketService;
import com.cg.frs.service.UserService;
import com.itextpdf.text.DocumentException;

/**
 * @author: DEVANG description: Controller for Bookings created date: 09/10/2019
 *          modified: 12/10/2019
 */

@Controller
public class BookingController {

	@Autowired
	BookingService bookingService;

	@Autowired
	AirportService airportService;

	@Autowired
	ScheduleFlightService scheduleFlightService;

	@Autowired
	UserService userService;
	
	@Autowired
	TicketService ticketService;

	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

	/*
	 * Author: DEVANG Description: Sends the flight search view to the user Input: -
	 * Output: Add Booking Page ModelAndView Created Date: 09/10/2019 Last Modified:
	 * -
	 */
	@GetMapping("/booking/add")
	public ModelAndView bookingSearch() {
		logger.info("Returning Booking Search View.");
		try {
			return new ModelAndView("AddBooking", "airportList", airportService.viewAirport());
		} catch (Exception e) {
			logger.error("Error adding Airport.");
			return new ModelAndView("ErrorPage");
		}
	}

	/*
	 * Author: DEVANG Description: Shows the flights available as per the search
	 * parameters. Input: Source Airport Code, Destination Airport Code, Date of
	 * Journey Output: Available Flights Page ModelAndView Created Date: 09/10/2019
	 * Last Modified: -
	 */
	@PostMapping("/booking/find")
	public ModelAndView flightSearch(@RequestParam("source_airport") String srcCode,
			@RequestParam("destination_airport") String destCode, @RequestParam("journeydate") String doj) {
		logger.info("Preparing Flight Search Parameters.");
		Airport sourceAirport;
		Airport destinationAirport;
		LocalDate journeyDate;
		try {
			sourceAirport = airportService.viewAirport(srcCode);
			destinationAirport = airportService.viewAirport(destCode);
			journeyDate = LocalDate.parse(doj);
			logger.info("Prepared Flight Search Parameters.");
			airportService.compareAirport(sourceAirport, destinationAirport);
		} catch (InvalidAirportException exception) {
			logger.error("Airport not found.");
			return new ModelAndView("ErrorPage");
		}
		logger.info("Returning View Flights View.");
		try {
			return new ModelAndView("ViewFlights", "scheduleFlightList",
					scheduleFlightService.viewScheduleFlights(sourceAirport, destinationAirport, journeyDate));
		} catch (FlightNotFoundException e) {
			logger.error("No Flights Available.");
			return new ModelAndView("NoFlights");
		}
	}

	/*
	 * Author: DEVANG Description: Shows the view of adding booking details for the
	 * selected flight Input: Flight Id of the selected flight Output: Add Details
	 * ModelAndView Created Date: 09/10/2019 Last Modified: -
	 */
	@GetMapping("/booking/addDetails")
	public ModelAndView addDetailsPage(@RequestParam("schedule_flight_id") BigInteger scheduleFlightId,
			HttpServletRequest request) {
		Booking booking = new Booking();
		List<Passenger> passList = new ArrayList<>();
		for (int i = 0; i < 4; i++)
			passList.add(new Passenger());
		booking.setPassengerList(passList);
		logger.info("Setting current flight as: " + scheduleFlightId);
		request.getSession().setAttribute("currentFlight", scheduleFlightId);
		logger.info("Returning Add Details View.");
		return new ModelAndView("AddBookingDetails", "booking", booking);
	}

	/*
	 * Author: DEVANG Description: Retrieves the current booking, Trims the
	 * passenger list, Sets other booking attributes and saves the booking. Input:
	 * Booking object. Output: Shows bookings done by the user. Created Date:
	 * 09/10/2019 Last Modified: -
	 */
	@PostMapping("/booking/save")
	public ModelAndView saveBooking(@Valid @ModelAttribute("booking") Booking booking, BindingResult result,
			Principal principal, HttpSession session) {
		if (result.hasErrors()) {
			logger.info("Found errors in entered details.");
			return new ModelAndView("AddBookingDetails", "booking", booking);
		} else {
			logger.info("Trimming Passenger List.");
			List<Passenger> passList = new ArrayList<>();
			for (Passenger passenger : booking.getPassengerList()) {
				if (!passenger.getPassengerName().equals("")) {
					try {
						if (passenger.getPassengerAge() == (null) || passenger.getPassengerUIN() == (null))
							throw new FrsException("Invalid Details Entered.");
					} catch (FrsException exception) {
						logger.info("Invalid Details Entered.");
						return new ModelAndView("InvalidDetails");
					}
					passenger.setPassengerState(true);
					passList.add(passenger);
				}
			}
			logger.info("Trimmed User List.");
			booking.setPassengerList(passList);
			try {
				booking.setUserId(userService.getUserIdFromName(principal.getName()));
			} catch (UserNotFoundException exception) {
				logger.error("User not Found.");
				return new ModelAndView("ErrorPage");
			}
			booking.setBookingState(true);
			booking.setBookingDate(LocalDateTime.now());
			booking.setPassengerCount(passList.size());
			try {
				booking.setScheduleFlight(
						scheduleFlightService.viewScheduleFlights((BigInteger) session.getAttribute("currentFlight")));
			} catch (FrsException e) {
				logger.error("Scheduled Flight not found.");
				return new ModelAndView("ErrorPage");
			}
			booking.setTicketCost((booking.getScheduleFlight().getTicketCost()) * booking.getPassengerCount());
			logger.info("Adding Booking.");
			try {
				bookingService.addBooking(booking);
			} catch (Exception e) {
				logger.error("Error Adding a Booking.");
				return new ModelAndView("Error Page");
			}
			logger.info("Added Booking.");
			session.setAttribute("currentFlight", null);
			logger.info("Resetting current flight parameter.");
			logger.info("Returning show booking view.");
			try {
				return new ModelAndView("ShowBooking", "bookings",
						bookingService.viewBookingsByUser(userService.getUserIdFromName(principal.getName())));
			} catch (InvalidBookingException exception) {
				logger.error("Booking not found.");
				return new ModelAndView("ErrorPage");
			} catch (UserNotFoundException exception) {
				logger.error("User not found.");
				return new ModelAndView("ErrorPage");
			}
		}
	}

	/*
	 * Author: DEVANG Description: Shows the list of bookings made by current user
	 * Input: - Output: Shows bookings done by the user. Created Date: 09/10/2019
	 * Last Modified: -
	 */
	@GetMapping("/booking/view")
	public ModelAndView showBooking(Principal principal) {
		logger.info("Returning show booking view.");
		try {
			return new ModelAndView("ShowBooking", "bookings",
					bookingService.viewBookingsByUser(userService.getUserIdFromName(principal.getName())));
		} catch (InvalidBookingException exception) {
			logger.error("Booking not found.");
			return new ModelAndView("ErrorPage");
		} catch (UserNotFoundException exception) {
			logger.error("User not found.");
			return new ModelAndView("ErrorPage");
		}
	}

	/*
	 * Author: DEVANG Description: Shows the cancel booking view to the user Input:
	 * - Output: Returns the Cancel Booking Page. Created Date: 09/10/2019 Last
	 * Modified: -
	 */
	@GetMapping("/booking/cancel")
	public String cancelBookingPage(@ModelAttribute("booking") Booking booking) {
		logger.info("Returning cancel booking view.");
		return "CancelBooking";
	}

	/*
	 * Author: DEVANG Description: Shows the booking details before cancelling.
	 * Input: Booking Id Output: Returns the booking with the search id provided.
	 * Created Date: 09/10/2019 Last Modified: -
	 */
	@GetMapping("/booking/cancelview")
	public ModelAndView viewBookingToCancel(@RequestParam("booking_id") BigInteger bookingId,
			@ModelAttribute("booking") Booking booking) {
		logger.info("Retrieving Booking and returning cancel booking view.");
		try {
			return new ModelAndView("CancelBooking", "booking", bookingService.viewBooking(bookingId));
		} catch (InvalidBookingException exception) {
			logger.error("Booking not found.");
			return new ModelAndView("NoBooking");
		}
	}

	/*
	 * Author: DEVANG Description: Confirms the booking cancellation. Input: Booking
	 * Id Output: Shows bookings done by the user. Created Date: 09/10/2019 Last
	 * Modified: -
	 */
	@PostMapping("/booking/confirmcancel")
	public ModelAndView confirmCancel(@RequestParam("booking_id") BigInteger bookingId, Principal principal) {
		logger.info("Cancelling Booking: " + bookingId);
		try {
			bookingService.deleteBooking(bookingId);
		} catch (InvalidBookingException e) {
			logger.error("Booking Not Found.");
			return new ModelAndView("ErrorPage");
		}
		logger.info("Booking Cancelled.");
		logger.info("Returning show booking view.");
		try {
			return new ModelAndView("ShowBooking", "bookings",
					bookingService.viewBookingsByUser(userService.getUserIdFromName(principal.getName())));
		} catch (InvalidBookingException exception) {
			logger.error("Booking not found.");
			return new ModelAndView("ErrorPage");
		} catch (UserNotFoundException exception) {
			logger.error("User not found.");
			return new ModelAndView("ErrorPage");
		}
	}
	
	@GetMapping("/booking/download")
	public ModelAndView download( HttpServletRequest request,
            HttpServletResponse response, @RequestParam("booking_id")BigInteger bookingId, Principal principal) {
		String filePath;
		try {
			logger.info("Generating eTicket for id: "+bookingId);
			filePath=ticketService.generate(bookingId);
			// get absolute path of the application
	        ServletContext context = request.getServletContext();      
	        File downloadFile = new File(filePath);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	        String mimeType = context.getMimeType(filePath);
	        if (mimeType == null) {
	            mimeType = "application/octet-stream";
	        }
	        logger.info("MIME type: " + mimeType);
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",
	                downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	        OutputStream outStream = response.getOutputStream();
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	        inputStream.close();
	        outStream.close();
		} catch (DocumentException | InvalidBookingException | IOException e) {
			logger.error("Error Generating Ticket");
			return new ModelAndView("ErrorPage");
		}
		logger.info("Returning show booking view.");
		try {
			return new ModelAndView("ShowBooking", "bookings",
					bookingService.viewBookingsByUser(userService.getUserIdFromName(principal.getName())));
		} catch (InvalidBookingException exception) {
			logger.error("Booking not found.");
			return new ModelAndView("ErrorPage");
		} catch (UserNotFoundException exception) {
			logger.error("User not found.");
			return new ModelAndView("ErrorPage");
		}
	}
	
}
