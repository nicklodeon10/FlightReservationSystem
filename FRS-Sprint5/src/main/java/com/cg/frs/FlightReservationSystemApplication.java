package com.cg.frs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightReservationSystemApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(FlightReservationSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FlightReservationSystemApplication.class, args);
		logger.info("Running.");
	}

}
