/**
 * 
 */
package com.cg.frs.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.frs.dto.Booking;
import com.cg.frs.exception.FRSException;
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
	
	//To add a booking
	@PostMapping("/add")
	public String addBooking(@RequestBody Booking booking)throws FRSException {
		if(bookingService.validatePassengerCount(booking.getScheduleFlight(), booking.getPassengerCount())) {
			bookingService.addBooking(booking);
		}
		return "Added Booking";
	}
	
	//To retrieve a booking by userId
	@GetMapping("/get")
	public List<Booking> getBookingsByUser(@RequestParam("userId")BigInteger userId) throws FRSException{
		return bookingService.viewBookingsByUser(userId);
	}
	
	//To cancel a booking
	@DeleteMapping("/delete")
	public boolean cancelBooking(@RequestParam("bookingId")BigInteger bookingId) throws FRSException {
		return bookingService.deleteBooking(bookingId);
	}
	
}
