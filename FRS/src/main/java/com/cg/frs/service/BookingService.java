package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Booking;

public interface BookingService {
	
	public Booking addBooking(Booking booking);
	
	public List<Booking> viewBooking();
	
	public List<Booking> viewBooking(BigInteger id);
	
	public Booking modifyBooking(Booking booking);
	
	public void deleteBooking(BigInteger bookingId);
	
	public boolean validateBookingWithId(BigInteger bookingId);
	
}
