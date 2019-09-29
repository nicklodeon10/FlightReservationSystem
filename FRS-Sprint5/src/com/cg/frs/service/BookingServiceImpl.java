package com.cg.frs.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dao.BookingDao;
import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Passenger;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FRSException;

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
	public List<Booking> viewBooking(BigInteger id) throws FRSException {
		List<Booking> bookingList=bookingDao.showBooking();
		List<Booking> extractedList=new ArrayList<>();
		for(Booking booking: bookingList) {
			if(booking.getBookingId().equals(id) || booking.getUserId().equals(id))
				extractedList.add(booking);
		}
		if(extractedList.isEmpty())
			throw new FRSException("No Bookings Found.");
		return extractedList;
	}

	@Override
	public boolean deleteBooking(BigInteger bookingId) throws FRSException {
		return bookingDao.removeBooking(bookingId);
	}

	@Override
	public BigInteger validateBookingWithId(BigInteger bookingId) throws FRSException {
		if(viewBooking(bookingId)==null)
			throw new FRSException("Invalid Booking Id.");
		return bookingId;
	}

	@Override
	public Booking validatePnr(Booking booking, BigInteger pnr) throws FRSException {
		List<Passenger> passengerList=booking.getPassengerList();
		for(Passenger passenger: passengerList) {
			if(passenger.getPnrNumber().equals(pnr))
				return booking;
		}
		throw new FRSException("PNR Not Found.");
	}

	@Override
	public ScheduleFlight validatePassengerCount(ScheduleFlight scheduleFlight, Integer passengerChange) throws FRSException {
		if(passengerChange>scheduleFlight.getAvailableSeats())
			throw new FRSException("Seats Not Available");
		return scheduleFlight;
	}

	@Override
	public boolean validatePassengerName(String name) throws FRSException {
		for(int i=0; i<name.length(); i++) {
			char current=name.charAt(i);
			if(!((current>='a' && current<='z') || (current>='A' && current<='Z') || current==' '))
				throw new FRSException("Invalid Passenger Name.");
		}
		return true;
	}
	
	public boolean validatePassengerUIN(BigInteger uIN) throws FRSException{
		String passUIN=uIN.toString();
		if(passUIN.length()!=12) {
			throw new FRSException("Invalid Passenger UIN.");
		}
		return true;
	}
	
	public boolean validateLuggage(Double luggage) throws FRSException {
		if(luggage<0 || luggage>18) {
			throw new FRSException("Luggage Limit Exceeded.");
		}
		return true;
	}

}
