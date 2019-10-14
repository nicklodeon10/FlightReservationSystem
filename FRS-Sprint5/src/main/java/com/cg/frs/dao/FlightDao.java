/**
 * 
 */
package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Flight;

/**
 * @author Navya
 *
 */

@Repository("flightDao")
public interface FlightDao extends JpaRepository<Flight, BigInteger> {

	@Query("FROM Flight WHERE flightState=true")
	public List<Flight> viewAll();
	
	public Flight findByFlightNumber(BigInteger flightNumber);	
	
}
