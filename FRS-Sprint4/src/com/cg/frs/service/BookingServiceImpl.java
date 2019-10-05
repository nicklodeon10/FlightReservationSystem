package com.cg.frs.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dao.BookingDao;
import com.cg.frs.dto.Booking;
import com.cg.frs.dto.ScheduleFlight;

@Service("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService
{
	@Autowired
	BookingDao bookingDao;
	@Autowired
	ScheduleFlightService scheduleFlightService;
	
	@Override
	public Booking addBooking(Booking booking) {
		return bookingDao.addBooking(booking);
	}

	@Override
	public List<Booking> viewBooking() {
		return bookingDao.showBooking();
	}

	@Override
	public List<Booking> viewBooking(BigInteger id){
		List<Booking> bookingList=bookingDao.showBooking();
		List<Booking> extractedList=new ArrayList<>();
		for(Booking booking: bookingList) {
			if(booking.getBookingId().equals(id) || booking.getUserId().equals(id))
				extractedList.add(booking);
		}
		return extractedList;
	}

	@Override
	public boolean deleteBooking(BigInteger bookingId) {
		return bookingDao.removeBooking(bookingId);
	}


	@Override
	public boolean validatePassengerCount(ScheduleFlight scheduleFlight, Integer passengerChange){
		if(passengerChange>scheduleFlight.getAvailableSeats())
			return false;
		return true;
	}

}
