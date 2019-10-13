/**
 * 
 */
package com.cg.frs.controllertest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import com.cg.frs.service.AirportService;
import com.cg.frs.service.BookingService;

/**
 * @author DEVANG
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookingControllerTest {

	@Autowired
	BookingService bookingService;
	
	@Autowired
	AirportService airportService;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	public void testHome() {
		ModelAndView home=restTemplate.getForObject("/", ModelAndView.class);
		assertThat(home.equals(new ModelAndView("Home", "airportList", airportService.viewAirport())));
	}
}
