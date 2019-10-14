/**
 * 
 */
package com.cg.frs.controller;

import java.math.BigInteger;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.frs.dto.Flight;
import com.cg.frs.exception.FlightExceptions;
import com.cg.frs.service.FlightService;

/**
 * @author NAVYA description: Controller for Flight created date: 09/10/2019
 *         modified: 12/10/2019
 */
@ComponentScan
@Controller

public class FlightController {

	private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

	@Autowired
	FlightService flightService;

	// Will Open Page To Add Flight
	@GetMapping(value = "/flight/add")
	public String getAddFlightPage(@ModelAttribute("flight") Flight flight) {

		return "AddFlight";

	}

	// Will Add The New Flight Details And Display Them
	@PostMapping(value = "/flight/added")
	public ModelAndView addFlight(@Valid @ModelAttribute("flight") Flight flight, BindingResult result)
			throws FlightExceptions {
		if (result.hasErrors()) {

			return new ModelAndView("AddFlight", "flight", flight);

		} else {

			try {
				if (flight.getFlightModel() == null || flight.getCarrierName() == null
						|| flight.getSeatCapacity() == null) {

					throw new FlightExceptions("NO DETAILS ENTERED");
				}
			} catch (FlightExceptions e) {

				return new ModelAndView("ErrorPage");

			}
		}
		flight.setFlightState(true);
		flightService.addFlight(flight);

		return new ModelAndView("ShowFlights", "flightList", flightService.viewAllFlight());

	}

	// Display The Available Flights
	@GetMapping(value = "/flight/view")
	public ModelAndView getShowFlightsPage() throws FlightExceptions {

		return new ModelAndView("ShowFlights", "flightList", flightService.viewAllFlight());

	}

	// Will Open Page To Search Flight
	@GetMapping(value = "/flight/search")
	public String getSearchFlightPage() {

		return "SearchFlight";

	}

	// Shows The Searched Flight Details
	@GetMapping(value = "/flight/found")
	public ModelAndView getSearchFlightsResult(@RequestParam("flight_id") BigInteger flightId) throws FlightExceptions {

		return new ModelAndView("SearchFlight", "flight", flightService.searchFlight(flightId));

	}

	// Will Open Page To Modify Flight
	@GetMapping(value = "/flight/modify")
	public String getModifyFlightPage(@ModelAttribute("flight") Flight flight) {

		return "ModifyFlight";

	}

	// Shows The Flight To Be Modified
	@GetMapping(value = "/flight/modify/search")
	public ModelAndView getEditFlightsSearchResult(@RequestParam("flight_id") BigInteger flightId,
			@ModelAttribute("flight") Flight flight) throws FlightExceptions {

		return new ModelAndView("ModifyFlight", "flight", flightService.searchFlight(flightId));

	}

	// Modifies The Flight And Returns To--
	@PostMapping(value = "/flight/modified")
	public ModelAndView modifyFlight(@ModelAttribute("flight") Flight flight) throws FlightExceptions {

		flightService.modifyFlight(flight);
		return new ModelAndView("ShowFlights", "flightList", flightService.viewAllFlight());

	}

	// Will Open Page To Modify Flight
	@GetMapping(value = "/flight/remove")
	public String getRemoveFlightPage(@ModelAttribute("flight") Flight flight) {

		return "RemoveFlight";

	}

	// Shows The Flight To Be Removed
	@GetMapping(value = "/flight/remove/search")
	public ModelAndView getRemoveFlightsSearchResult(@RequestParam("flight_id") BigInteger flightId,
			@ModelAttribute("flight") Flight flight) throws FlightExceptions {

		return new ModelAndView("RemoveFlight", "flight", flightService.searchFlight(flightId));

	}

	// Removes The Flight And Returns To--
	@PostMapping(value = "/flight/removed")
	public ModelAndView flightRemove(@RequestParam("flight_id") BigInteger flightId) throws FlightExceptions {

		flightService.deleteFlight(flightId);
		return new ModelAndView("ShowFlights", "flightList", flightService.viewAllFlight());

	}
}
