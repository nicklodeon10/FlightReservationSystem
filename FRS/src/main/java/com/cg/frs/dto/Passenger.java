package com.cg.frs.dto;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private BigInteger pnrNumber;
	@Column
	private String passengerName;
	@Column
	private Integer passengerAge;
	@Column
	private BigInteger passengerUIN;
	@Column
	private Boolean passengerState;

	public Passenger() {
		super();
	}

	public Passenger(BigInteger pnrNumber, String passengerName, Integer passengerAge, BigInteger passengerUIN,
			Boolean passengerState) {
		super();
		this.pnrNumber = pnrNumber;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerUIN = passengerUIN;
		this.passengerState = passengerState;
	}

	@Override
	public String toString() {
		return "Passenger [pnrNumber=" + pnrNumber + ", passengerName=" + passengerName + ", passengerAge="
				+ passengerAge + ", passengerUIN=" + passengerUIN + ", passengerState=" + passengerState + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((passengerAge == null) ? 0 : passengerAge.hashCode());
		result = prime * result + ((passengerName == null) ? 0 : passengerName.hashCode());
		result = prime * result + ((passengerState == null) ? 0 : passengerState.hashCode());
		result = prime * result + ((passengerUIN == null) ? 0 : passengerUIN.hashCode());
		result = prime * result + ((pnrNumber == null) ? 0 : pnrNumber.hashCode());
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
		Passenger other = (Passenger) obj;
		if (passengerAge == null) {
			if (other.passengerAge != null)
				return false;
		} else if (!passengerAge.equals(other.passengerAge))
			return false;
		if (passengerName == null) {
			if (other.passengerName != null)
				return false;
		} else if (!passengerName.equals(other.passengerName))
			return false;
		if (passengerState == null) {
			if (other.passengerState != null)
				return false;
		} else if (!passengerState.equals(other.passengerState))
			return false;
		if (passengerUIN == null) {
			if (other.passengerUIN != null)
				return false;
		} else if (!passengerUIN.equals(other.passengerUIN))
			return false;
		if (pnrNumber == null) {
			if (other.pnrNumber != null)
				return false;
		} else if (!pnrNumber.equals(other.pnrNumber))
			return false;
		return true;
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

	public Integer getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(Integer passengerAge) {
		this.passengerAge = passengerAge;
	}

	public BigInteger getPassengerUIN() {
		return passengerUIN;
	}

	public void setPassengerUIN(BigInteger passengerUIN) {
		this.passengerUIN = passengerUIN;
	}

	public Boolean getPassengerState() {
		return passengerState;
	}

	public void setPassengerState(Boolean passengerState) {
		this.passengerState = passengerState;
	}

}
