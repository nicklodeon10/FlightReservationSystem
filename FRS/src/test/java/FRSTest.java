import static org.junit.jupiter.api.Assertions.assertEquals;
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
		user.setUserType("admin");
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
		passenger.setLuggage(12.0);
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
	public void testValidateUserWithId() throws FRSException {
		assertEquals(BigInteger.valueOf(99999), userService.validateUserWithId(BigInteger.valueOf(99999)));
		assertThrows(FRSException.class, () -> userService.validateUserWithId(BigInteger.valueOf(00000)));
	}
	
	@Test
	public void testValidateCustomerWithId() throws FRSException {
		assertEquals(BigInteger.valueOf(11111), userService.validateCustomerWithId(BigInteger.valueOf(11111)));
		assertThrows(FRSException.class, () -> userService.validateCustomerWithId(BigInteger.valueOf(00000)));
	}
	
	@Test
	public void testValidateAdminWithId() throws FRSException {
		assertEquals(BigInteger.valueOf(99999), userService.validateAdminWithId(BigInteger.valueOf(99999)));
		assertThrows(FRSException.class, () -> userService.validateCustomerWithId(BigInteger.valueOf(00000)));
	}
	
	@Test
	public void testValidatePhoneNumber() throws FRSException {
		assertEquals(true, userService.validatePhoneNumber(BigInteger.valueOf(9999999999L)));
		assertThrows(FRSException.class, ()-> userService.validatePhoneNumber(BigInteger.valueOf(11111111L)));
		assertThrows(FRSException.class, ()-> userService.validatePhoneNumber(BigInteger.valueOf(1111111111L)));
	}
	
	@Test
	public void testValidateEmail() throws FRSException {
		assertEquals(true, userService.validateEmail("abc@xyz.com"));
		assertThrows(FRSException.class, ()->userService.validateEmail("abc"));		
	}
	
	@Test
	public void testViewAirport() throws FRSException {
		assertEquals(sourceAirport, airportService.viewAirport("MUM"));
		assertThrows(FRSException.class, ()->airportService.viewAirport("ABC"));
	}
	
	@Test
	public void testValidateAirportWithCode() throws FRSException {
		assertEquals(sourceAirport, airportService.validateAirportWithCode("MUM"));
		assertThrows(FRSException.class, ()->airportService.viewAirport("ABC"));
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
		assertEquals(bookingList, bookingService.viewBooking(BigInteger.valueOf(123456789)));
		assertEquals(bookingList, bookingService.viewBooking(BigInteger.valueOf(11111)));
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
	public void testValidatePassengerCount() throws FRSException {
		assertEquals(scheduleFlight, bookingService.validatePassengerCount(scheduleFlight, 1));
		assertThrows(FRSException.class, ()->bookingService.validatePassengerCount(scheduleFlight, 100));
	}
	
	@Test
	public void testValidatePassengerName() throws FRSException {
		assertEquals(true, bookingService.validatePassengerName("Basic Passenger"));
		assertThrows(FRSException.class, ()->bookingService.validatePassengerName("123"));
	}
	
	@Test
	public void testValidateBookingWithId() throws FRSException {
		assertEquals(BigInteger.valueOf(123456789L), bookingService.validateBookingWithId(BigInteger.valueOf(123456789L)));
		assertThrows(FRSException.class, ()->bookingService.validateBookingWithId(BigInteger.valueOf(112122L)));
	}
	
	@Test
	public void testValidatePnr() throws FRSException {
		assertEquals(booking, bookingService.validatePnr(booking, BigInteger.valueOf(123L)));
		assertThrows(FRSException.class, ()->bookingService.validatePnr(booking, BigInteger.valueOf(1234L)));
	}
	
	@Test
	public void testValidatePassengerUIN() throws FRSException {
		assertEquals(true, bookingService.validatePassengerUIN(BigInteger.valueOf(123412341234L)));
		assertThrows(FRSException.class, ()->bookingService.validatePassengerUIN(BigInteger.valueOf(1234L)));
	}
	
	@Test
	public void testValidateLuggage() throws FRSException {
		assertEquals(true, bookingService.validateLuggage(13.0));
		assertThrows(FRSException.class, ()->bookingService.validateLuggage(32.0));
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
	public void testValidateFlightWithId() throws FRSException {
		assertEquals(BigInteger.valueOf(12345L), flightService.validateFlightWithId(BigInteger.valueOf(12345L)));
		assertThrows(FRSException.class, ()->flightService.validateFlightWithId(BigInteger.valueOf(123L)));
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
	public void testValidateScheduleFlightWithId() throws FRSException {
		assertEquals(BigInteger.valueOf(12345L), scheduleFlightService.validateScheduleFlightWithId(BigInteger.valueOf(12345L)));
		assertThrows(FRSException.class, ()->scheduleFlightService.validateScheduleFlightWithId(BigInteger.valueOf(121L)));
	}
	
	@Test
	public void testModifySeatCount() {
		assertEquals(2, scheduleFlightService.modifySeatCount(scheduleFlight, 2));
	}
}
