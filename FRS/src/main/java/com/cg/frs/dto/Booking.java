package com.cg.frs.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public class Booking {
	
	private BigInteger bookingId;
	private BigInteger userId;
	private LocalDateTime bookingDate;
	private List<Passenger> passengerList;
	private Double ticketCost;
	private ScheduleFlight flight;
	private Integer noOfPassengers;
	
	public Booking() {
		super();
	}
	
	public Booking(BigInteger bookingId, BigInteger userId, LocalDateTime bookingDate, List<Passenger> passengerList,
			Double ticketCost, ScheduleFlight flight, Integer noOfPassengers) {
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
	
	public BigInteger getUserId() {
		return userId;
	}
	
	public void setUserId(BigInteger userId) {
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
	
	public ScheduleFlight getFlight() {
		return flight;
	}
	
	public void setFlight(ScheduleFlight flight) {
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
