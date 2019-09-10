package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Passenger;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FRSException;

public interface BookingService {
	
	public Booking addBooking(Booking booking);
	
	public List<Booking> viewBooking();
	
	public List<Booking> viewBooking(BigInteger id);
	
	public Booking modifyBooking(Booking booking);
	
	public void deleteBooking(BigInteger bookingId);
	
	public Integer modifySeatCount(ScheduleFlight scheduleFlight, int change) ;
	
	public Integer validatePassengerCount(ScheduleFlight scheduleFlight,Integer passengerChange) throws FRSException;
	
	public BigInteger validateBookingWithId(BigInteger bookingId) throws FRSException;
	
	public Passenger validateBooking(Passenger passenger) throws FRSException;
	
	public Booking validatePnr(Booking booking, BigInteger pnr) throws FRSException;
	
}
