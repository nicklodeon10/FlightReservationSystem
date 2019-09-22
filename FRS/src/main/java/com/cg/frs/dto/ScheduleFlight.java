package com.cg.frs.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class ScheduleFlight {

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Flight flight;
	@Column
	private Integer availableSeats;
	@Embedded
	private Schedule schedule;
	@Column
	private Double ticketCost;
	@Column
	private Boolean scheduleFlightState;

	public ScheduleFlight() {
		super();
	}

	public ScheduleFlight(Flight flight, Integer availableSeats, Schedule schedule, Double ticketCost,
			Boolean scheduleFlightState) {
		super();
		this.flight = flight;
		this.availableSeats = availableSeats;
		this.schedule = schedule;
		this.ticketCost = ticketCost;
		this.scheduleFlightState = scheduleFlightState;
	}

	@Override
	public String toString() {
		return "ScheduleFlight [flight=" + flight + ", availableSeats=" + availableSeats + ", schedule=" + schedule
				+ ", ticketCost=" + ticketCost + ", scheduleFlightState=" + scheduleFlightState + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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

}
