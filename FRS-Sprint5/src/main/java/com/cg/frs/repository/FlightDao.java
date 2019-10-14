package com.cg.frs.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.frs.dto.Flight;

/**
 * @author NAVYA
 *
 */

public interface FlightDao extends JpaRepository<Flight, BigInteger> {

	// Saves flight
	public Flight save(Flight flight);

	// shows all flight where state is true
	@Query("FROM Flight WHERE flightState=true")
	public List<Flight> viewAll();

	// Search flight by id
	public Flight findByFlightNumber(BigInteger flightId);

}