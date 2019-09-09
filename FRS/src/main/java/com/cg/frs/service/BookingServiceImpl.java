package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dao.BookingDao;
import com.cg.frs.dao.BookingDaoImpl;
import com.cg.frs.dto.Booking;

public class BookingServiceImpl implements BookingService
{
	BookingDao bookingdao=new BookingDaoImpl();
	public Booking addBooking(Booking booking)
	{
		return bookingdao.addBooking(booking);
	}
	public List<Booking> viewBooking()
	{
		return bookingdao.showBooking();
	}
	public Booking viewBooking(BigInteger bookingId)
	{
		return bookingdao.showBooking(bookingId);
	}
	public Booking modifyBooking(Booking booking)
	{
		return bookingdao.updateBooking(booking);
	}
	public void deleteBooking(BigInteger bookingId)
	{
		bookingdao.removeBooking(bookingId);
	}
	//validate for booking and passenger
}
