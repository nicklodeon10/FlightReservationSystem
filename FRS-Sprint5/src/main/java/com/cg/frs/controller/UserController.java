package com.cg.frs.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cg.frs.dto.User;
import com.cg.frs.exception.UserNotFoundException;
import com.cg.frs.service.AirportService;
import com.cg.frs.service.UserService;

/**
 * @author: DEVANG description: Controller for Bookings created date: 09/10/2019
 *          modified: 12/10/2019
 */

@Controller
public class UserController {

	@Autowired
	AirportService airportService;

	@Autowired
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/*
	 * Author: DEVANG Description: Sends the home page view to the client Input: -
	 * Output: Home Page ModelAndView Created Date: 11/10/2019 Last Modified: -
	 */
	@GetMapping("/")
	public ModelAndView home() {
		logger.info("Returning Home View.");
		return new ModelAndView("Home", "airportList", airportService.viewAirport());
	}

	/*
	 * Author: DEVANG Description: Sends the admin panel view to the admin Input: -
	 * Output: - Created Date: 11/10/2019 Last Modified: -
	 */
	@GetMapping("/admin")
	public String adminPanel(Principal principal) {
		logger.info("Returning Admin Panel View for admin: " + principal.getName());
		return "AdminPanel";
	}

	/*
	 * Author: DEVANG Description: Sends the user panel view to the user Input: -
	 * Output: - Created Date: 11/10/2019 Last Modified: -
	 */
	@GetMapping("/user")
	public String userPanel(Principal principal) {
		logger.info("Returning User Panel View for user: " + principal.getName());
		return "UserPanel";
	}

	// Send to Sign Up Page
	@GetMapping("/signup")
	public String signUpPage(@ModelAttribute("user") User user) {
		return "SignUp";
	}

	// Add User to Repository
	@PostMapping("/useradd")
	public String addUser(@ModelAttribute("user") User user) {
		user.setActive(true);
		user.setRoles("ROLE_USER");
		userService.addUser(user);
		return "UserAdded";
	}

	// Get list of User
	@GetMapping("/admin/userlist")
	public ModelAndView getUserListPage() {
		logger.info("Returning user list page.");
		try {
			return new ModelAndView("RegUserList", "userList", userService.viewUser());
		} catch (UserNotFoundException e) {
			logger.error("No Users found.");
			return new ModelAndView("ErrorPage");
		}
	}

}
