/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.Booking;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.InvalidBookingException;

/**
 * @author: DEVANG
 * description: Service Interface for Booking.
 * created date: 09/10/2019
 * modified: -
 */
public interface BookingService {

	/*	
	 *  Author: DEVANG
	 *  Description: Sends a booking to the repository.
	 *  Input: Booking object.
	 *  Output: Added booking object.
	 *  Created Date: 09/10/2019
	 *  Last Modified: - 
	 */
	public Booking addBooking(Booking booking)throws Exception;

	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves the list of all bookings.
	 *  Input: -
	 *  Output: List of Bookings.
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	public List<Booking> viewBooking()throws InvalidBookingException;

	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves a booking by its id.
	 *  Input: Booking Id.
	 *  Output: Booking object.
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	public Booking viewBooking(BigInteger bookingId)throws InvalidBookingException;
	
	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves the bookings made by the user.
	 *  Input: User Id.
	 *  Output: List of bookings,
	 *  Created Date: 09/10/2019
	 *  Last Modified: - 
	 */
	public List<Booking> viewBookingsByUser(BigInteger userId)throws InvalidBookingException;

	/*	
	 *  Author: DEVANG
	 *  Description: Cancels a booking.
	 *  Input: Booking Id.
	 *  Output: True if boolean is removed.
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	public boolean deleteBooking(BigInteger bookingId)throws InvalidBookingException;

	/*	
	 *  Author: DEVANG
	 *  Description: Validates if seats are available in a flight against the current booking.
	 *  Input: ScheduleFlight object, Passenger Count
	 *  Output: True if seats are available, else false.
	 *  Created Date: 09/10/2019 
	 *  Last Modified: -
	 */
	public boolean validatePassengerCount(ScheduleFlight scheduleFlight, Integer passengerChange)throws InvalidBookingException;

}
