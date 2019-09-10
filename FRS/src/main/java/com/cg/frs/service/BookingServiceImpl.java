package com.cg.frs.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dao.BookingDao;
import com.cg.frs.dao.BookingDaoImpl;
import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Passenger;
import com.cg.frs.exception.FRSException;

public class BookingServiceImpl implements BookingService
{
	BookingDao bookingDao=new BookingDaoImpl();

	@Override
	public Booking addBooking(Booking booking) {
		return bookingDao.addBooking(booking);
	}

	@Override
	public List<Booking> viewBooking() {
		return bookingDao.showBooking();
	}

	@Override
	public List<Booking> viewBooking(BigInteger id) {
		List<Booking> bookingList=bookingDao.showBooking();
		List<Booking> extractedList=new ArrayList<Booking>();
		for(Booking booking: bookingList) {
			if(booking.getBookingId()==id || booking.getUserId()==id)
				extractedList.add(booking);
		}
		return bookingList;
	}

	@Override
	public Booking modifyBooking(Booking booking) {
		return bookingDao.addBooking(booking);
	}

	@Override
	public void deleteBooking(BigInteger bookingId) {
		bookingDao.removeBooking(bookingId);
	}

	@Override
	public BigInteger validateBookingWithId(BigInteger bookingId) throws FRSException {
		if(viewBooking(bookingId)==null)
			throw new FRSException("Invalid Booking Id.");
		return bookingId;
	}

	@Override
	public Passenger validateBooking(Passenger passenger) throws FRSException {
		String passUIN=passenger.getPassengerUIN().toString();
		String name=passenger.getPassengerName();
		Double luggage=passenger.getLuggage();
		if(passUIN.length()!=12) {
			throw new FRSException("Invalid Passenger UIN.");
		}
		if(luggage<0 || luggage>18) {
			throw new FRSException("Luggage Limit Exceeded.");
		}
		for(int i=0; i<name.length(); i++) {
			char current=name.charAt(i);
			if(!((current>'a' && current<'z') || (current>'A' && current<'Z') || current==' '))
				throw new FRSException("Invalid Passenger Name.");
		}
		return passenger;
	}

}
