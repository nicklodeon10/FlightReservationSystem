/**
 * 
 */
package com.cg.frs.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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

	/*
	 * Author: NAVYA Description: Sends the add flight page view to the user Created
	 * Date: 09/10/2019 Last Modified: 14/10/2019
	 */

	@GetMapping(value = "/flight/add")
	public String getAddFlightPage(@ModelAttribute("flight") Flight flight) {

		return "AddFlight";

	}

	/*
	 * Author: NAVYA Description: Will Add The New Flight Details And Display Them
	 * Created Date: 09/10/2019 Last Modified: 14/10/2019 -
	 */
	@PostMapping(value = "/flight/added")
	public ModelAndView addFlight(@Valid @ModelAttribute("flight") Flight flight, BindingResult result)
			throws FlightExceptions {
		if (result.hasErrors()) {

			logger.error("No flight found ");
			return new ModelAndView("AddFlight", "flight", flight);

		} else {

			try {
				if (flight.getFlightModel() == null || flight.getCarrierName() == null
						|| flight.getSeatCapacity() == null) {
					logger.error("No parameters entered ");
					throw new FlightExceptions("NO DETAILS ENTERED");
				}
			} catch (FlightExceptions e) {
				logger.error("Accessing error page ");
				return new ModelAndView("ErrorPage");

			}
		}

		logger.info("Adding and setting flight state");
		flight.setFlightState(true);
		flightService.addFlight(flight);
		logger.info("Accessing Show flight ");
		List<Flight> allFlights = flightService.viewAllFlight();
		List<Flight> currentFlight = new ArrayList<Flight>();
		currentFlight.add(allFlights.get(allFlights.size() - 1));
		return new ModelAndView("ShowFlights", "flightList", currentFlight);
	}

	/*
	 * Author: NAVYA Description:Display The Available Flights Created Date:
	 * 09/10/2019 Last Modified: 14/10/2019 -
	 */
	@GetMapping(value = "/flight/view")
	public ModelAndView getShowFlightsPage() throws FlightExceptions {

		logger.info("Accessing Show flight ");
		return new ModelAndView("ShowFlights", "flightList", flightService.viewAllFlight());

	}

	/*
	 * Author: NAVYA Description:Will Open Page To Search Flight Created Date:
	 * 09/10/2019 Last Modified: 14/10/2019 -
	 */
	@GetMapping(value = "/flight/search")
	public String getSearchFlightPage() {
		logger.info("Accessing search flight ");
		return "SearchFlight";

	}

	/*
	 * Author: NAVYA Description: Shows The Searched Flight Details Created Date:
	 * 09/10/2019 Last Modified: 14/10/2019 -
	 */
	@GetMapping(value = "/flight/found")
	public ModelAndView getSearchFlightsResult(@RequestParam("flight_id") BigInteger flightId) throws FlightExceptions {

		logger.info("flight has been found");
		return new ModelAndView("SearchFlight", "flight", flightService.searchFlight(flightId));

	}

	/*
	 * Author: NAVYA Description: Will Open Page To Modify Flight Created Date:
	 * 09/10/2019 Last Modified: 14/10/2019 -
	 */
	@GetMapping(value = "/flight/modify")
	public String getModifyFlightPage(@ModelAttribute("flight") Flight flight) {
		logger.info("Accessing modify flight ");
		return "ModifyFlight";

	}

	/*
	 * Author: NAVYA Description: Shows The Flight To Be Modified Created Date:
	 * 09/10/2019 Last Modified: 14/10/2019 -
	 */
	@GetMapping(value = "/flight/modify/search")
	public ModelAndView getEditFlightsSearchResult(@RequestParam("flight_id") BigInteger flightId,
			@ModelAttribute("flight") Flight flight) throws FlightExceptions {

		logger.info("Parameters being set for modifying flight flight ");
		return new ModelAndView("ModifyFlight", "flight", flightService.searchFlight(flightId));

	}

	//

	/*
	 * Author: NAVYA Description: Modifies The Flight And Returns To show all
	 * flights Created Date: 09/10/2019 Last Modified: 14/10/2019 -
	 */
	@PostMapping(value = "/flight/modified")
	public ModelAndView modifyFlight(@ModelAttribute("flight") Flight flight) throws FlightExceptions {

		flightService.modifyFlight(flight);
		logger.info("Accessing Show flight ");
		return new ModelAndView("ShowFlights", "flightList", flightService.viewAllFlight());

	}

	/*
	 * Author: NAVYA Description: Will Open Page To Modify Flight Created Date:
	 * 09/10/2019 Last Modified: 14/10/2019 -
	 */
	@GetMapping(value = "/flight/remove")
	public String getRemoveFlightPage(@ModelAttribute("flight") Flight flight) {

		logger.info("Accessing remove flight ");
		return "RemoveFlight";

	}

	/*
	 * Author: NAVYA Description: Shows The Flight To Be Removed Created Date:
	 * 09/10/2019 Last Modified: 14/10/2019 -
	 */
	@GetMapping(value = "/flight/remove/search")
	public ModelAndView getRemoveFlightsSearchResult(@RequestParam("flight_id") BigInteger flightId,
			@ModelAttribute("flight") Flight flight) throws FlightExceptions {

		logger.info("Searching  flight ");
		return new ModelAndView("RemoveFlight", "flight", flightService.searchFlight(flightId));

	}

	/*
	 * Author: NAVYA Description:Removes The Flight And Returns To show flight
	 * Created Date: 09/10/2019 Last Modified: 14/10/2019 -
	 */
	@PostMapping(value = "/flight/removed")
	public ModelAndView flightRemove(@RequestParam("flight_id") BigInteger flightId) throws FlightExceptions {

		logger.info("Removing flight ");
		flightService.deleteFlight(flightId);
		logger.info("Accessing Show flight ");
		return new ModelAndView("ShowFlights", "flightList", flightService.viewAllFlight());

	}

}
