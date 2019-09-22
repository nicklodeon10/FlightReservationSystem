package com.cg.frs.ui;

import java.math.BigInteger;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
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

		int userTypeChoice = 1;
		Scanner scanner = new Scanner(System.in);
		AirportService airportService = new AirportServiceImpl();
		BookingService bookingService = new BookingServiceImpl();
		FlightService flightService = new FlightServiceImpl();
		ScheduleFlightService scheduleFlightService = new ScheduleFlightServiceImpl();
		UserService userService = new UserServiceImpl();
		do {
			System.out.println("Enter 1 to Sign Up.");
			System.out.println("Enter 2 to View Profile Details.");
			System.out.println("Enter 3 to Edit Profile Details.");
			System.out.println("Enter 4 to Delete User Profile.");
			System.out.println("Enter 5 for User Actions.");
			System.out.println("Enter 6 for Admin Actions.");
			System.out.println("Enter 0 to Exit.");
			while (true) {
				try {
					userTypeChoice = scanner.nextInt();
					break;
				} catch (InputMismatchException exception) {
					scanner.nextLine();
					System.err.println(exception);
					continue;
				}
			}
			switch (userTypeChoice) {
			case 1: {
				User user;
				BigInteger userPhone;
				String userEmail;
				String userName;
				String userPassword;
				// userId = BigDecimal.valueOf(Math.random() * 100000).toBigInteger();
				System.out.println("Enter User Name: ");
				userName = scanner.next();
				System.out.println("Enter User Password: ");
				userPassword = scanner.next();
				while (true) {
					try {
						System.out.println("Enter User Phone Number: ");
						userPhone = scanner.nextBigInteger();
						userService.validatePhoneNumber(userPhone);
						break;
					} catch (InputMismatchException exception) {
						scanner.nextLine();
						System.err.println(exception);
						continue;
					} catch (FRSException exception) {
						System.err.println(exception.getMessage());
						continue;
					}
				}
				while (true) {
					try {
						System.out.println("Enter User Email: ");
						userEmail = scanner.next();
						userService.validateEmail(userEmail);
						break;
					} catch (FRSException exception) {
						System.err.println(exception.getMessage());
						continue;
					}
				}
				user = new User();
				user.setUserType("customer");
				user.setUserName(userName);
				user.setUserPassword(userPassword);
				user.setUserPhone(userPhone);
				user.setEmail(userEmail);
				user = userService.addUser(user);
				System.out.println("User Created with UserId: " + user.getUserId());
				break;
			}
			case 2: {
				BigInteger showUserId;
				User user;
				while (true) {
					try {
						System.out.println("Enter User Id: ");
						showUserId = scanner.nextBigInteger();
						userService.validateUserWithId(showUserId);
						user=userService.viewUser(showUserId);
						break;
					} catch (FRSException exception) {
						System.err.println(exception.getMessage());
						continue;
					} catch (InputMismatchException exception) {
						scanner.nextLine();
						System.err.println(exception);
						continue;
					}
				}
				System.out.println("Profile Details: ");
				System.out.println("----------------------------");
				System.out.println("UserName: " + user.getUserName());
				System.out.println("UserId: " + user.getUserId());
				System.out.println("Email: " + user.getEmail());
				System.out.println("Phone: " + user.getUserPhone());
				System.out.println("UserType: " + user.getUserType());
				System.out.println("----------------------------");
				break;
			}
			case 3: {
				BigInteger editUserId;
				User editUser;
				String userName;
				BigInteger userPhone;
				String userEmail;
				String userPassword;
				while (true) {
					try {
						System.out.println("Enter User Id: ");
						editUserId = scanner.nextBigInteger();
						userService.validateUserWithId(editUserId);
						break;
					} catch (FRSException exception) {
						System.err.println(exception.getMessage());
						continue;
					} catch (InputMismatchException exception) {
						scanner.nextLine();
						System.err.println(exception);
						continue;
					}
				}
				System.out.println("Enter User Name:");
				userName = scanner.next();
				System.out.println("Enter User Password: ");
				userPassword = scanner.next();
				while (true) {
					try {
						System.out.println("Enter User Phone Number: ");
						userPhone = scanner.nextBigInteger();
						userService.validatePhoneNumber(userPhone);
						break;
					} catch (InputMismatchException exception) {
						scanner.nextLine();
						System.err.println(exception);
						continue;
					} catch (FRSException exception) {
						System.err.println(exception.getMessage());
						continue;
					}
				}
				while (true) {
					try {
						System.out.println("Enter User Email: ");
						userEmail = scanner.next();
						userService.validateEmail(userEmail);
						break;
					} catch (FRSException exception) {
						System.err.println(exception.getMessage());
						continue;
					}
				}
				editUser = new User(userService.viewUser(editUserId).getUserType(), editUserId, userName, userPassword,
						userPhone, userEmail);
				userService.updateUser(editUser);
				break;
			}
			case 4: {
				BigInteger deleteUserId;
				while (true) {
					try {
						System.out.println("Enter User Id: ");
						deleteUserId = scanner.nextBigInteger();
						userService.validateUserWithId(deleteUserId);
						break;
					} catch (FRSException exception) {
						System.err.println(exception.getMessage());
						continue;
					} catch (InputMismatchException exception) {
						scanner.nextLine();
						System.err.println(exception);
						continue;
					}
				}
				userService.deleteUser(deleteUserId);
				break;
			}
			case 5: {
				BOOKING: do {
					int userChoice;
					System.out.println("Enter 1 to Book a Flight.");
					System.out.println("Enter 2 to View Previous Flight Bookings.");
					System.out.println("Enter 3 to Modify a Flight Booking.");
					System.out.println("Enter 4 to Cancel a Flight Booking.");
					System.out.println("Enter 0 to Go Back to Previous Menu.");
					while (true) {
						try {
							userChoice = scanner.nextInt();
							break;
						} catch (InputMismatchException exception) {
							scanner.nextLine();
							System.err.println(exception);
							continue;
						}
					}
					switch (userChoice) {
					case 1: {
						String bookingSourceAirportCode;
						String bookingDestinationAirportCode;
						Airport sourceAirport;
						Airport destinationAirport;
						String bookingDateString;
						LocalDate bookingDate;
						List<Airport> airportList = airportService.viewAirport();
						System.out.println("Choose Airports: ");
						System.out.println("----------------------------");
						for (Airport airport : airportList) {
							System.out.print("Code: " + airport.getAirportCode());
							System.out.print(", Name: " + airport.getAirportName());
							System.out.println(", Location: " + airport.getAirportLocation());
							System.out.println("----------------------------");
						}
						System.out.println("----------------------------");
						while (true) {
							try {
								System.out.println("Enter Source Airport Code: ");
								bookingSourceAirportCode = scanner.next();
								airportService.validateAirportWithCode(bookingSourceAirportCode);
								sourceAirport = airportService.viewAirport(bookingSourceAirportCode);
								break;
							} catch (FRSException exception) {
								System.err.println(exception.getMessage());
								continue;
							}
						}
						while (true) {
							try {
								System.out.println("Enter Destination Airport Code: ");
								bookingDestinationAirportCode = scanner.next();
								airportService.validateAirportWithCode(bookingDestinationAirportCode);
								destinationAirport = airportService.viewAirport(bookingDestinationAirportCode);
								airportService.compareAirport(sourceAirport, destinationAirport);
								break;
							} catch (FRSException exception) {
								System.err.println(exception.getMessage());
								continue;
							}
						}
						while (true) {
							try {
								System.out.println("Enter the Date of Journey (YYYY-MM-DD):");
								bookingDateString = scanner.next();
								bookingDate = LocalDate.parse(bookingDateString);
								break;
							} catch (DateTimeException exception) {
								System.err.println(exception);
								continue;
							}
						}
						System.out.println("Search Results: ");
						System.out.println("----------------------------");
						List<ScheduleFlight> searchScheduledFlights = scheduleFlightService
								.viewScheduleFlights(sourceAirport, destinationAirport, bookingDate);
						if (searchScheduledFlights.size() != 0) {
							BigInteger bookingUserId;
							BigInteger bookingFlightNumber;
							// BigInteger bookingId = BigDecimal.valueOf(Math.random() *
							// 1000000000).toBigInteger();
							List<Passenger> bookingPassengerList = new ArrayList<Passenger>();
							Integer noOfPassengers;
							for (ScheduleFlight scheduleFlight : searchScheduledFlights) {
								System.out.println("Flight Carrier: " + scheduleFlight.getFlight().getCarrierName());
								System.out.println("Flight Model: " + scheduleFlight.getFlight().getFlightModel());
								System.out.println("Flight Number: " + scheduleFlight.getFlight().getFlightNumber());
								System.out.println("Source Airport: "
										+ scheduleFlight.getSchedule().getSourceAirport().getAirportName());
								System.out.println("Destination Airport: "
										+ scheduleFlight.getSchedule().getDestinationAirport().getAirportName());
								System.out.println(
										"Departure Time: " + scheduleFlight.getSchedule().getDepartureDateTime());
								System.out
										.println("Arrival Time: " + scheduleFlight.getSchedule().getArrivalDateTime());
								System.out.println("Available Seats: " + scheduleFlight.getAvailableSeats());
								System.out.println("----------------------------");
							}
							System.out.println("----------------------------");
							while (true) {
								try {
									System.out.println("Enter User Id: ");
									bookingUserId = scanner.nextBigInteger();
									userService.validateCustomerWithId(bookingUserId);
									break;
								} catch (FRSException exception) {
									System.err.println(exception.getMessage());
									continue;
								} catch (InputMismatchException exception) {
									scanner.nextLine();
									System.err.println(exception);
									continue;
								}
							}
							while (true) {
								try {
									System.out.println("Enter Chosen Flight Number: ");
									bookingFlightNumber = scanner.nextBigInteger();
									scheduleFlightService.validateScheduleFlightWithId(bookingFlightNumber);
									break;
								} catch (FRSException exception) {
									System.err.println(exception);
									continue;
								} catch (InputMismatchException exception) {
									scanner.nextLine();
									System.err.println(exception);
									continue;
								}
							}
							while (true) {
								try {
									System.out.println("Enter No. of Passengers:");
									noOfPassengers = scanner.nextInt();
									bookingService.validatePassengerCount(
											scheduleFlightService.viewScheduleFlights(bookingFlightNumber),
											noOfPassengers);
									break;
								} catch (FRSException exception) {
									System.err.println(exception.getMessage());
									continue;
								} catch (InputMismatchException exception) {
									scanner.nextLine();
									System.err.println(exception);
									continue;
								}
							}
							for (int i = 0; i < noOfPassengers; i++) {
								Passenger passenger;
								String passengerName;
								Integer passengerAge;
								BigInteger passengerUIN;
								scanner.nextLine();
								while (true) {
									try {
										System.out.println("Enter Passenger Name: ");
										passengerName = scanner.nextLine();
										bookingService.validatePassengerName(passengerName);
										break;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								while (true) {
									try {
										System.out.println("Enter Passenger Age: ");
										passengerAge = scanner.nextInt();
										break;
									} catch (InputMismatchException exception) {
										System.out.println(exception);
										continue;
									}
								}
								while (true) {
									try {
										System.out.println("Enter 12-digit Passenger UIN: ");
										passengerUIN = scanner.nextBigInteger();
										bookingService.validatePassengerUIN(passengerUIN);
										break;
									} catch (InputMismatchException exception) {
										System.out.println(exception);
										continue;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								passenger = new Passenger();
								passenger.setPassengerName(passengerName);
								passenger.setPassengerAge(passengerAge);
								passenger.setPassengerUIN(passengerUIN);
								bookingPassengerList.add(passenger);
							}
							Booking booking = new Booking();
							booking.setUserId(bookingUserId);
							booking.setPassengerList(bookingPassengerList);
							booking.setFlight(scheduleFlightService.viewScheduleFlights(bookingFlightNumber));
							booking.setTicketCost(
									scheduleFlightService.viewScheduleFlights(bookingFlightNumber).getTicketCost()
											* noOfPassengers);
							booking.setNoOfPassengers(noOfPassengers);
							booking = bookingService.addBooking(booking);
							System.out.println("Booking Successful with Booking Id: " + booking.getBookingId());
						} else {
							System.out.println("No Flights Found.");
							System.out.println("----------------------------");
						}
						break;
					}
					case 2: {
						BigInteger bookingSearchId;
						List<Booking> userBookingsList;
						while (true) {
							try {
								System.out.println("Enter Search Id(userId or bookingId): ");
								bookingSearchId = scanner.nextBigInteger();
								bookingService.validateBookingWithId(bookingSearchId);
								userBookingsList = bookingService.viewBooking(bookingSearchId);
								break;
							} catch (FRSException exception) {
								System.err.println(exception.getMessage());
								continue;
							} catch (InputMismatchException exception) {
								scanner.nextLine();
								System.err.println(exception);
								continue;
							}
						}
						System.out.println("Booking Details: ");
						System.out.println("----------------------------");
						for (Booking userBooking : userBookingsList) {
							System.out.println("Booking Id: " + userBooking.getBookingId());
							System.out.println("Booking Date: " + userBooking.getBookingDate());
							System.out.println("Booked by: " + userBooking.getUserId());
							System.out.println(
									"Departure Time: " + userBooking.getFlight().getSchedule().getDepartureDateTime());
							System.out.println(
									"Source Airport: " + userBooking.getFlight().getSchedule().getSourceAirport());
							System.out.println(
									"Arrival Time: " + userBooking.getFlight().getSchedule().getArrivalDateTime());
							System.out.println("Destination Aiport: "
									+ userBooking.getFlight().getSchedule().getDestinationAirport());
							System.out.println("Ticket Cost: " + userBooking.getTicketCost());
							System.out.println("Passengers List:");
							System.out.println("----------------------------");
							List<Passenger> bookingPassengerList1 = userBooking.getPassengerList();
							for (Passenger passenger : bookingPassengerList1) {
								System.out.println("Name: " + passenger.getPassengerName());
								System.out.println("Age: " + passenger.getPassengerAge());
								System.out.println("PNR: " + passenger.getPnrNumber());
								System.out.println("UIN: " + passenger.getPassengerUIN());
								System.out.println("----------------------------");
							}
							System.out.println("----------------------------");
						}
						break;
					}
					case 3: {
						BigInteger bookingEditId;
						Booking modifyBooking;
						int removePassengerCount;
						while (true) {
							try {
								System.out.println("Enter Booking Id: ");
								bookingEditId = scanner.nextBigInteger();
								bookingService.validateBookingWithId(bookingEditId);
								modifyBooking = bookingService.viewBooking(bookingEditId).get(0);
								break;
							} catch (FRSException exception) {
								System.err.println(exception.getMessage());
								continue;
							}
						}
						List<Passenger> modifyPassengerList = modifyBooking.getPassengerList();
						List<Passenger> removePassengerList = new ArrayList<Passenger>();
						while (true) {
							try {
								System.out.println("Enter the no of passengers to remove: ");
								removePassengerCount = scanner.nextInt();
								break;
							} catch (InputMismatchException exception) {
								scanner.nextLine();
								System.err.println(exception);
								continue;
							}
						}
						for (int i = 0; i < removePassengerCount; i++) {
							BigInteger removePnr;
							while (true) {
								try {
									System.out.println("Enter passenger pnr: ");
									removePnr = scanner.nextBigInteger();
									bookingService.validatePnr(modifyBooking, removePnr);
									break;
								} catch (FRSException exception) {
									System.err.println(exception);
									continue;
								} catch (InputMismatchException exception) {
									scanner.nextLine();
									System.err.println(exception);
									continue;
								}
							}
							for (Passenger passenger : modifyPassengerList) {
								if (passenger.getPnrNumber().equals(removePnr)) {
									removePassengerList.add(passenger);
									break;
								}
							}
						}
						modifyBooking.setPassengerList(removePassengerList);
						bookingService.modifyBooking(modifyBooking, removePassengerCount);
						break;
					}
					case 4: {
						BigInteger bookingDeleteId;
						Booking removeBooking;
						while (true) {
							try {
								System.out.println("Enter Booking Id: ");
								bookingDeleteId = scanner.nextBigInteger();
								bookingService.validateBookingWithId(bookingDeleteId);
								removeBooking = bookingService.viewBooking(bookingDeleteId).get(0);
								bookingService.deleteBooking(removeBooking.getBookingId());
								break;
							} catch (FRSException exception) {
								System.err.println(exception.getMessage());
								continue;
							} catch (InputMismatchException exception) {
								scanner.nextLine();
								System.err.println(exception);
								continue;
							}
						}
						break;
					}
					case 0:
						break BOOKING;
					}
				} while (true);
				break;
			}
			case 6: {
				BigInteger adminActionId;
				while (true) {
					try {
						System.out.println("Enter Admin Id: ");
						adminActionId = scanner.nextBigInteger();
						userService.validateAdminWithId(adminActionId);
						break;
					} catch (FRSException exception) {
						System.err.println(exception.getMessage());
						continue;
					} catch (InputMismatchException exception) {
						scanner.nextLine();
						System.err.println(exception);
						continue;
					}
				}
				MANAGEMENT: do {
					int adminChoice;
					System.out.println("Enter 1 to View a List of Users.");
					System.out.println("Enter 2 to View Flight Management Options.");
					System.out.println("Enter 3 to View Flight Scheduling Options.");
					System.out.println("Enter 0 to Go Back to the Previous Menu.");
					while (true) {
						try {
							adminChoice = scanner.nextInt();
							break;
						} catch (InputMismatchException exception) {
							scanner.nextLine();
							System.err.println(exception);
							continue;
						}
					}
					switch (adminChoice) {
					case 1:
						List<User> userList = userService.viewUser();
						System.out.println("User List: ");
						System.out.println("----------------------------");
						for (User printUser : userList) {
							System.out.println("UserName: " + printUser.getUserName());
							System.out.println("UserId: " + printUser.getUserId());
							System.out.println("Email: " + printUser.getEmail());
							System.out.println("Phone: " + printUser.getUserPhone());
							System.out.println("UserType: " + printUser.getUserType());
							System.out.println("----------------------------");
						}
						System.out.println("----------------------------");
						break;
					case 2:
						FLIGHTMANAGEMENT: do {
							int adminFlightManageChoice;
							System.out.println("Enter 1 to Add a Flight.");
							System.out.println("Enter 2 to Show all Flights.");
							System.out.println("Enter 3 to Search a Flight.");
							System.out.println("Enter 4 to Modify a Flight.");
							System.out.println("Enter 5 to Remove a Flight.");
							System.out.println("Enter 0 to Go Back to Previous Menu.");
							while (true) {
								try {
									adminFlightManageChoice = scanner.nextInt();
									break;
								} catch (InputMismatchException exception) {
									scanner.nextLine();
									System.err.println(exception);
									continue;
								}
							}
							switch (adminFlightManageChoice) {
							case 1: {
								// BigInteger flightNumber;
								String carrierName;
								String flightModel;
								Integer seatCapacity;
								Flight flight;
								System.out.println("Enter Carrier Name: ");
								carrierName = scanner.next();
								System.out.println("Enter Flight Model: ");
								flightModel = scanner.next();
								while (true) {
									try {
										System.out.println("Enter the Flight Capacity: ");
										seatCapacity = scanner.nextInt();
										break;
									} catch (InputMismatchException exception) {
										scanner.nextLine();
										System.err.println(exception);
										continue;
									}
								}
								flight = new Flight();
								flight.setFlightModel(flightModel);
								flight.setCarrierName(carrierName);
								flight.setSeatCapacity(seatCapacity);
								flight = flightService.addFlight(flight);
								System.out.println("Flight Added with Flight Number: " + flight.getFlightNumber());
								break;
							}
							case 2: {
								List<Flight> flightList = flightService.viewFlight();
								System.out.println("Available Flights: ");
								System.out.println("----------------------------");
								for (Flight viewFlight : flightList) {
									System.out.println("Flight Number: " + viewFlight.getFlightNumber());
									System.out.println("Carrier Name: " + viewFlight.getCarrierName());
									System.out.println("Flight Model: " + viewFlight.getFlightModel());
									System.out.println("Seat Capacity: " + viewFlight.getSeatCapacity());
									System.out.println("----------------------------");
								}
								System.out.println("----------------------------");
								break;
							}
							case 3:
								BigInteger flightId;
								while (true) {
									try {
										System.out.println("Enter Flight Number: ");
										flightId = scanner.nextBigInteger();
										flightService.validateFlightWithId(flightId);
										System.out.println("Search Results:");
										System.out.println("----------------------------");
										System.out.println("Flight Number: "
												+ flightService.viewFlight(flightId).getFlightNumber());
										System.out.println(
												"Carrier Name: " + flightService.viewFlight(flightId).getCarrierName());
										System.out.println(
												"Flight Model: " + flightService.viewFlight(flightId).getFlightModel());
										System.out.println("Seat Capacity: "
												+ flightService.viewFlight(flightId).getSeatCapacity());
										System.out.println("----------------------------");
										break;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								break;
							case 4:
								BigInteger modifyFlightNumber;
								Integer modifySeatCapacity;
								while (true) {
									try {
										System.out.println("Enter Flight Number: ");
										modifyFlightNumber = scanner.nextBigInteger();
										flightService.validateFlightWithId(modifyFlightNumber);
										break;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								System.out.println("Enter Carrier Name: ");
								String modifyCarrierName = scanner.next();
								System.out.println("Enter Flight Model: ");
								String modifyFlightModel = scanner.next();
								while (true) {
									try {
										System.out.println("Enter Seat Capacity: ");
										modifySeatCapacity = scanner.nextInt();
										break;
									} catch (InputMismatchException exception) {
										scanner.nextLine();
										System.err.println(exception);
										continue;
									}
								}
								Flight modifyFlight = new Flight(modifyFlightNumber, modifyFlightModel,
										modifyCarrierName, modifySeatCapacity);
								flightService.addFlight(modifyFlight);
								break;
							case 5:
								BigInteger deleteFlightNumber;
								while (true) {
									try {
										System.out.println("Enter Flight Number: ");
										deleteFlightNumber = scanner.nextBigInteger();
										flightService.validateFlightWithId(deleteFlightNumber);
										break;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								flightService.deleteFlight(deleteFlightNumber);
								break;
							case 0:
								break FLIGHTMANAGEMENT;
							}
						} while (true);
						break;
					case 3:
						SCHEDULEFLIGHTMANAGEMENT: do {
							BigInteger scheduleFlightId;
							String sourceAirportCode;
							Airport sourceAirport;
							String destinationAirportCode;
							Airport destinationAirport;
							DateTimeFormatter dateTimeFormatter;
							String departureTimeString;
							LocalDateTime departureDateTime;
							String arrivalTimeString;
							LocalDateTime arrivalDateTime;
							Schedule schedule;
							Double ticketCost;
							ScheduleFlight scheduleFlight;
							int adminScheduleChoice;
							System.out.println("Enter 1 to Schedule a Flight.");
							System.out.println("Enter 2 to Show all Scheduled Flights.");
							System.out.println("Enter 3 to Search a Scheduled Flight.");
							System.out.println("Enter 4 to Modify a Scheduled Flight.");
							System.out.println("Enter 5 to Remove a Scheduled Flight.");
							System.out.println("Enter 0 to Go Back to Previous Menu.");
							while (true) {
								try {
									adminScheduleChoice = scanner.nextInt();
									break;
								} catch (InputMismatchException exception) {
									scanner.nextLine();
									System.err.println(exception);
									continue;
								}
							}
							switch (adminScheduleChoice) {
							case 1:
								Integer availableSeats;
								while (true) {
									try {
										System.out.println("Enter Flight Number: ");
										scheduleFlightId = scanner.nextBigInteger();
										flightService.validateFlightWithId(scheduleFlightId);
										availableSeats = flightService.viewFlight(scheduleFlightId).getSeatCapacity();
										break;
									} catch (InputMismatchException exception) {
										scanner.nextLine();
										System.err.println(exception);
										continue;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								while (true) {
									try {
										System.out.println("Enter Source Airport Code: ");
										sourceAirportCode = scanner.next();
										sourceAirport = airportService.viewAirport(sourceAirportCode);
										airportService.validateAirportWithCode(sourceAirportCode);
										break;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								while (true) {
									try {
										System.out.println("Enter Destination Airport Code: ");
										destinationAirportCode = scanner.next();
										destinationAirport = airportService.viewAirport(destinationAirportCode);
										airportService.validateAirportWithCode(destinationAirportCode);
										break;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
									}
								}
								dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
								scanner.nextLine();
								while (true) {
									try {
										System.out.println("Enter Departure Time (dd-MM-yyyy HH:mm:ss) :");
										departureTimeString = scanner.nextLine();
										departureDateTime = LocalDateTime.parse(departureTimeString, dateTimeFormatter);
										break;
									} catch (DateTimeException exception) {
										System.err.println(exception.getMessage());
									}
								}
								while (true) {
									try {
										System.out.println("Enter Arrival Time (dd-MM-yyyy HH:mm:ss) :");
										arrivalTimeString = scanner.nextLine();
										arrivalDateTime = LocalDateTime.parse(arrivalTimeString, dateTimeFormatter);
										break;
									} catch (DateTimeException exception) {
										System.err.println(exception.getMessage());
									}
								}
								schedule = new Schedule(sourceAirport, destinationAirport, departureDateTime,
										arrivalDateTime);
								while (true) {
									try {
										System.out.println("Enter Ticket Cost: ");
										ticketCost = scanner.nextDouble();
										break;
									} catch (InputMismatchException exception) {
										scanner.nextLine();
										System.err.println(exception);
										continue;
									}
								}
								try {
									scheduleFlight = new ScheduleFlight(flightService.viewFlight(scheduleFlightId),
											availableSeats, schedule, ticketCost);
									scheduleFlightService.addScheduleFlight(scheduleFlight);
									System.out.println("Flight Scheduled.\n");
								} catch (FRSException exception) {
									System.out.println(exception.getMessage());
								}
								break;
							case 2:
								List<ScheduleFlight> scheduleFlightList = scheduleFlightService.viewScheduleFlight();
								System.out.println("Available Scheduled Flights: ");
								System.out.println("----------------------------");
								for (ScheduleFlight scheduleFlightView : scheduleFlightList) {
									System.out.println(
											"Carrier Name: " + scheduleFlightView.getFlight().getCarrierName());
									System.out.println(
											"Flight Number: " + scheduleFlightView.getFlight().getFlightNumber());
									System.out.println(
											"Flight Model: " + scheduleFlightView.getFlight().getFlightModel());
									System.out.println(
											"Seat Capacity: " + scheduleFlightView.getFlight().getSeatCapacity());
									System.out
											.println("Source: " + scheduleFlightView.getSchedule().getSourceAirport());
									System.out.println("Departure Time: "
											+ scheduleFlightView.getSchedule().getDepartureDateTime());
									System.out.println(
											"Destination: " + scheduleFlightView.getSchedule().getDestinationAirport());
									System.out.println(
											"Arrival Time: " + scheduleFlightView.getSchedule().getArrivalDateTime());
									System.out.println("Ticket Cost: " + scheduleFlightView.getTicketCost());
									System.out.println("----------------------------");
								}
								System.out.println("----------------------------");
								break;
							case 3:
								BigInteger searchScheduleFlightId;
								while (true) {
									try {
										System.out.println("Enter Flight Number: ");
										searchScheduleFlightId = scanner.nextBigInteger();
										scheduleFlightService.validateScheduleFlightWithId(searchScheduleFlightId);
										break;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								System.out.println("Search Result: ");
								System.out.println("----------------------------");
								System.out.println(scheduleFlightService.viewScheduleFlights(searchScheduleFlightId));
								System.out.println("Carrier Name: " + scheduleFlightService
										.viewScheduleFlights(searchScheduleFlightId).getFlight().getCarrierName());
								System.out.println("Flight Number: " + scheduleFlightService
										.viewScheduleFlights(searchScheduleFlightId).getFlight().getFlightNumber());
								System.out.println("Flight Model: " + scheduleFlightService
										.viewScheduleFlights(searchScheduleFlightId).getFlight().getFlightModel());
								System.out.println("Seat Capacity: " + scheduleFlightService
										.viewScheduleFlights(searchScheduleFlightId).getFlight().getSeatCapacity());
								System.out.println("Source: " + scheduleFlightService
										.viewScheduleFlights(searchScheduleFlightId).getSchedule().getSourceAirport());
								System.out.println("Departure Time: "
										+ scheduleFlightService.viewScheduleFlights(searchScheduleFlightId)
												.getSchedule().getDepartureDateTime());
								System.out.println("Destination: "
										+ scheduleFlightService.viewScheduleFlights(searchScheduleFlightId)
												.getSchedule().getDestinationAirport());
								System.out.println("Arrival Time: "
										+ scheduleFlightService.viewScheduleFlights(searchScheduleFlightId)
												.getSchedule().getArrivalDateTime());
								System.out.println("Ticket Cost: " + scheduleFlightService
										.viewScheduleFlights(searchScheduleFlightId).getTicketCost());
								System.out.println("----------------------------");
								break;
							case 4:
								BigInteger modifyScheduleFlightId;
								Integer modifyAvailableSeats;
								String modifySourceAirportCode;
								Airport modifySourceAirport;
								String modifyDestinationAirportCode;
								Airport modifyDestinationAirport;
								String modifyDepartureTimeString;
								LocalDateTime modifyDepartureDateTime;
								String modifyArrivalTimeString;
								LocalDateTime modifyArrivalDateTime;
								Schedule modifySchedule;
								Double modifyTicketCost;
								ScheduleFlight modifyScheduleFlight;
								while (true) {
									try {
										System.out.println("Enter Flight Number: ");
										modifyScheduleFlightId = scanner.nextBigInteger();
										scheduleFlightService.validateScheduleFlightWithId(modifyScheduleFlightId);
										break;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									} catch (InputMismatchException exception) {
										scanner.nextLine();
										System.err.println(exception);
										continue;
									}
								}
								while (true) {
									try {
										System.out.println("Enter Available Seats: ");
										modifyAvailableSeats = scanner.nextInt();
										break;
									} catch (InputMismatchException exception) {
										scanner.nextLine();
										System.err.println(exception);
										continue;
									}
								}
								while (true) {
									try {
										System.out.println("Enter Source Airport Code: ");
										modifySourceAirportCode = scanner.next();
										System.out.println("You have entered: "
												+ airportService.validateAirportWithCode(modifySourceAirportCode));
										modifySourceAirport = airportService.viewAirport(modifySourceAirportCode);
										break;
									} catch (InputMismatchException exception) {
										scanner.nextLine();
										System.err.println(exception);
										continue;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								while (true) {
									try {
										System.out.println("Enter Destination Airport Code: ");
										modifyDestinationAirportCode = scanner.next();
										System.out.println("You have entered: "
												+ airportService.validateAirportWithCode(modifyDestinationAirportCode));
										modifyDestinationAirport = airportService
												.viewAirport(modifyDestinationAirportCode);
										break;
									} catch (InputMismatchException exception) {
										System.out.println(exception);
										continue;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								DateTimeFormatter modifyDateTimeFormatter = DateTimeFormatter
										.ofPattern("dd-MM-yyyy HH:mm:ss");
								scanner.nextLine();
								while (true) {
									try {
										System.out.println("Enter Departure Time (dd-MM-yyyy HH:mm:ss) :");
										modifyDepartureTimeString = scanner.nextLine();
										modifyDepartureDateTime = LocalDateTime.parse(modifyDepartureTimeString,
												modifyDateTimeFormatter);
										break;
									} catch (DateTimeException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								while (true) {
									try {
										System.out.println("Enter Arrival Time (dd-MM-yyyy HH:mm:ss) :");
										modifyArrivalTimeString = scanner.nextLine();
										modifyArrivalDateTime = LocalDateTime.parse(modifyArrivalTimeString,
												modifyDateTimeFormatter);
										break;
									} catch (DateTimeException exception) {
										System.err.println(exception.getMessage());
										continue;
									}
								}
								modifySchedule = new Schedule(modifySourceAirport, modifyDestinationAirport,
										modifyDepartureDateTime, modifyArrivalDateTime);
								while (true) {
									try {
										System.out.println("Enter Ticket Cost: ");
										modifyTicketCost = scanner.nextDouble();
										break;
									} catch (InputMismatchException exception) {
										System.out.println(exception);
										continue;
									}
								}
								try {
									modifyScheduleFlight = new ScheduleFlight(
											flightService.viewFlight(modifyScheduleFlightId), modifyAvailableSeats,
											modifySchedule, modifyTicketCost);
									scheduleFlightService.addScheduleFlight(modifyScheduleFlight);
								} catch (FRSException e) {
									System.err.println(e.getMessage());
								}
								break;
							case 5:
								BigInteger deleteScheduleFlightId;
								while (true) {
									try {
										System.out.println("Enter Flight Number: ");
										deleteScheduleFlightId = scanner.nextBigInteger();
										scheduleFlightService.validateScheduleFlightWithId(deleteScheduleFlightId);
										break;
									} catch (FRSException exception) {
										System.err.println(exception.getMessage());
										continue;
									} catch (InputMismatchException exception) {
										scanner.nextLine();
										System.err.println(exception);
										continue;
									}
								}
								scheduleFlightService.deleteScheduleFlight(deleteScheduleFlightId);
								break;
							case 0:
								break SCHEDULEFLIGHTMANAGEMENT;
							}
						} while (true);
						break;
					case 0:
						break MANAGEMENT;
					}
				} while (true);
				break;
			}
			}
		} while (userTypeChoice != 0);
		scanner.close();
	}

}
