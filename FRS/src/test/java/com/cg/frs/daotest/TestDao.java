package com.cg.frs.daotest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Flight;
import com.cg.frs.dto.Passenger;
import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.dto.User;
import com.cg.frs.exception.FRSException;
import com.cg.frs.dao.AirportDao;
import com.cg.frs.dao.AirportDaoImpl;
import com.cg.frs.dao.BookingDao;
import com.cg.frs.dao.BookingDaoImpl;
import com.cg.frs.dao.FlightDao;
import com.cg.frs.dao.FlightDaoImpl;
import com.cg.frs.dao.ScheduleFlightDao;
import com.cg.frs.dao.ScheduleFlightDaoImpl;
import com.cg.frs.dao.UserDao;
import com.cg.frs.dao.UserDaoImpl;

public class TestDao {
	static UserDao userDao;
	static AirportDao airportDao;
	static BookingDao bookingDao;
	static FlightDao flightDao;
	static ScheduleFlightDao scheduleFlightDao;
	
	User user;
	Airport sourceAirport;
	Airport destinationAirport;
	Booking booking;
	Flight flight;
	ScheduleFlight scheduleFlight;
	Schedule schedule;
	Passenger passenger;
	
	@BeforeAll
	public static void beforeTest() {
		userDao=new UserDaoImpl();
		airportDao=new AirportDaoImpl();
		bookingDao=new BookingDaoImpl();
		flightDao=new FlightDaoImpl();
		scheduleFlightDao=new ScheduleFlightDaoImpl();
	}
	
	@AfterAll
	public static void afterTest() {
		userDao=null;
		airportDao=null;
	}
	
	@BeforeEach
	public void beforeEachTest() {
		user=new User();
		user.setUserType("customer");
		user.setUserId(BigInteger.valueOf(99999L));
		user.setUserName("ADMIN");
		user.setUserPassword("ADMIN");
		user.setUserPhone(BigInteger.valueOf(9999999999L));
		user.setEmail("admin@frs.com");
		sourceAirport=new Airport();
		sourceAirport.setAirportCode("DEL");
		sourceAirport.setAirportLocation("Delhi");
		sourceAirport.setAirportName("Indira Gandhi International Airport");
		destinationAirport=new Airport();
		destinationAirport.setAirportCode("MUM");
		destinationAirport.setAirportLocation("Mumbai");
		destinationAirport.setAirportName("Chhatrapati Shivaji International Airport");
		flight=new Flight();
		flight.setCarrierName("BestCarrier");
		flight.setFlightModel("BestModel111");
		flight.setFlightNumber(BigInteger.valueOf(12345L));
		flight.setSeatCapacity(50);
		schedule=new Schedule();
		schedule.setSourceAirport(sourceAirport);
		schedule.setDestinationAirport(destinationAirport);
		schedule.setDepartureDateTime(LocalDateTime.now());
		schedule.setArrivalDateTime(LocalDateTime.now());
		scheduleFlight=new ScheduleFlight();
		scheduleFlight.setFlight(flight);
		scheduleFlight.setSchedule(schedule);
		scheduleFlight.setTicketCost(5000D);
		scheduleFlight.setAvailableSeats(50);
		passenger=new Passenger();
		passenger.setPassengerName("BestPassenger");
		passenger.setPassengerAge(21);
		passenger.setPassengerUIN(BigInteger.valueOf(123412341234L));
		passenger.setPnrNumber(BigInteger.valueOf(123));
		booking=new Booking();
		booking.setBookingId(BigInteger.valueOf(123456789));
		booking.setUserId(BigInteger.valueOf(11111L));
		booking.setBookingDate(LocalDateTime.now());
		booking.setScheduleFlight(scheduleFlight);
		booking.setTicketCost(5000D);
		booking.setNoOfPassengers(1);
		List<Passenger> passengerList=new ArrayList<Passenger>();
		passengerList.add(passenger);
		booking.setPassengerList(passengerList);

}
	@Test
	public void testAddUser() {
		assertEquals(user, userDao.addUser(user));
	}
	
	@Test
	public void testViewUser() {
		assertEquals(user, userDao.showUser(BigInteger.valueOf(99999)));
	}

	@Test
	public void testModifyUser() {
		assertEquals(user, userDao.updateUser(user));
	}
	
	@Test
	public void testDeleteUser() {
		assertEquals(true, userDao.removeUser(BigInteger.valueOf(99999)));
	}
	
	
	@Test
	public void testAddBooking() {
		assertEquals(booking, bookingDao.addBooking(booking));
	}
	
	//Complete
	@Test
	public void testViewBooking() throws FRSException {

	}
	
	@Test
	public void testDeleteBooking() throws FRSException {
		assertEquals(true, bookingDao.removeBooking(BigInteger.valueOf(123456789)));
	}
	
	@Test
	public void testAddFlight() {
		assertEquals(flight, flightDao.addFlight(flight));
	}
	
	@Test
	public void testModifyFlight() {
		assertEquals(flight, flightDao.updateFlight(flight));
	}
	
	@Test
	public void testViewFlight() throws FRSException {
		assertEquals(flight, flightDao.viewFlight());
		assertThrows(FRSException.class, ()->flightDao.viewFlight());
	}
	
	@Test
	public void testDeleteFlight() {
		assertEquals(true, flightDao.deleteFlight(BigInteger.valueOf(12345L)));
	}
	
	@Test
	public void testAddScheduleFlight() {
		assertEquals(scheduleFlight, scheduleFlightDao.addScheduleFlight(scheduleFlight));
	}
	
	@Test
	public void testViewScheduleFlight() {
		assertEquals(scheduleFlight, scheduleFlightDao.viewScheduleFlight());
	}
	
	@Test
	public void testModifyScheduleFlight() {
		assertEquals(scheduleFlight, scheduleFlightDao.updateScheduleFlight(scheduleFlight));
	}
	
	@Test
	public void testDeleteScheduleFlight() {
		assertEquals(true, scheduleFlightDao.deleteScheduleFlight(BigInteger.valueOf(12345L)));
	}
	
}
