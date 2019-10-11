package com.cg.frs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cg.frs")
public class FlightReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightReservationSystemApplication.class, args);
		System.out.println("asd");
	}

}
