package com.cg.frs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: DEVANG
 * description: The runner class for the application 
 * created date: 09/10/2019
 * modified: -
 */

@SpringBootApplication
public class FlightReservationSystemApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(FlightReservationSystemApplication.class);

	/*	
	 *  Author: Devang
	 *  Description: The main class
	 *  Input: -
	 *  Output: -
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	public static void main(String[] args) {
		SpringApplication.run(FlightReservationSystemApplication.class, args);
		logger.info("Running.");
	}

}
