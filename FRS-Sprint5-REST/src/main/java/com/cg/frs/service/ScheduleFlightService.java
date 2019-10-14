/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import com.cg.frs.dto.Airport;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FRSException;
import com.cg.frs.exception.FlightNotFoundException;

/**
 * @author: SURYA, DEVANG
 * description: Service Interface Method for Scheduled Flight.
 * created date: 10/10/2019
 * modified: 10/10/2019
 */
public interface ScheduleFlightService {
	
	/*	
	 *  Author
	 *  Description
	 *  Input
	 *  Created Date
	 *  Last Modified 
	 */
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight);

	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves a list of scheduled flights against the given inputs.
	 *  Input: Source Airport, Destination Airport, Date
	 *  Output: List of Scheduled Flights.
	 *  Created Date: 10/10/2019
	 *  Last Modified: 10/10/2019
	 */
	public List<ScheduleFlight> viewScheduleFlights(Airport source, Airport destination, LocalDate flightDate)
			throws FlightNotFoundException;

	/*	
	 *  Author
	 *  Description
	 *  Input
	 *  Created Date
	 *  Last Modified 
	 */
	public ScheduleFlight viewScheduleFlights(BigInteger flightId) throws FlightNotFoundException;

	/*	
	 *  Author
	 *  Description
	 *  Input
	 *  Created Date
	 *  Last Modified 
	 */
	public List<ScheduleFlight> viewScheduleFlight();

	/*	
	 *  Author
	 *  Description
	 *  Input
	 *  Created Date
	 *  Last Modified 
	 */
	public ScheduleFlight modifyScheduleFlight(ScheduleFlight scheduleFlight);

	/*	
	 *  Author
	 *  Description
	 *  Input
	 *  Created Date
	 *  Last Modified 
	 */
	public boolean deleteScheduleFlight(BigInteger flightId) throws FRSException;

}
