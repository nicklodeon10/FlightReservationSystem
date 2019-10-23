/**
 * 
 */
package com.cg.frs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Passenger;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FlightNotFoundException;
import com.cg.frs.exception.FrsException;
import com.cg.frs.exception.InvalidAirportException;
import com.cg.frs.exception.InvalidBookingException;
import com.cg.frs.service.AirportService;
import com.cg.frs.service.BookingService;
import com.cg.frs.service.ScheduleFlightService;
import com.cg.frs.service.TicketService;
import com.itextpdf.text.DocumentException;

/**
 * @author DEVANG
 *
 */

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@Autowired
	AirportService airportService;

	@Autowired
	ScheduleFlightService scheduleFlightService;
	
	@Autowired
	TicketService ticketService;

	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

	// To add a booking
	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Booking> addBooking(@ModelAttribute Booking booking,
			@RequestParam("flightId") BigInteger flightId, @RequestParam("userId")BigInteger userId) {
		booking.setUserId(userId); 	
		booking.setPassengerCount(booking.getPassengerList().size());
		for (Passenger passenger : booking.getPassengerList())passenger.setPassengerState(true);
		booking.setBookingDate(LocalDateTime.now());
		booking.setBookingState(true);
		try {
			booking.setScheduleFlight(scheduleFlightService.viewScheduleFlights(flightId));
			booking.setTicketCost(
					scheduleFlightService.viewScheduleFlights(flightId).getTicketCost() * booking.getPassengerCount());
		} catch (FrsException e) {
			logger.error("Flight not found.");
			return new ResponseEntity("Data not added", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Adding Booking.");
		try {
			booking = bookingService.addBooking(booking);			
		} catch (Exception e) {
			logger.info("Data Not Added.");
			return new ResponseEntity("Data not added", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (booking == null) {
			logger.error("Unable to add booking.");
			return new ResponseEntity("Data not added", HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			logger.info("Booking Added.");
			return new ResponseEntity<Booking>(booking, HttpStatus.OK);
		}
	}

	// To retrieve a booking by userId
	@GetMapping("/getbyuserid")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<List<Booking>> getBookingsByUser(@RequestParam("userId") BigInteger userId) {
		List<Booking> bookingList;
		try {
			logger.info("Retrieving Bookings.");
			bookingList = bookingService.viewBookingsByUser(userId);
		} catch (InvalidBookingException exception) {
			logger.error("No Bookings Found.");
			return new ResponseEntity("No Bookings Found.", HttpStatus.BAD_REQUEST);
		}
		logger.info("Returning Bookings by user: " + userId);
		return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
	}
	
	@GetMapping("/getprev")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Booking> getLastBooking(@RequestParam("userId") BigInteger userId){
		List<Booking> bookingList;
		try {
			logger.info("Retrieving Bookings.");
			bookingList = bookingService.viewBookingsByUser(userId);
		} catch (InvalidBookingException exception) {
			logger.error("No Bookings Found.");
			return new ResponseEntity("No Bookings Found.", HttpStatus.BAD_REQUEST);
		}
		logger.info("Retrieving Last Booking");
		return new ResponseEntity<Booking>(bookingList.get(bookingList.size()-1), HttpStatus.OK);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<Booking>> getBookings() {
		List<Booking> bookingList;
		try {
			logger.info("Retrieving Bookings.");
			bookingList = bookingService.viewBooking();
		} catch (InvalidBookingException exception) {
			logger.error("No Bookings Found.");
			return new ResponseEntity("No Bookings Found.", HttpStatus.BAD_REQUEST);
		}
		logger.info("Returning Bookings.");
		return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
	}

	// To cancel a booking
	@DeleteMapping("/cancel")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Boolean> cancelBooking(@RequestParam("bookingId") BigInteger bookingId) {
		try {
			logger.info("Cancelling Booking.");
			bookingService.deleteBooking(bookingId);
		} catch (InvalidBookingException exception) {
			logger.info("Unable to Cancel.");
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Booking Cancelled.");
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@GetMapping("/find")
	public ResponseEntity<List<ScheduleFlight>> flightSearch(@RequestParam("source_airport") String srcCode,
			@RequestParam("destination_airport") String destCode, @RequestParam("journey_date") String doj) {
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
			return new ResponseEntity("No Airports Found.", HttpStatus.BAD_REQUEST);
		}
		logger.info("Returning View Flights View.");
		try {
			return new ResponseEntity<List<ScheduleFlight>>(
					scheduleFlightService.viewScheduleFlights(sourceAirport, destinationAirport, journeyDate),
					HttpStatus.OK);
		} catch (FlightNotFoundException e) {
			logger.error("No Flights Available.");
			return new ResponseEntity("No Flights Found.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("download")
	public ResponseEntity<String> download( HttpServletRequest request,
            HttpServletResponse response, @RequestParam("booking_id")BigInteger bookingId) {
		logger.info("Downloading Ticket");
		String filePath;
		try {
			logger.info("Generating eTicket for id: "+bookingId);
			filePath=ticketService.generate(bookingId);
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
			return new ResponseEntity<String>("Error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Returning show booking view.");
		return new ResponseEntity<String>("Error", HttpStatus.OK);
	}
}
