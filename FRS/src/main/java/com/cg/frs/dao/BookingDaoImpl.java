package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.Booking;

public class BookingDaoImpl implements BookingDao {
	
	private List<Booking> bookingList=new ArrayList<Booking>();
	
	public Booking addBooking(Booking booking) {
		bookingList.add(booking);
		return booking;
	}
	
	public List<Booking> showBooking() {
		return bookingList;
	}

	public void removeBooking(BigInteger bookingId)	{
		for(Booking booking:bookingList) {
			if(booking.getBookingId()==bookingId) {
				bookingList.remove(booking);
				break;
			}
		}
	}
}
