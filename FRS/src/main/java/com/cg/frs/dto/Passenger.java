package com.cg.frs.dto;

import java.math.BigInteger;

public class Passenger{
	
	private BigInteger pnrNumber;
	private String passengerName;
	private Integer passenegerAge;
	private BigInteger passenegerUIN;
	private Double luggage;
	
	public Passenger() {
		super();
	}

	public Passenger(BigInteger pnrNumber, String passengerName, Integer passenegerAge, BigInteger passenegerUIN,
			Double luggage) {
		super();
		this.pnrNumber = pnrNumber;
		this.passengerName = passengerName;
		this.passenegerAge = passenegerAge;
		this.passenegerUIN = passenegerUIN;
		this.luggage = luggage;
	}

	public BigInteger getPnrNumber() {
		return pnrNumber;
	}
	
	public void setPnrNumber(BigInteger pnrNumber) {
		this.pnrNumber = pnrNumber;
	}
	
	public String getPassengerName() {
		return passengerName;
	}
	
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	
	public Integer getPassenegerAge() {
		return passenegerAge;
	}
	
	public void setPassenegerAge(Integer passenegerAge) {
		this.passenegerAge = passenegerAge;
	}
	
	public BigInteger getPassenegerUIN() {
		return passenegerUIN;
	}
	
	public void setPassenegerUIN(BigInteger passenegerUIN) {
		this.passenegerUIN = passenegerUIN;
	}
	
	public Double getLuggage() {
		return luggage;
	}
	
	public void setLuggage(Double luggage) {
		this.luggage = luggage;
	}
	
	@Override
	public String toString() {
		return "Passenger [passengerName=" + passengerName + ", passenegerAge=" + passenegerAge + ", luggage=" + luggage
				+ "]";
	}

}
