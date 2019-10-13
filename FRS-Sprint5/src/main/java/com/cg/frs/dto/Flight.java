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
import javax.persistence.Table;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.ComponentScan;






/**
 * @author NAVYA
 *
 */

	@ComponentScan
	@Entity
	@Table(name="flight")
	public class Flight {

		// private static final Logger logger = LoggerFactory.getLogger(Flight.class);

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name="flight_id")
		private BigInteger flightId;
		@NotEmpty(message="Don't Leave Flight Model Empty")
		@Column(name="flight_model")
		private String flightModel;
		@NotEmpty(message="Don't Leave Flight Carrier Empty")
		@Column(name="carrier_name")
		private String carrierName;
		@NotNull(message="Enter Seat Capacity")
		@Column(name="seat_capacity")
		private Integer seatCapacity;
		@Column(name="flightState")
		private Boolean flightState;

		public Flight() {
			super();
		}

		public Flight(BigInteger flightId, String flightModel, String carrierName, Integer seatCapacity,
				Boolean flightState) {
			super();
			this.flightId = flightId;
			this.flightModel = flightModel;
			this.carrierName = carrierName;
			this.seatCapacity = seatCapacity;
			this.flightState = flightState;
		}

		public BigInteger getFlightId() {
			return flightId;
		}

		public void setFlightId(BigInteger flightId) {
			this.flightId = flightId;
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

		@Override
		public String toString() {
			return "Flight [flightId=" + flightId + ", flightModel=" + flightModel + ", carrierName=" + carrierName
					+ ", seatCapacity=" + seatCapacity + ", flightState=" + flightState + "]";
		}
	
	
	
}
