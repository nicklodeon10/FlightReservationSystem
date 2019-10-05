package com.cg.frs.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name="Airport")
public class Airport {

	@Id
	@Column(name="airport_code")
	
	private String airportCode;
	@Column(name="airport_name")
	@NotEmpty(message = "Airport Name is Empty ")
	private String airportName;
	@Column(name="airport_location")
	@NotEmpty(message = "Airport location is Empty ")
	private String airportLocation;

	public Airport() {
		super();
	}

	public Airport(String airportName, String airportLocation, String airportCode) {
		super();
		this.airportName = airportName;
		this.airportLocation = airportLocation;
		this.airportCode = airportCode;
	}

	@Override
	public String toString() {
		return "Airport [airportName=" + airportName + ", airportLocation=" + airportLocation + ", airportCode="
				+ airportCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airportCode == null) ? 0 : airportCode.hashCode());
		result = prime * result + ((airportLocation == null) ? 0 : airportLocation.hashCode());
		result = prime * result + ((airportName == null) ? 0 : airportName.hashCode());
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
		Airport other = (Airport) obj;
		if (airportCode == null) {
			if (other.airportCode != null)
				return false;
		} else if (!airportCode.equals(other.airportCode))
			return false;
		if (airportLocation == null) {
			if (other.airportLocation != null)
				return false;
		} else if (!airportLocation.equals(other.airportLocation))
			return false;
		if (airportName == null) {
			if (other.airportName != null)
				return false;
		} else if (!airportName.equals(other.airportName))
			return false;
		return true;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportLocation() {
		return airportLocation;
	}

	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

}
