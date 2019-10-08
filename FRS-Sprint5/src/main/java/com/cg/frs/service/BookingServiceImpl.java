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

/**
 * @author DEVANG
 *
 */

@Service("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingDao bookingDao;

	/*
	 * @Autowired ScheduleFlightService scheduleFlightService;
	 */

	// Service Method to add a booking
	@Override
	public Booking addBooking(Booking booking) {
		return bookingDao.save(booking);
	}

	// Service Method to retrieve a list of all bookings
	@Override
	public List<Booking> viewBooking()throws FRSException {
		List<Booking> bookings=bookingDao.findAll();
		if(bookings.isEmpty()) {
			throw new FRSException("No Bookings Found.");
		}
		return bookings;
	}

	// Service Method to retrieve a booking by Id
	@Override
	public Booking viewBooking(BigInteger bookingId) throws FRSException {
		Booking booking=bookingDao.findById(bookingId).get();
		if(booking==null) {
			throw new FRSException("No Bookings Found.");
		}
		return booking;
	}

	// Service Method to delete a booking
	@Override
	public boolean deleteBooking(BigInteger bookingId) throws FRSException {
		Booking booking = viewBooking(bookingId);
		if(booking==null) {
			throw new FRSException("No Bookings Found.");
		}
		booking.setBookingState(false);
		bookingDao.save(booking);
		return true;
	}

	// Service Method to check if the flight has seats available
	@Override
	public boolean validatePassengerCount(ScheduleFlight scheduleFlight, Integer passengerChange) throws FRSException {
		if (passengerChange > scheduleFlight.getAvailableSeats())
			throw new FRSException("Seats not available.");
		return true;
	}

	// Service Method to retrieve a list of bookings made by userId
	@Override
	public List<Booking> viewBookingsByUser(BigInteger userId) throws FRSException {
		List<Booking> bookings=bookingDao.findByUserId(userId);
		if(bookings.isEmpty()) {
			throw new FRSException("No Bookings Found.");
		}
		return bookings;
	}

}