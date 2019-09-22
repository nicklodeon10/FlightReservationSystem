package com.cg.frs.dto;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ScheduleFlight {

	@Id
	private BigInteger scheduleFlightId;
	@OneToOne(fetch = FetchType.EAGER)
	private Flight flight;
	@Column
	private Integer availableSeats;
	@OneToOne
	private Schedule schedule;
	@Column
	private Double ticketCost;
	@Column
	private Boolean scheduleFlightState;

	public ScheduleFlight() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((scheduleFlightId == null) ? 0 : scheduleFlightId.hashCode());
		result = prime * result + ((availableSeats == null) ? 0 : availableSeats.hashCode());
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
		result = prime * result + ((scheduleFlightState == null) ? 0 : scheduleFlightState.hashCode());
		result = prime * result + ((ticketCost == null) ? 0 : ticketCost.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleFlight other = (ScheduleFlight) obj;
		if (scheduleFlightId == null) {
			if (other.scheduleFlightId != null)
				return false;
		} else if (!scheduleFlightId.equals(other.scheduleFlightId))
			return false;
		if (availableSeats == null) {
			if (other.availableSeats != null)
				return false;
		} else if (!availableSeats.equals(other.availableSeats))
			return false;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (schedule == null) {
			if (other.schedule != null)
				return false;
		} else if (!schedule.equals(other.schedule))
			return false;
		if (scheduleFlightState == null) {
			if (other.scheduleFlightState != null)
				return false;
		} else if (!scheduleFlightState.equals(other.scheduleFlightState))
			return false;
		if (ticketCost == null) {
			if (other.ticketCost != null)
				return false;
		} else if (!ticketCost.equals(other.ticketCost))
			return false;
		return true;
	}

	public ScheduleFlight(BigInteger scheduleFlightId, Flight flight, Integer availableSeats, Schedule schedule,
			Double ticketCost, Boolean scheduleFlightState) {
		super();
		scheduleFlightId = scheduleFlightId;
		this.flight = flight;
		this.availableSeats = availableSeats;
		this.schedule = schedule;
		this.ticketCost = ticketCost;
		this.scheduleFlightState = scheduleFlightState;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Double getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(Double ticketCost) {
		this.ticketCost = ticketCost;
	}

	public Boolean getScheduleFlightState() {
		return scheduleFlightState;
	}

	public void setScheduleFlightState(Boolean scheduleFlightState) {
		this.scheduleFlightState = scheduleFlightState;
	}

	public BigInteger getScheduleFlightId() {
		return scheduleFlightId;
	}

	public void setScheduleFlightId(BigInteger scheduleFlightId) {
		scheduleFlightId = scheduleFlightId;
	}

}
