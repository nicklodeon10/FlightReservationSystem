package com.cg.frs.repository;
/**
 * @author NAVYA
 *
 */
import java.math.BigInteger;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, BigInteger> {

	public Flight save(Flight flight);
	
	@Query("FROM Flight WHERE flightState=true")
	public List<Flight> viewAll();
	
	public Flight findByFlightNumber(BigInteger Id);	
	
}
