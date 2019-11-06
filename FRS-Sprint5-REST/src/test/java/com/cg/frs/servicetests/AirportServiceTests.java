package com.cg.frs.servicetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.frs.dto.Airport;
import com.cg.frs.exception.InvalidAirportException;
import com.cg.frs.service.AirportService;

/**
 * @author DEVANG
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AirportServiceTests {

	/*
	 * @Autowired AirportService airportService;
	 * 
	 * @Autowired TestRestTemplate restTemplate;
	 * 
	 * @Test public void testViewAllAirports1() { assertEquals(7,
	 * airportService.viewAirport().size()); }
	 * 
	 * @Test public void testViewAllAirports2() {
	 * assertNotNull(airportService.viewAirport()); }
	 * 
	 * @Test public void testViewAirport() throws InvalidAirportException {
	 * assertEquals("MUM", airportService.viewAirport("MUM").getAirportCode()); }
	 * 
	 * @Test(expected=InvalidAirportException.class) public void
	 * testViewAirportException() throws InvalidAirportException {
	 * airportService.viewAirport("GOOF"); }
	 * 
	 * @Test public void testValidateAirport() throws InvalidAirportException {
	 * assertEquals(true, airportService.validateAirportWithCode("DEL")); }
	 * 
	 * 
	 * @Test(expected=InvalidAirportException.class) public void
	 * testValidateAirportException() throws InvalidAirportException {
	 * airportService.validateAirportWithCode("GOOF"); }
	 * 
	 * @Test public void testCompareAirport() throws InvalidAirportException {
	 * Airport src=airportService.viewAirport("MUM"); Airport
	 * dest=airportService.viewAirport("DEL"); assertEquals(false,
	 * airportService.compareAirport(src, dest)); }
	 * 
	 * @Test(expected=InvalidAirportException.class) public void
	 * testCompareAirportException() throws InvalidAirportException { Airport
	 * src=airportService.viewAirport("MUM"); Airport
	 * dest=airportService.viewAirport("MUM"); assertEquals(false,
	 * airportService.compareAirport(src, dest)); }
	 */
}
