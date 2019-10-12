package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Booking;

public interface BookingDao {
	
	public Booking addBooking(Booking booking);
	
	public List<Booking> showBooking();
	
	public boolean removeBooking(BigInteger bookingId);
	
	public Booking updateBooking(Booking booking);
	
}
