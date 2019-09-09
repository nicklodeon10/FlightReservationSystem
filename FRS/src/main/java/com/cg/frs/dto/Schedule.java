package com.cg.frs.dto;

import java.time.LocalTime;

public class Schedule{	

	private Airport sourceAirport;
	private Airport destinationAirport;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	
	public Schedule() {
		super();
	}
	
	public Schedule(Airport sourceAirport, Airport destinationAirport, LocalTime departureTime, LocalTime arrivalTime) {
		super();
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}
	
	public Airport getSourceAirport() {
		return sourceAirport;
	}
	
	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}
	
	public Airport getDestinationAirport() {
		return destinationAirport;
	}
	
	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}
	
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	@Override
	public String toString() {
		return "Schedule [sourceAirport=" + sourceAirport + ", destinationAirport=" + destinationAirport
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + "]";
	}
	
}

