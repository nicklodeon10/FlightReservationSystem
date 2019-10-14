/**
 * 
 */
package com.cg.frs.servicetest;

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
import com.cg.frs.repository.FlightDao;
import com.cg.frs.service.FlightService;



/**
 * @author NAVYA
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlightServiceTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	FlightService flightService;

	@Autowired
	FlightDao flightDao;


	BigInteger flightId=BigInteger.valueOf(1L);
	Flight flight=new Flight();





	@Test
	public void testAddFlight() {

		
		flight.setFlightNumber(flightId);
		flight.setFlightModel("A202");
		flight.setCarrierName("AIRWAYS");
		flight.setSeatCapacity(100);
		flight.setFlightState(true);
	
		
		assertEquals(flight,flightService.addFlight(flight));

	}



	@Test
	public void testViewAllFlight()   {
		
		assertEquals(flightDao.findAll().size(),flightService.viewAllFlight().size());
		
	}

	@Test
	public void testViewFlight()  {
		
		
		assertEquals(flightId,flightService.searchFlight(flightId).getFlightNumber());
	}


	@Test
	public void testRemoveFlight() {
		
		assertEquals(true,flightService.deleteFlight(flightId));
		
		
	}
	 

	
}
