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

public interface FlightRepository extends JpaRepository<Flight, BigInteger> {

	/*
	 * Author: NAVYA 
	 * Description: Repository of flight for adding,view and find
	 *  Created Date: 09/10/2019 
	 *  Last Modified: 14/10/2019
	 * -
	 */
	
	// Saves flight
	public Flight save(Flight flight);

	// shows all flight where state is true
	@Query("FROM Flight WHERE flightState=true")
	public List<Flight> viewAll();

	// Search flight by id
	public Flight findByFlightNumber(BigInteger flightId);

}