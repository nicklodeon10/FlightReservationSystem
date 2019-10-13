/**
 * 
 */
package com.cg.frs.controller;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.frs.FlightReservationSystemApplication;
import com.cg.frs.dto.Booking;
import com.cg.frs.exception.InvalidBookingException;
import com.cg.frs.service.AirportService;
import com.cg.frs.service.BookingService;

/**
 * @author DEVANG
 *
 */

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@Autowired
	AirportService airportService;
	
	private static final Logger logger = LoggerFactory.getLogger(FlightReservationSystemApplication.class);
	
	//To add a booking
	@PostMapping("/add")
	public ResponseEntity<Booking> addBooking(@ModelAttribute Booking booking) {
		logger.info("Adding Booking.");
		booking=bookingService.addBooking(booking);
		if(booking==null) {
			logger.error("Unable to add booking.");
			return new ResponseEntity("Data not added", HttpStatus.INTERNAL_SERVER_ERROR);
		}else {
			logger.info("Booking Added.");
			return new ResponseEntity<Booking>(booking, HttpStatus.OK);
		}
	}
	
	//To retrieve a booking by userId
	@GetMapping("/getbyuserid")
	public ResponseEntity<List<Booking>> getBookingsByUser(@RequestParam("userId")BigInteger userId){
		List<Booking> bookingList;
		try {
			logger.info("Retrieving Bookings.");
			bookingList=bookingService.viewBookingsByUser(userId);
		}catch(InvalidBookingException exception) {
			logger.error("No Bookings Found.");
			return new ResponseEntity("No Bookings Found.", HttpStatus.BAD_REQUEST);
		}
		logger.info("Returning Bookings by user: "+userId);
		return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
	}
	
	//To retrieve a booking by userId
	@GetMapping("/getbyid")
	public ResponseEntity<Booking> getBooking(@RequestParam("userId")BigInteger bookingId){
		Booking booking;
		try {
			logger.info("Retrieving Booking with id: "+bookingId);
			booking=bookingService.viewBooking(bookingId);
		}catch(InvalidBookingException exception) {
			logger.error("No Bookings Found.");
			return new ResponseEntity("No Bookings Found.", HttpStatus.BAD_REQUEST);
		}
		logger.info("Returning Bookings with id: "+bookingId);
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Booking>> getBookings(){
		List<Booking> bookingList;
		try {
			logger.info("Retrieving Bookings.");
			bookingList=bookingService.viewBooking();
		}catch(InvalidBookingException exception) {
			logger.error("No Bookings Found.");
			return new ResponseEntity("No Bookings Found.", HttpStatus.BAD_REQUEST);
		}
		logger.info("Returning Bookings.");
		return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
	}
	
	//To cancel a booking
	@DeleteMapping("/delete")
	public ResponseEntity<Boolean> cancelBooking(@RequestParam("bookingId")BigInteger bookingId) {
		try {
			logger.info("Cancelling Booking.");
			bookingService.deleteBooking(bookingId);
		}catch(InvalidBookingException exception) {
			logger.info("Unable to Cancel.");
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Booking Cancelled.");
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
}
