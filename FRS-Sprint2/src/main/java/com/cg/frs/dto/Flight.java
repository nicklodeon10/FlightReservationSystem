package com.cg.frs.dto;

import java.math.BigInteger;

public class Flight {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrierName == null) ? 0 : carrierName.hashCode());
		result = prime * result + ((flightModel == null) ? 0 : flightModel.hashCode());
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + ((seatCapacity == null) ? 0 : seatCapacity.hashCode());
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
		Flight other = (Flight) obj;
		if (carrierName == null) {
			if (other.carrierName != null)
				return false;
		} else if (!carrierName.equals(other.carrierName))
			return false;
		if (flightModel == null) {
			if (other.flightModel != null)
				return false;
		} else if (!flightModel.equals(other.flightModel))
			return false;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		if (seatCapacity == null) {
			if (other.seatCapacity != null)
				return false;
		} else if (!seatCapacity.equals(other.seatCapacity))
			return false;
		return true;
	}

	private BigInteger flightNumber;
	private String flightModel;
	private String carrierName;
	private Integer seatCapacity;
	
	public Flight() {
		super();
	}
	
	public Flight(BigInteger flightNumber, String flightModel, String carrierName, Integer seatCapacity) {
		super();
		this.flightNumber = flightNumber;
		this.flightModel = flightModel;
		this.carrierName = carrierName;
		this.seatCapacity = seatCapacity;
	}
	
	public BigInteger getFlightNumber() {
		return flightNumber;
	}
	
	public void setFlightNumber(BigInteger flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public String getFlightModel() {
		return flightModel;
	}
	
	public void setFlightModel(String flightModel) {
		this.flightModel = flightModel;
	}
	
	public String getCarrierName() {
		return carrierName;
	}
	
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	
	public Integer getSeatCapacity() {
		return seatCapacity;
	}
	
	public void setSeatCapacity(Integer seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
	
}
