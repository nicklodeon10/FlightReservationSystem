package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Booking;
import com.cg.frs.dto.ScheduleFlight;

public interface BookingService {
	
	public Booking addBooking(Booking booking);
	
	public List<Booking> viewBooking();
	
	public List<Booking> viewBooking(BigInteger id);
	
	public boolean deleteBooking(BigInteger bookingId);
	
	public boolean validatePassengerCount(ScheduleFlight scheduleFlight,Integer passengerChange);
	
}
