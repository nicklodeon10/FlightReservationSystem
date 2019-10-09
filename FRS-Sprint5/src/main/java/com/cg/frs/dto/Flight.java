/**
 * 
 */
package com.cg.frs.dto;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author DEVANG
 *
 */

@Entity(name="Flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="flight_number")
	private BigInteger flightNumber;
	@Column(name="flight_model")
	@NotEmpty(message="Flight Model is Empty")
	private String flightModel;
	@Column(name="carrier_name")
	@NotEmpty(message="Carrier Name is Empty")
	private String carrierName;
	@Column(name="seat_capacity")
	@NotNull(message="Seat Capacity is Empty")
	private Integer seatCapacity;
	@Column(name="flightState")
	private Boolean flightState;

	public Flight() {
		super();
	}

	public Flight(BigInteger flightNumber, String flightModel, String carrierName, Integer seatCapacity,
			Boolean flightState) {
		super();
		this.flightNumber = flightNumber;
		this.flightModel = flightModel;
		this.carrierName = carrierName;
		this.seatCapacity = seatCapacity;
		this.flightState = flightState;
	}

	@Override
	public String toString() {
		return "Flight [flightNumber=" + flightNumber + ", flightModel=" + flightModel + ", carrierName=" + carrierName
				+ ", seatCapacity=" + seatCapacity + ", flightState=" + flightState + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrierName == null) ? 0 : carrierName.hashCode());
		result = prime * result + ((flightModel == null) ? 0 : flightModel.hashCode());
		result = prime * result + ((flightNumber == null) ? 0 : flightNumber.hashCode());
		result = prime * result + ((flightState == null) ? 0 : flightState.hashCode());
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
		if (flightState == null) {
			if (other.flightState != null)
				return false;
		} else if (!flightState.equals(other.flightState))
			return false;
		if (seatCapacity == null) {
			if (other.seatCapacity != null)
				return false;
		} else if (!seatCapacity.equals(other.seatCapacity))
			return false;
		return true;
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

	public Boolean getFlightState() {
		return flightState;
	}

	public void setFlightState(Boolean flightState) {
		this.flightState = flightState;
	}

}
