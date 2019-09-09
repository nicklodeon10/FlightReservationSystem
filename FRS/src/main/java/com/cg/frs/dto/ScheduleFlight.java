package com.cg.frs.dto;

public class ScheduleFlight  {

	private Flight flight=new Flight();
	private Integer availableSeats;
	private Schedule schedule;
	
	public ScheduleFlight() {
		super();
	}

	public ScheduleFlight(Flight flight, Integer availableSeats, Schedule schedule) {
		super();
		this.flight = flight;
		this.availableSeats = availableSeats;
		this.schedule = schedule;
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

	@Override
	public String toString() {
		return "ScheduleFlight [flight=" + flight + ", availableSeats=" + availableSeats + ", schedule=" + schedule
				+ "]";
	}
	
}
