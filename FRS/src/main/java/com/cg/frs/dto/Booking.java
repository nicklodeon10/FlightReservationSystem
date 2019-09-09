package com.cg.frs.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public class Booking {
	
	private BigInteger bookingId;
	private User userId;
	private LocalDateTime bookingDate;
	private List<Passenger> passengerList;
	private Double ticketCost;
	private Flight flight;
	private Integer noOfPassengers;
	
	public Booking() {
		super();
	}
	
	public Booking(BigInteger bookingId, User userId, LocalDateTime bookingDate, List<Passenger> passengerList,
			Double ticketCost, Flight flight, Integer noOfPassengers) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.bookingDate = bookingDate;
		this.passengerList = passengerList;
		this.ticketCost = ticketCost;
		this.flight = flight;
		this.noOfPassengers = noOfPassengers;
	}

	public BigInteger getBookingId() {
		return bookingId;
	}
	
	public void setBookingId(BigInteger bookingId) {
		this.bookingId = bookingId;
	}
	
	public User getUserId() {
		return userId;
	}
	
	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	public List<Passenger> getPassengerList() {
		return passengerList;
	}
	
	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	
	public Double getTicketCost() {
		return ticketCost;
	}
	
	public void setTicketCost(Double ticketCost) {
		this.ticketCost = ticketCost;
	}
	
	public Flight getFlight() {
		return flight;
	}
	
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	public int getNoOfPassengers() {
		return noOfPassengers;
	}
	
	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", userId=" + userId + ", bookingDate=" + bookingDate
				+ ", passengerList=" + passengerList + ", ticketCost=" + ticketCost + ", flight=" + flight
				+ ", noOfPassengers=" + noOfPassengers + "]";
	}
	
}
