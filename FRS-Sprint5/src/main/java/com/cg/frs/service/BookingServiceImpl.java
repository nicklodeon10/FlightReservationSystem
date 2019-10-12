/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dto.Booking;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.InvalidBookingException;
import com.cg.frs.repository.BookingRepository;

/**
 * @author: DEVANG
 * description:  Implementation of the service interface for booking.
 * created date: 09/10/2019
 * modified: -
 */
@Service("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	BookingRepository bookingDao;

	@Autowired
	ScheduleFlightService scheduleFlightService;
	
	private static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	/*	
	 *  Author: DEVANG
	 *  Description: Sends a booking to the repository.
	 *  Input: Booking object.
	 *  Output: Added booking object.
	 *  Created Date: 09/10/2019
	 *  Last Modified: - 
	 */
	@Override
	public Booking addBooking(Booking booking) {
		logger.info("Storing Booking.");
		return bookingDao.save(booking);
	}

	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves the list of all bookings.
	 *  Input: -
	 *  Output: List of Bookings.
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@Override
	public List<Booking> viewBooking() throws InvalidBookingException {
		List<Booking> bookings = bookingDao.findAll();
		logger.info("Retrieved all bookings.");
		if (bookings.isEmpty()) {
			logger.error("No Bookings found.");
			throw new InvalidBookingException("No Bookings Found.");
		}
		logger.info("Returning retrieved Bookings.");
		return bookings;
	}

	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves a booking by its id.
	 *  Input: Booking Id.
	 *  Output: Booking object.
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@Override
	public Booking viewBooking(BigInteger bookingId) throws InvalidBookingException {
		Booking booking = bookingDao.findById(bookingId).get();
		logger.info("Retrieved Booking by Booking id: "+bookingId);
		if (booking == null) {
			logger.error("No Bookings found.");
			throw new InvalidBookingException("No Bookings Found.");
		}
		logger.info("Returning Booking.");
		return booking;
	}

	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves the bookings made by the user.
	 *  Input: User Id.
	 *  Output: List of bookings,
	 *  Created Date: 09/10/2019
	 *  Last Modified: - 
	 */
	
	@Override
	public List<Booking> viewBookingsByUser(BigInteger userId) throws InvalidBookingException {
		List<Booking> bookings = bookingDao.findByUserId(userId);
		logger.info("Retrieved all Bookings made by User Id: "+userId);
		if (bookings.isEmpty()) {
			logger.error("No Bookings found.");
			throw new InvalidBookingException("No Bookings Found.");
		}
		logger.info("Returning Bookings.");
		return bookings;
	}
	
	/*	
	 *  Author: DEVANG
	 *  Description: Cancels a booking.
	 *  Input: Booking Id.
	 *  Output: True if boolean is removed.
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@Override
	public boolean deleteBooking(BigInteger bookingId) throws InvalidBookingException {
		Booking booking = viewBooking(bookingId);
		logger.info("Retrieved booking by booking id: "+bookingId);
		if (booking == null) {
			logger.error("No Bookings found.");
			throw new InvalidBookingException("No Bookings Found.");
		}
		booking.setBookingState(false);
		bookingDao.save(booking);
		logger.info("Cancelled Booking.");
		return true;
	}

	/*	
	 *  Author: DEVANG
	 *  Description: Validates if seats are available in a flight against the current booking.
	 *  Input: ScheduleFlight object, Passenger Count
	 *  Output: True if seats are available, else false.
	 *  Created Date: 09/10/2019 
	 *  Last Modified: -
	 */
	@Override
	public boolean validatePassengerCount(ScheduleFlight scheduleFlight, Integer passengerChange) throws InvalidBookingException {
		if (passengerChange > scheduleFlight.getAvailableSeats()) {
			logger.info("Checked for seats. Seats not available.");
			throw new InvalidBookingException("Seats not available.");
		}
		logger.info("Checked for Seats. Seats available.");
		return true;
	}

}