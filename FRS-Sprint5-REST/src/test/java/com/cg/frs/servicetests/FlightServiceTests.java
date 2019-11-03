/**
 * 
 */
package com.cg.frs.servicetests;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.frs.dto.Flight;
import com.cg.frs.exception.FlightExceptions;
import com.cg.frs.repository.FlightRepository;
import com.cg.frs.service.FlightService;



/**
 * @author NAVYA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlightServiceTests {

	/*
	 * @Autowired TestRestTemplate restTemplate;
	 * 
	 * @Autowired FlightService flightService;
	 * 
	 * @Autowired FlightRepository flightRepository;
	 * 
	 * 
	 * BigInteger flightId=BigInteger.valueOf(1L); Flight flight=new Flight();
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @Test public void testAddFlight() {
	 * 
	 * 
	 * flight.setFlightNumber(flightId); flight.setFlightModel("A202");
	 * flight.setCarrierName("AIRWAYS"); flight.setSeatCapacity(100);
	 * flight.setFlightState(true);
	 * 
	 * 
	 * assertEquals(flight,flightService.saveFlight(flight));
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @Test public void testViewAllFlight() throws FlightExceptions {
	 * 
	 * assertEquals(flightRepository.findAll().size(),flightService.viewAllFlight().
	 * size());
	 * 
	 * }
	 * 
	 * @Test public void testViewFlight() throws FlightExceptions {
	 * 
	 * 
	 * assertEquals(flightId,flightService.searchFlight(flightId).getFlightNumber())
	 * ; }
	 * 
	 * 
	 * @Test public void testRemoveFlight() throws FlightExceptions {
	 * 
	 * assertEquals(true,flightService.deleteFlight(flightId));
	 * 
	 * 
	 * }
	 * 
	 */
	
}
