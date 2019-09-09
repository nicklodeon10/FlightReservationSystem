package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dao.BookingDao;
import com.cg.frs.dao.BookingDaoImpl;
import com.cg.frs.dto.Booking;

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
	public Booking viewBooking(BigInteger bookingId) {
		List<Booking> bookingList=bookingDao.showBooking();
		for(Booking booking: bookingList) {
			if(booking.getBookingId()==bookingId)
				return booking;
		}
		return null;
	}

	@Override
	public Booking modifyBooking(Booking booking) {
		return bookingDao.addBooking(booking);
	}

	@Override
	public void deleteBooking(BigInteger bookingId) {
		bookingDao.removeBooking(bookingId);
	}

	//validate for booking and passenger
}
