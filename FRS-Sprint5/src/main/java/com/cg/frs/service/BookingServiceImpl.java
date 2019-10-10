/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dao.BookingDao;
import com.cg.frs.dto.Booking;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FRSException;
import com.cg.frs.exception.InvalidBookingException;

/**
 * @author DEVANG
 *
 */

@Service("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingDao bookingDao;

	@Autowired
	ScheduleFlightService scheduleFlightService;

	// Service Method to add a booking
	@Override
	public Booking addBooking(Booking booking) {
		return bookingDao.save(booking);
	}

	// Service Method to retrieve a list of all bookings
	@Override
	public List<Booking> viewBooking() throws InvalidBookingException {
		List<Booking> bookings = bookingDao.findAll();
		if (bookings.isEmpty()) {
			throw new InvalidBookingException("No Bookings Found.");
		}
		return bookings;
	}

	// Service Method to retrieve a booking by Id
	@Override
	public Booking viewBooking(BigInteger bookingId) throws InvalidBookingException {
		Booking booking = bookingDao.findById(bookingId).get();
		if (booking == null) {
			throw new InvalidBookingException("No Bookings Found.");
		}
		return booking;
	}

	// Service Method to delete a booking
	@Override
	public boolean deleteBooking(BigInteger bookingId) throws InvalidBookingException {
		Booking booking = viewBooking(bookingId);
		if (booking == null) {
			throw new InvalidBookingException("No Bookings Found.");
		}
		booking.setBookingState(false);
		bookingDao.save(booking);
		return true;
	}

	// Service Method to check if the flight has seats available
	@Override
	public boolean validatePassengerCount(ScheduleFlight scheduleFlight, Integer passengerChange) throws InvalidBookingException {
		if (passengerChange > scheduleFlight.getAvailableSeats())
			throw new InvalidBookingException("Seats not available.");
		return true;
	}

	// Service Method to retrieve a list of bookings made by userId
	@Override
	public List<Booking> viewBookingsByUser(BigInteger userId) throws InvalidBookingException {
		List<Booking> bookings = bookingDao.findByUserId(userId);
		if (bookings.isEmpty()) {
			throw new InvalidBookingException("No Bookings Found.");
		}
		return bookings;
	}

}