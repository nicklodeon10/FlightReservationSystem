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
import com.cg.frs.service.AirportService;
import com.cg.frs.service.AirportServiceImpl;
import com.cg.frs.service.BookingService;
import com.cg.frs.service.BookingServiceImpl;
import com.cg.frs.service.FlightService;
import com.cg.frs.service.FlightServiceImpl;
import com.cg.frs.service.ScheduleFlightService;
import com.cg.frs.service.ScheduleFlightServiceImpl;
import com.cg.frs.service.UserService;
import com.cg.frs.service.UserServiceImpl;

class FRSTest {

	static UserService userService;
	static AirportService airportService;
	static BookingService bookingService;
	static FlightService flightService;
	static ScheduleFlightService scheduleFlightService;
	
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
		userService=new UserServiceImpl();
		airportService=new AirportServiceImpl();
		bookingService=new BookingServiceImpl();
		flightService=new FlightServiceImpl();
		scheduleFlightService=new ScheduleFlightServiceImpl();
	}
	
	@AfterAll
	public static void afterTest() {
		userService=null;
		airportService=null;
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
		booking.setFlight(scheduleFlight);
		booking.setTicketCost(5000D);
		booking.setNoOfPassengers(1);
		List<Passenger> passengerList=new ArrayList<Passenger>();
		passengerList.add(passenger);
		booking.setPassengerList(passengerList);
	}
	
	@Test
	public void testAddUser() {
		assertEquals(user, userService.addUser(user));
	}
	
	@Test
	public void testViewUser() {
		assertEquals(user, userService.viewUser(BigInteger.valueOf(99999)));
	}

	@Test
	public void testModifyUser() {
		assertEquals(user, userService.updateUser(user));
	}
	
	@Test
	public void testDeleteUser() {
		assertEquals(true, userService.deleteUser(BigInteger.valueOf(99999)));
	}
	
	@Test
	public void testCompareAirport() throws FRSException {
		assertThrows(FRSException.class, ()->airportService.compareAirport(sourceAirport, sourceAirport));
	}
	
	@Test
	public void testAddBooking() {
		assertEquals(booking, bookingService.addBooking(booking));
	}
	
	@Test
	public void testViewBooking() throws FRSException {
		List<Booking> bookingList=new ArrayList<Booking>();
		bookingList.add(booking);
		assertNotNull(bookingList);
		System.out.println("88888888888888888888888   Booking List");
		bookingList.forEach(System.out::println);
	}
	
	@Test
	public void testModifyBooking() {
		Booking modifyBooking=booking;
		booking.setNoOfPassengers(booking.getNoOfPassengers()-2);
		assertEquals(modifyBooking, bookingService.modifyBooking(booking, 2));
	}
	
	@Test
	public void testDeleteBooking() throws FRSException {
		assertEquals(true, bookingService.deleteBooking(BigInteger.valueOf(123456789)));
	}
	
	@Test
	public void testAddFlight() {
		assertEquals(flight, flightService.addFlight(flight));
	}
	
	@Test
	public void testModifyFlight() {
		assertEquals(flight, flightService.modifyFlight(flight));
	}
	
	@Test
	public void testViewFlight() throws FRSException {
		assertEquals(flight, flightService.viewFlight(BigInteger.valueOf(12345L)));
		assertThrows(FRSException.class, ()->flightService.viewFlight(BigInteger.valueOf(1001L)));
	}
	
	@Test
	public void testDeleteFlight() {
		assertEquals(true, flightService.deleteFlight(BigInteger.valueOf(12345L)));
	}
	
	@Test
	public void testAddScheduleFlight() {
		assertEquals(scheduleFlight, scheduleFlightService.addScheduleFlight(scheduleFlight));
	}
	
	@Test
	public void testViewScheduleFlight() {
		assertEquals(scheduleFlight, scheduleFlightService.viewScheduleFlights(BigInteger.valueOf(12345L)));
	}
	
	@Test
	public void testModifyScheduleFlight() {
		assertEquals(scheduleFlight, scheduleFlightService.modifyScheduleFlight(scheduleFlight));
	}
	
	@Test
	public void testDeleteScheduleFlight() {
		assertEquals(true, scheduleFlightService.deleteScheduleFlight(BigInteger.valueOf(12345L)));
	}
	
	@Test
	public void testModifySeatCount() {
		assertEquals(2, scheduleFlightService.modifySeatCount(scheduleFlight, 2));
	}
}
