/**
 * 
 */
package com.cg.frs.test;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.frs.dto.Booking;
import com.cg.frs.exception.InvalidBookingException;
import com.cg.frs.repository.BookingRepository;
import com.cg.frs.service.BookingService;

/**
 * @author DEVANG
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookingServiceTests {
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	TestRestTemplate restTemplate;
	
	@Autowired
	BookingRepository bookingRepository;
	
	BigInteger bookingId=BigInteger.valueOf(1L);
	BigInteger userId=BigInteger.valueOf(1L);
	Booking booking=new Booking();
	
	@Test
	public void testAddBooking() {
		booking.setBookingState(true);
		booking.setUserId(userId);
		assertEquals(booking, bookingService.addBooking(booking));
	}
	
	@Test
	public void testViewAllBooking() throws InvalidBookingException {
		assertEquals(bookingRepository.findAll().size(),bookingService.viewBooking().size());
	}
	
	@Test
	public void testViewBooking() throws InvalidBookingException {
		assertEquals(bookingId, bookingService.viewBooking(bookingId).getBookingId());
	}
	
	@Test(expected = InvalidBookingException.class)
	public void testViewBookingException() throws InvalidBookingException {
		bookingService.viewBooking(BigInteger.valueOf(99999L)).getBookingId();
	}
	
	@Test
	public void testViewBookingByUser() throws InvalidBookingException {
		assertEquals(bookingRepository.findByUserId(userId).size(), bookingService.viewBookingsByUser(userId).size());
	}
	
	@Test(expected = InvalidBookingException.class)
	public void testViewBookingByUserException() throws InvalidBookingException {
		bookingService.viewBookingsByUser(BigInteger.valueOf(99999L));
	}
	
	@Test
	public void testCancelBooking() throws InvalidBookingException {
		assertEquals(true, bookingService.deleteBooking(bookingId));
	}
	
}
