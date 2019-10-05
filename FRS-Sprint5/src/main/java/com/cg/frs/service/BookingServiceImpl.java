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
	
	@Override
	public Booking addBooking(Booking booking) {
		return bookingDao.save(booking);
	}

	@Override
	public List<Booking> viewBooking() {
		return bookingDao.findAll();
	}

	@Override
	public Booking viewBooking(BigInteger bookingId) {
		return bookingDao.findById(bookingId).get();
	}

	@Override
	public boolean deleteBooking(BigInteger bookingId) {
		Booking booking=viewBooking(bookingId);
		booking.setBookingState(false);
		bookingDao.save(booking);
		return true;
	}

	@Override
	public boolean validatePassengerCount(ScheduleFlight scheduleFlight, Integer passengerChange) {
		if (passengerChange > scheduleFlight.getAvailableSeats())
			return false;
		return true;
	}

}