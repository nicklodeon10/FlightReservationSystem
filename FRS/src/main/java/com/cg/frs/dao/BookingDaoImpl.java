package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.Booking;
import com.cg.frs.dto.User;

public class BookingDaoImpl implements BookingDao 
{
	public List<Booking> bookingList=new ArrayList<Booking>();
	public Booking addBooking(Booking booking)
	{
		bookingList.add(booking);
		return booking;
	}
	public List<Booking> showBooking()
	{
		return bookingList;
	}
	public Booking showBooking(BigInteger bookingId)
	{
		for(Booking b:bookingList)
		{
			if(b.getBookingId()==bookingId)
			return b;
	}
		return null;
	}
	public Booking updateBooking(Booking booking)
	{
		for(Booking b:bookingList)
		{
			if(b.getBookingId()==booking.getBookingId())
			{
			//fill in for booking date
			b.setPassengerList(booking.getPassengerList());
			b.setTicketCost(booking.getTicketCost());
			b.setFlight(booking.getFlight());
			b.setNoOfPassengers(booking.getNoOfPassengers());
			}
		}
		return null;
	}
	public void removeBooking(BigInteger bookingId)
	{
		for(Booking b:bookingList)
		{
			if(b.getBookingId()==bookingId)
			{
				bookingList.remove(b);
		}
	}
	}
}
