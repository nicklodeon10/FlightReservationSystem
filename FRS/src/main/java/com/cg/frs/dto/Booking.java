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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
		result = prime * result + ((bookingId == null) ? 0 : bookingId.hashCode());
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + ((noOfPassengers == null) ? 0 : noOfPassengers.hashCode());
		result = prime * result + ((passengerList == null) ? 0 : passengerList.hashCode());
		result = prime * result + ((ticketCost == null) ? 0 : ticketCost.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Booking other = (Booking) obj;
		if (bookingDate == null) {
			if (other.bookingDate != null) {
				return false;
			}
		} else if (!bookingDate.equals(other.bookingDate)) {
			return false;
		}
		if (bookingId == null) {
			if (other.bookingId != null) {
				return false;
			}
		} else if (!bookingId.equals(other.bookingId)) {
			return false;
		}
		if (flight == null) {
			if (other.flight != null) {
				return false;
			}
		} else if (!flight.equals(other.flight)) {
			return false;
		}
		if (noOfPassengers == null) {
			if (other.noOfPassengers != null) {
				return false;
			}
		} else if (!noOfPassengers.equals(other.noOfPassengers)) {
			return false;
		}
		if (passengerList == null) {
			if (other.passengerList != null) {
				return false;
			}
		} else if (!passengerList.equals(other.passengerList)) {
			return false;
		}
		if (ticketCost == null) {
			if (other.ticketCost != null) {
				return false;
			}
		} else if (!ticketCost.equals(other.ticketCost)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}
	
}
