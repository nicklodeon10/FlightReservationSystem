package com.cg.frs.ui;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Passenger;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.dto.User;
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

public class Application {

	public static void main(String[] args) {
		
		Scanner scanner=new Scanner(System.in);
		AirportService airportService=new AirportServiceImpl();
		BookingService bookingService=new BookingServiceImpl();
		FlightService flightService=new FlightServiceImpl();
		ScheduleFlightService scheduleFlightService=new ScheduleFlightServiceImpl();
		UserService userService=new UserServiceImpl();
		
		int userTypeChoice;
		do {
			System.out.println("Enter 1 to Create New User.");
			System.out.println("Enter 2 to Show User Details.");
			System.out.println("Enter 3 to Edit a User.");
			System.out.println("Enter 4 to Delete a User.");
			System.out.println("Enter 5 for User Action.");
			System.out.println("Enter 6 for Admin Action.");
			System.out.println("Enter 0 to Exit.");
			userTypeChoice=scanner.nextInt();
			switch(userTypeChoice) {
				case 1:
					System.out.print("Enter user type (admin/customer): ");
					String userType=scanner.next();
					BigInteger userId=BigDecimal.valueOf(Math.random()*100000).toBigInteger();
					System.out.print("Enter User Name: ");
					String userName=scanner.next();
					System.out.print("Enter User Password: ");
					String userPassword=scanner.next();
					System.out.print("Enter User Phone Number: ");
					BigInteger userPhone=scanner.nextBigInteger();
					System.out.print("Enter User Email: ");
					String userEmail=scanner.next();
					User user=new User(userType, userId, userName, userPassword, userPhone, userEmail);
					userService.addUser(user);
					break;
				case 2:
					System.out.print("Enter User Id: ");
					BigInteger showUserId=scanner.nextBigInteger();
					System.out.println(userService.viewUser(showUserId));
					break;
				case 3:
					System.out.print("Enter User Id: ");
					BigInteger editUserId=scanner.nextBigInteger();
					String editUserName=scanner.next();
					System.out.print("Enter User Password: ");
					String editUserPassword=scanner.next();
					System.out.print("Enter User Phone Number: ");
					BigInteger editUserPhone=scanner.nextBigInteger();
					System.out.print("Enter User Email: ");
					String editUserEmail=scanner.next();
					User editUser=new User(userService.viewUser(editUserId).getUserType(), editUserId, editUserName, editUserPassword, editUserPhone, editUserEmail);
					userService.addUser(editUser);
					break;
				case 4:
					System.out.println("Enter User Id: ");
					BigInteger deleteUserId=scanner.nextBigInteger();
					userService.deleteUser(deleteUserId);
					break;
				case 5:
					System.out.println("Enter 1 to Create a Booking.");
					System.out.println("Enter 2 to View Previous Bookings.");
					System.out.println("Enter 3 to Modify a Booking.");
					System.out.println("Enter 4 to Cancel a Booking.");
					int userChoice=scanner.nextInt();
					switch(userChoice) {
						case 1:
							System.out.print("Enter Source Airport Code: ");
							String bookingSourceAirportCode=scanner.next(); //validate
							Airport sourceAirport=airportService.viewAirport(bookingSourceAirportCode);
							System.out.print("Enter Destination Airport Code: ");
							String bookingDestinationAirportCode=scanner.next();
							Airport destinationAirport=airportService.viewAirport(bookingDestinationAirportCode);
							System.out.print("Enter the Date of Journey (DD/MM/YYYY):");
							String bookingDateString=scanner.next();
							DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("dd/mm/yyyy");
							LocalDate bookingDate=LocalDate.parse(bookingDateString, dateFormatter);
							System.out.println("Scheduled Flights: ");
							List<ScheduleFlight> searchScheduledFlights=scheduleFlightService.viewScheduleFlights(sourceAirport, destinationAirport, bookingDate);
							for(ScheduleFlight scheduleFlight: searchScheduledFlights) {
								System.out.println(scheduleFlight.getFlight().getCarrierName());
								System.out.println(scheduleFlight.getFlight().getFlightModel());
								System.out.println(scheduleFlight.getFlight().getFlightNumber());
								System.out.println(scheduleFlight.getSchedule().getSourceAirport().getAirportName());
								System.out.println(scheduleFlight.getSchedule().getDestinationAirport().getAirportName());
								System.out.println(scheduleFlight.getSchedule().getDepartureDateTime());
								System.out.println(scheduleFlight.getSchedule().getArrivalDateTime());
								System.out.println(scheduleFlight.getAvailableSeats());
								System.out.println("--------------");
							}System.out.println("--------------");
							System.out.println("Enter User Id: ");
							BigInteger bookingUserId=scanner.nextBigInteger();
							System.out.print("Enter Chosen Flight Number: ");
							BigInteger bookingFlightNumber=scanner.nextBigInteger();
							BigInteger bookingId=BigDecimal.valueOf(Math.random()*1000000000).toBigInteger();
							List<Passenger> bookingPassengerList=new ArrayList<Passenger>();
							System.out.println("Enter No. of Passengers:");
							Integer noOfPassengers=scanner.nextInt();
							for(int i=0; i<noOfPassengers; i++) {
								BigInteger pnr=BigDecimal.valueOf(Math.random()*10000000).toBigInteger();
								System.out.print("Enter Passenger Name: ");
								String passengerName=scanner.next();
								System.out.print("Enter Passenger Age: ");
								Integer passengerAge=scanner.nextInt();
								System.out.println("Enter 12-digit Passenger UIN: ");
								BigInteger passengerUin=scanner.nextBigInteger();
								System.out.println("Enter luggage weight: ");
								Double passengerLuggage=scanner.nextDouble();
								Passenger passenger=new Passenger(pnr, passengerName, passengerAge, passengerUin, passengerLuggage);
								bookingPassengerList.add(passenger);
							}
							Booking booking=new Booking(bookingId, bookingUserId, LocalDateTime.now(), bookingPassengerList, scheduleFlightService.viewScheduleFlights(bookingFlightNumber).getTicketCost()*noOfPassengers, scheduleFlightService.viewScheduleFlights(bookingFlightNumber), noOfPassengers);
							bookingService.addBooking(booking);
							scheduleFlightService.viewScheduleFlights(bookingFlightNumber).setAvailableSeats(scheduleFlightService.viewScheduleFlights(bookingFlightNumber).getAvailableSeats()-noOfPassengers);
							break;
						case 2:
							System.out.println("Enter Search Id(userId or bookingId): ");
							BigInteger bookingSearchId=scanner.nextBigInteger();
							List<Booking> userBookingsList=bookingService.viewBooking(bookingSearchId);
							for(Booking userBooking: userBookingsList) {
								System.out.println(userBooking);   //expand
								System.out.println("--------------");
							}System.out.println("--------------");
							break;
						case 3:
							System.out.println("Enter Booking Id: ");
							BigInteger bookingEditId=scanner.nextBigInteger();
							Booking modifyBooking=bookingService.viewBooking(bookingEditId).get(0);
							List<Passenger> modifyPassengerList=modifyBooking.getPassengerList();
							System.out.println("Enter the no of passengers to remove: ");
							int removePassengerCount=scanner.nextInt();
							for(int i=0; i<removePassengerCount; i++) {
								System.out.println("Enter passenger pnr: ");
								BigInteger removePnr=scanner.nextBigInteger();
								for(Passenger passenger: modifyPassengerList) {
									if(passenger.getPnrNumber()==removePnr) {
										modifyPassengerList.remove(passenger);
										break;
									}
								}
							}
							modifyBooking.setPassengerList(modifyPassengerList);
							bookingService.addBooking(modifyBooking);
							break;
						case 4:
							System.out.println("Enter Booking Id: ");
							BigInteger bookingDeleteId=scanner.nextBigInteger();
							Booking removeBooking=bookingService.viewBooking(bookingDeleteId).get(0);
							scheduleFlightService.viewScheduleFlights(removeBooking.getFlight().getFlight().getFlightNumber()).setAvailableSeats(scheduleFlightService.viewScheduleFlights(removeBooking.getFlight().getFlight().getFlightNumber()).getAvailableSeats()+removeBooking.getNoOfPassengers());
							bookingService.deleteBooking(removeBooking.getBookingId());
							break;
					}
					break;
				case 6:
					System.out.println("Enter 1 to View User List.");
					System.out.println("Enter 2 for Flight Management.");
					System.out.println("Enter 3 for Flight Schedule Management.");
					int adminChoice=scanner.nextInt();
					switch(adminChoice) {
						case 1:
							break;
						case 2:
							System.out.println("Enter 1 to Add a Flight.");
							System.out.println("Enter 2 to Show all Flights.");
							System.out.println("Enter 3 to Search a Flight.");
							System.out.println("Enter 4 to Modify a Flight.");
							System.out.println("Enter 5 to Remove a Flight.");
							int adminFlightManageChoice=scanner.nextInt();
							switch(adminFlightManageChoice) {
								case 1:
									break;
								case 2:
									break;
								case 3:
									break;
								case 4:
									break;
								case 5:
									break;
							}
							break;
						case 3:	
							System.out.println("Enter 1 to Schedule a Flight.");
							System.out.println("Enter 2 to Show all Scheduled Flights.");
							System.out.println("Enter 3 to Search a Scheduled Flight.");
							System.out.println("Enter 4 to Modify a Scheduled Flight.");
							System.out.println("Enter 5 to Remove a Scheduled Flight.");
							int adminScheduleChoice=scanner.nextInt();
							switch(adminScheduleChoice) {
								case 1:
									break;
								case 2:
									break;
								case 3:
									break;
								case 4:
									break;
								case 5:
									break;
							}
							break;
					}
					break;
			}
		}while(userTypeChoice!=0);
		scanner.close();
	}

}
