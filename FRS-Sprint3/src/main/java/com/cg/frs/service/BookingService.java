package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Booking;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FRSException;

public interface BookingService {
	
	public Booking addBooking(Booking booking);
	
	public List<Booking> viewBooking();
	
	public List<Booking> viewBooking(BigInteger id) throws FRSException;
	
	public Booking modifyBooking(Booking booking, Integer removePassengerCount);
	
	public boolean deleteBooking(BigInteger bookingId) throws FRSException;
	
	public ScheduleFlight validatePassengerCount(ScheduleFlight scheduleFlight,Integer passengerChange) throws FRSException;
	
	public boolean validatePassengerName(String name) throws FRSException;
	
	public BigInteger validateBookingWithId(BigInteger bookingId) throws FRSException;
	
	public Booking validatePnr(Booking booking, BigInteger pnr) throws FRSException;
	
	public boolean validatePassengerUIN(BigInteger UIN) throws FRSException;
	
	public boolean validateLuggage(Double luggage) throws FRSException;
	
}
