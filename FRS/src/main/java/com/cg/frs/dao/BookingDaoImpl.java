package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.Booking;

public class BookingDaoImpl implements BookingDao {
	
	private List<Booking> bookingList=new ArrayList<Booking>();
	
	public Booking addBooking(Booking booking) {
		if(bookingList.indexOf(booking)== -1) {
			bookingList.add(booking);
		}else {
			int modifyIndex=bookingList.indexOf(booking);
			bookingList.set(modifyIndex, booking);
		}
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
