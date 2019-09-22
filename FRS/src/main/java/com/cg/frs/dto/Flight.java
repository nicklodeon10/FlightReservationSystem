package com.cg.frs.dto;

import java.math.BigInteger;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Flight {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Override
	public int hashCode() {
	
		return flightNumber.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		boolean status=false;
		if (obj != null)
			if(obj.hashCode()==this.hashCode())
				status=true;
		else
			status=false;
		return status;
	}

}
