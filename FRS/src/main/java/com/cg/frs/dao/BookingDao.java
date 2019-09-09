package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Booking;

public interface BookingDao 
{
	public Booking addBooking(Booking booking);
	public List<Booking> showBooking();
	public Booking showBooking(BigInteger bookingId);
	public Booking updateBooking(Booking booking);
	public void removeBooking(BigInteger bookingId);
}
