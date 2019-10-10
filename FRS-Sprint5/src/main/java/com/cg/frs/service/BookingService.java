/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Booking;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.InvalidBookingException;

/**
 * @author DEVANG
 *
 */
public interface BookingService {

	//Service Method to add a booking
	public Booking addBooking(Booking booking);

	//Service Method to retrieve a list of all bookings
	public List<Booking> viewBooking()throws InvalidBookingException;

	//Service Method to retrieve a booking by Id
	public Booking viewBooking(BigInteger bookingId)throws InvalidBookingException;
	
	//Service Method to retrieve a list of bookings made by userId
	public List<Booking> viewBookingsByUser(BigInteger userId)throws InvalidBookingException;

	//Service Method to delete a booking
	public boolean deleteBooking(BigInteger bookingId)throws InvalidBookingException;

	//Service Method to check if the flight has seats available
	public boolean validatePassengerCount(ScheduleFlight scheduleFlight, Integer passengerChange)throws InvalidBookingException;

}
