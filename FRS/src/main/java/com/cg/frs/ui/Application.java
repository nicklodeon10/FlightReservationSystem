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

public class Application {

	public static void main(String[] args) {
		
		Scanner scanner=new Scanner(System.in);
		AirportService airportService=new AirportServiceImpl();
		airportService.addAirport();
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
					User user;
					while(true) {
						try {
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
							user=new User(userType, userId, userName, userPassword, userPhone, userEmail);
							userService.validateUser(user);
							break;
						}catch(FRSException exception) {
							System.err.println(exception.getMessage());
							continue;
						}
					}
					userService.addUser(user);
					System.out.println("User Created with UserId: "+user.getUserId());
					break;
				case 2:
					BigInteger showUserId;
					while(true) {
						try {
							System.out.print("Enter User Id: ");
							showUserId=scanner.nextBigInteger();
							userService.validateUserWithId(showUserId);
							break;
						}catch(FRSException exception) {
							System.err.println(exception.getMessage());
							continue;
						}
					}	
					System.out.println("UserName: "+userService.viewUser(showUserId).getUserName());
					System.out.println("UserId: "+userService.viewUser(showUserId).getUserId());
					System.out.println("Email: "+userService.viewUser(showUserId).getEmail());
					System.out.println("Phone: "+userService.viewUser(showUserId).getUserPhone());
					System.out.println("UserType: "+userService.viewUser(showUserId).getUserType());
					break;
				case 3:
					BigInteger editUserId;
					while(true) {
						try {
							System.out.print("Enter User Id: ");
							editUserId=scanner.nextBigInteger();
							userService.validateUserWithId(editUserId);
							break;
						}catch(FRSException exception) {
							System.err.println(exception.getMessage());
							continue;
						}
					}
					User editUser;
					while(true) {
						try {
							System.out.print("Enter User Name:");
							String editUserName=scanner.next();
							System.out.print("Enter User Password: ");
							String editUserPassword=scanner.next();
							System.out.print("Enter User Phone Number: ");
							BigInteger editUserPhone=scanner.nextBigInteger();
							System.out.print("Enter User Email: ");
							String editUserEmail=scanner.next();
							editUser=new User(userService.viewUser(editUserId).getUserType(), editUserId, editUserName, editUserPassword, editUserPhone, editUserEmail);
							userService.validateUser(editUser);
							break;
						}catch(FRSException exception) {
							System.err.println(exception.getMessage());
							continue;
						}
					}
					userService.addUser(editUser);
					break;
				case 4:
					BigInteger deleteUserId;
					while(true) {
						try {
							System.out.println("Enter User Id: ");
							deleteUserId=scanner.nextBigInteger();
							userService.validateUserWithId(deleteUserId);
							break;
						}catch(FRSException exception) {
							System.err.println(exception.getMessage());
							continue;
						}
					}
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
							String bookingSourceAirportCode;
							Airport sourceAirport;
							Airport destinationAirport;
							while(true) {
								try {
									System.out.print("Enter Source Airport Code: ");
									bookingSourceAirportCode=scanner.next();
									airportService.validateAirportWithCode(bookingSourceAirportCode);
									sourceAirport=airportService.viewAirport(bookingSourceAirportCode);
									break;
								}catch(FRSException exception) {
									System.err.println(exception.getMessage());
									continue;
								}
							} 
							String bookingDestinationAirportCode;
							while(true) {
								try {
									System.out.print("Enter Destination Airport Code: ");
									bookingDestinationAirportCode=scanner.next();
									airportService.validateAirportWithCode(bookingDestinationAirportCode);
									destinationAirport=airportService.viewAirport(bookingDestinationAirportCode);
									airportService.compareAirport(sourceAirport, destinationAirport);
									break;
								}catch(FRSException exception) {
									System.err.println(exception.getMessage());
									continue;
								}
							}							
							System.out.print("Enter the Date of Journey (YYYY-MM-DD):");
							String bookingDateString=scanner.next();
							LocalDate bookingDate = LocalDate.parse(bookingDateString);
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
							BigInteger bookingUserId;
							while(true) {
								try {
									System.out.println("Enter User Id: ");
									bookingUserId=scanner.nextBigInteger();
									userService.validateCustomerWithId(bookingUserId);
									break;
								}catch(FRSException exception) {
									System.err.println(exception.getMessage());
									continue;
								}
							}
							BigInteger bookingFlightNumber;
							while(true) {
								try {
									System.out.print("Enter Chosen Flight Number: ");
									bookingFlightNumber=scanner.nextBigInteger();
									scheduleFlightService.validateScheduleFlightWithId(bookingFlightNumber);
									break;
								}catch(FRSException exception) {
									System.err.println(exception);
									continue;
								}
							}
							BigInteger bookingId=BigDecimal.valueOf(Math.random()*1000000000).toBigInteger();
							List<Passenger> bookingPassengerList=new ArrayList<Passenger>();
							Integer noOfPassengers;
							while(true) {
								try {
									System.out.println("Enter No. of Passengers:");
									noOfPassengers=scanner.nextInt();
									bookingService.validatePassengerCount(scheduleFlightService.viewScheduleFlights(bookingFlightNumber), noOfPassengers);
									break;
								}catch(FRSException exception) {
									System.err.println(exception.getMessage());
									continue;
								}
							}
							for(int i=0; i<noOfPassengers; i++) {
								Passenger passenger;
								while(true) {
									try {
										BigInteger pnr=BigDecimal.valueOf(Math.random()*10000000).toBigInteger();
										System.out.print("Enter Passenger Name: ");
										String passengerName=scanner.nextLine();
										System.out.print("Enter Passenger Age: ");
										Integer passengerAge=scanner.nextInt();
										System.out.println("Enter 12-digit Passenger UIN: ");
										BigInteger passengerUin=scanner.nextBigInteger();
										System.out.println("Enter luggage weight: ");
										Double passengerLuggage=scanner.nextDouble();
										passenger=new Passenger(pnr, passengerName, passengerAge, passengerUin, passengerLuggage);
										bookingService.validateBooking(passenger);
										break;
									}catch(FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								bookingPassengerList.add(passenger);
							}
							Booking booking=new Booking(bookingId, bookingUserId, LocalDateTime.now(), bookingPassengerList, scheduleFlightService.viewScheduleFlights(bookingFlightNumber).getTicketCost()*noOfPassengers, scheduleFlightService.viewScheduleFlights(bookingFlightNumber), noOfPassengers);
							bookingService.addBooking(booking);
							System.out.println("Booking Successful with Booking Id: "+bookingId);
							break;
						case 2:
							BigInteger bookingSearchId;
							while(true) {
								try {
									System.out.println("Enter Search Id(userId or bookingId): ");
									bookingSearchId=scanner.nextBigInteger();
									bookingService.validateBookingWithId(bookingSearchId);
									break;
								}catch(FRSException exception) {
									System.err.println(exception.getMessage());
									continue;
								}
							}
							List<Booking> userBookingsList=bookingService.viewBooking(bookingSearchId);
							for(Booking userBooking: userBookingsList) {
								System.out.println("Booking Id: "+userBooking.getBookingId());
								System.out.println("Booking Date: "+userBooking.getBookingDate());
								System.out.println("Booked by: "+userBooking.getUserId());
								System.out.println("Departure Time: "+userBooking.getFlight().getSchedule().getDepartureDateTime());
								System.out.println("Source Airport: "+userBooking.getFlight().getSchedule().getSourceAirport());
								System.out.println("Arrival Time: "+userBooking.getFlight().getSchedule().getArrivalDateTime());
								System.out.println("Destination Aiport: "+userBooking.getFlight().getSchedule().getDestinationAirport());
								System.out.println("Ticket Cost: "+userBooking.getTicketCost());
								System.out.println("Passengers:");
								List<Passenger> bookingPassengerList1=userBooking.getPassengerList();
								for(Passenger passenger: bookingPassengerList1) {
									System.out.println("Name: "+passenger.getPassengerName());
									System.out.println("Age: "+passenger.getPassengerAge());
									System.out.println("PNR: "+passenger.getPnrNumber());
									System.out.println("UIN: "+passenger.getPassengerUIN());
									System.out.println("Luggage: "+passenger.getLuggage());
								}
								System.out.println("--------------");
							}System.out.println("--------------");
							break;
						case 3:
							BigInteger bookingEditId;
							while(true) {
								try {
									System.out.println("Enter Booking Id: ");
									bookingEditId=scanner.nextBigInteger();
									bookingService.validateBookingWithId(bookingEditId);
									break;
								}catch(FRSException exception) {
									System.err.println(exception.getMessage());
									continue;
								}
							}
							Booking modifyBooking=bookingService.viewBooking(bookingEditId).get(0);
							List<Passenger> modifyPassengerList=modifyBooking.getPassengerList();
							System.out.println("Enter the no of passengers to remove: ");
							int removePassengerCount=scanner.nextInt();
							for(int i=0; i<removePassengerCount; i++) {
								BigInteger removePnr;
								while(true) {
									try {
										System.out.println("Enter passenger pnr: ");
										removePnr=scanner.nextBigInteger();
										bookingService.validatePnr(modifyBooking, removePnr);
										break;
									}catch(FRSException exception) {
										System.err.println(exception);
										continue;
									}
								}
								for(Passenger passenger: modifyPassengerList) {
									if(passenger.getPnrNumber()==removePnr) {
										modifyPassengerList.remove(passenger);
										break;
									}
								}
							}
							modifyBooking.setPassengerList(modifyPassengerList);
							bookingService.modifyBooking(modifyBooking, removePassengerCount);
							break;
						case 4:
							BigInteger bookingDeleteId;
							while(true) {
								try {
									System.out.println("Enter Booking Id: ");
									bookingDeleteId=scanner.nextBigInteger();
									bookingService.validateBookingWithId(bookingDeleteId);
									break;
								}catch(FRSException exception) {
									System.err.println(exception.getMessage());
									continue;
								}
							}
							Booking removeBooking=bookingService.viewBooking(bookingDeleteId).get(0);
							bookingService.deleteBooking(removeBooking.getBookingId());
							break;
					}
					break;
				case 6:
					BigInteger adminActionId;
					while(true) {
						try {
							System.out.print("Enter Admin Id: ");
							adminActionId=scanner.nextBigInteger();
							userService.validateAdminWithId(adminActionId);
							break;
						}catch(FRSException exception) {
							System.err.println(exception.getMessage());
							continue;
						}
					}
					System.out.println("Enter 1 to View User List.");
					System.out.println("Enter 2 for Flight Management.");
					System.out.println("Enter 3 for Flight Schedule Management.");
					int adminChoice=scanner.nextInt();
					switch(adminChoice) {
						case 1:
							List<User> userList=userService.viewUser();
							for(User printUser: userList) {
								System.out.println("UserName: "+printUser.getUserName());
								System.out.println("UserId: "+printUser.getUserId());
								System.out.println("Email: "+printUser.getEmail());
								System.out.println("Phone: "+printUser.getUserPhone());
								System.out.println("UserType: "+printUser.getUserType());
								System.out.println("--------------");
							}System.out.println("--------------");
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
									System.out.print("Enter Flight Number: ");
									BigInteger flightNumber=scanner.nextBigInteger();
									System.out.print("Enter Carrier Name: ");
									String carrierName=scanner.next();
									System.out.print("Enter Flight Model: ");
									String flightModel=scanner.next();
									System.out.print("Enter the Flight Capacity: ");
									Integer seatCapacity=scanner.nextInt();
									Flight flight=new Flight(flightNumber, flightModel, carrierName, seatCapacity);
									flightService.addFlight(flight);
									break;
								case 2:
									List<Flight> flightList=flightService.viewFlight();
									for(Flight viewFlight: flightList) {
										System.out.println("Flight Number: "+viewFlight.getFlightNumber());
										System.out.println("Carrier Name: "+viewFlight.getCarrierName());
										System.out.println("Flight Model: "+viewFlight.getFlightModel());
										System.out.println("Seat Capacity: "+viewFlight.getSeatCapacity());
										System.out.println("--------------");
									}System.out.println("--------------");
									break;
								case 3:
									BigInteger flightId;
									while(true) {
										try {
											System.out.print("Enter Flight Number: ");
											flightId=scanner.nextBigInteger();
											flightService.validateFlightWithId(flightId);
											break;
										}catch(FRSException exception) {
											System.err.println(exception.getMessage());
											continue;
										}
									}
									System.out.println("Flight Number: "+flightService.viewFlight(flightId).getFlightNumber());
									System.out.println("Carrier Name: "+flightService.viewFlight(flightId).getCarrierName());
									System.out.println("Flight Model: "+flightService.viewFlight(flightId).getFlightModel());
									System.out.println("Seat Capacity: "+flightService.viewFlight(flightId).getSeatCapacity());
									break;
								case 4:
									BigInteger modifyFlightNumber;
									while(true) {
										try {
											System.out.print("Enter Flight Number: ");
											modifyFlightNumber=scanner.nextBigInteger();
											flightService.validateFlightWithId(modifyFlightNumber);
											break;
										}catch(FRSException exception) {
											System.err.println(exception.getMessage());
											continue;
										}
									}
									System.out.print("Enter Carrier Name: ");
									String modifyCarrierName=scanner.next();
									System.out.print("Enter Flight Model: ");
									String modifyFlightModel=scanner.next();
									System.out.print("Enter Seat Capacity: ");
									Integer modifySeatCapacity=scanner.nextInt();
									Flight modifyFlight=new Flight(modifyFlightNumber, modifyFlightModel, modifyCarrierName, modifySeatCapacity);
									flightService.addFlight(modifyFlight);
									break;
								case 5:
									BigInteger deleteFlightNumber;
									while(true) {
										try {
											System.out.print("Enter Flight Number: ");
											deleteFlightNumber=scanner.nextBigInteger();
											flightService.validateFlightWithId(deleteFlightNumber);
											break;
										}catch(FRSException exception) {
											System.err.println(exception.getMessage());
											continue;
										}
									}
									flightService.deleteFlight(deleteFlightNumber);
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
									System.out.print("Enter Flight Number: ");
									BigInteger scheduleFlightId=scanner.nextBigInteger();
									System.out.println("Enter Available Seats: ");
									Integer availableSeats=scanner.nextInt();
									System.out.println("Enter Source Airport Code: ");
									String sourceAirportCode=scanner.next();
									Airport sourceAirport=airportService.viewAirport(sourceAirportCode);
									System.out.println("Enter Destination Airport Code: ");
									String destinationAirportCode=scanner.next();
									Airport destinationAirport=airportService.viewAirport(destinationAirportCode);
									DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
									System.out.print("Enter Departure Time (dd-MM-yyyy HH:mm:ss) :");
									String departureTimeString=scanner.nextLine();
									LocalDateTime departureDateTime=LocalDateTime.parse(departureTimeString, dateTimeFormatter);
									System.out.print("Enter Arrival Time (dd-MM-yyyy HH:mm:ss) :");
									String arrivalTimeString=scanner.nextLine();
									LocalDateTime arrivalDateTime=LocalDateTime.parse(arrivalTimeString, dateTimeFormatter);
									Schedule schedule=new Schedule(sourceAirport, destinationAirport, departureDateTime, arrivalDateTime);
									System.out.print("Enter Ticket Cost: ");
									Double ticketCost=scanner.nextDouble();
									ScheduleFlight scheduleFlight=new ScheduleFlight(flightService.viewFlight(scheduleFlightId), availableSeats, schedule, ticketCost);
									scheduleFlightService.addScheduleFlight(scheduleFlight);
									break;
								case 2:
									List<ScheduleFlight> scheduleFlightList=scheduleFlightService.viewScheduleFlight();
									for(ScheduleFlight scheduleFlightView: scheduleFlightList) {
										System.out.println("Carrier Name: "+scheduleFlightView.getFlight().getCarrierName());
										System.out.println("Flight Number: "+scheduleFlightView.getFlight().getFlightNumber());
										System.out.println("Flight Model: "+scheduleFlightView.getFlight().getFlightModel());
										System.out.println("Seat Capacity: "+scheduleFlightView.getFlight().getSeatCapacity());
										System.out.println("Source: "+scheduleFlightView.getSchedule().getSourceAirport());
										System.out.println("Departure Time: "+scheduleFlightView.getSchedule().getDepartureDateTime());
										System.out.println("Destination: "+scheduleFlightView.getSchedule().getDestinationAirport());
										System.out.println("Arrival Time: "+scheduleFlightView.getSchedule().getArrivalDateTime());
										System.out.println("Ticket Cost: "+scheduleFlightView.getTicketCost());
										System.out.println("--------------");
									}System.out.println("--------------");
									break;
								case 3:
									BigInteger searchScheduleFlightId;
									while(true) {
										try {
											System.out.print("Enter Flight Number: ");
											searchScheduleFlightId=scanner.nextBigInteger();
											scheduleFlightService.validateScheduleFlightWithId(searchScheduleFlightId);
											break;
										}catch(FRSException exception) {
											System.err.println(exception.getMessage());
											continue;
										}
									}
									System.out.println(scheduleFlightService.viewScheduleFlights(searchScheduleFlightId));
									System.out.println("Carrier Name: "+scheduleFlightService.viewScheduleFlights(searchScheduleFlightId).getFlight().getCarrierName());
									System.out.println("Flight Number: "+scheduleFlightService.viewScheduleFlights(searchScheduleFlightId).getFlight().getFlightNumber());
									System.out.println("Flight Model: "+scheduleFlightService.viewScheduleFlights(searchScheduleFlightId).getFlight().getFlightModel());
									System.out.println("Seat Capacity: "+scheduleFlightService.viewScheduleFlights(searchScheduleFlightId).getFlight().getSeatCapacity());
									System.out.println("Source: "+scheduleFlightService.viewScheduleFlights(searchScheduleFlightId).getSchedule().getSourceAirport());
									System.out.println("Departure Time: "+scheduleFlightService.viewScheduleFlights(searchScheduleFlightId).getSchedule().getDepartureDateTime());
									System.out.println("Destination: "+scheduleFlightService.viewScheduleFlights(searchScheduleFlightId).getSchedule().getDestinationAirport());
									System.out.println("Arrival Time: "+scheduleFlightService.viewScheduleFlights(searchScheduleFlightId).getSchedule().getArrivalDateTime());
									System.out.println("Ticket Cost: "+scheduleFlightService.viewScheduleFlights(searchScheduleFlightId).getTicketCost());
									break;
								case 4:
									BigInteger modifyScheduleFlightId;
									while(true) {
										try {
											System.out.print("Enter Flight Number: ");
											modifyScheduleFlightId=scanner.nextBigInteger();
											scheduleFlightService.validateScheduleFlightWithId(modifyScheduleFlightId);
											break;
										}catch(FRSException exception) {
											System.err.println(exception.getMessage());
											continue;
										}
									}
									System.out.println("Enter Available Seats: ");
									Integer modifyAvailableSeats=scanner.nextInt();
									System.out.println("Enter Source Airport Code: ");
									String modifySourceAirportCode=scanner.next();
									Airport modifySourceAirport=airportService.viewAirport(modifySourceAirportCode);
									System.out.println("Enter Destination Airport Code: ");
									String modifyDestinationAirportCode=scanner.next();
									Airport modifyDestinationAirport=airportService.viewAirport(modifyDestinationAirportCode);
									DateTimeFormatter modifyDateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
									System.out.print("Enter Departure Time (dd-MM-yyyy HH:mm:ss) :");
									String modifyDepartureTimeString=scanner.nextLine();
									LocalDateTime modifyDepartureDateTime=LocalDateTime.parse(modifyDepartureTimeString, modifyDateTimeFormatter);
									System.out.print("Enter Arrival Time (dd-MM-yyyy HH:mm:ss) :");
									String modifyArrivalTimeString=scanner.nextLine();
									LocalDateTime modifyArrivalDateTime=LocalDateTime.parse(modifyArrivalTimeString, modifyDateTimeFormatter);
									Schedule modifySchedule=new Schedule(modifySourceAirport, modifyDestinationAirport, modifyDepartureDateTime, modifyArrivalDateTime);
									System.out.print("Enter Ticket Cost: ");
									Double modifyTicketCost=scanner.nextDouble();
									ScheduleFlight modifyScheduleFlight=new ScheduleFlight(flightService.viewFlight(modifyScheduleFlightId), modifyAvailableSeats, modifySchedule, modifyTicketCost);
									scheduleFlightService.addScheduleFlight(modifyScheduleFlight);
									break;
								case 5:
									BigInteger deleteScheduleFlightId;
									while(true) {
										try {
											System.out.print("Enter Flight Number: ");
											deleteScheduleFlightId=scanner.nextBigInteger();
											scheduleFlightService.validateScheduleFlightWithId(deleteScheduleFlightId);
											break;
										}catch(FRSException exception) {
											System.err.println(exception.getMessage());
											continue;
										}
									}
									scheduleFlightService.deleteScheduleFlight(deleteScheduleFlightId);
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
