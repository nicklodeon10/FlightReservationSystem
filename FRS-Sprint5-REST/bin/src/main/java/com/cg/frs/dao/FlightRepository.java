package com.cg.frs.SpringBootFrs.repository;
/**
 * @author Navya
 *
 */
import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.frs.SpringBootFrs.dto.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, BigInteger> {

	public Flight save(Flight flight);
	
	@Query("FROM Flight WHERE flightState=true")
	public List<Flight> viewAll();
	
	public Flight findByFlightId(BigInteger Id);
	
	//@Modifying
	//@Query(value="UPDATE Flight SET flightMode=:model,carrierName=:carrier,flightCapacity=:capacity WHERE flightId=Id")
	//public Flight modifyByFlightId(BigInteger Id);
	
	

	
	
}
