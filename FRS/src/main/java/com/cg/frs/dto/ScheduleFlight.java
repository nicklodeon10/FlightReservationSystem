package com.cg.frs.dto;

public class ScheduleFlight  {

	private Flight flight=new Flight();
	private Integer availableSeats;
	private Schedule schedule;
	private Double ticketCost; 
	
	public ScheduleFlight() {
		super();
	}

	public ScheduleFlight(Flight flight, Integer availableSeats, Schedule schedule, Double ticketCost) {
		super();
		this.flight = flight;
		this.availableSeats = availableSeats;
		this.schedule = schedule;
		this.ticketCost = ticketCost;
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

	@Override
	public String toString() {
		return "ScheduleFlight [flight=" + flight + ", availableSeats=" + availableSeats + ", schedule=" + schedule
				+ ", ticketCost=" + ticketCost + "]";
	}
	
}
