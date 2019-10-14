/**
 * 
 */
package com.cg.frs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: DEVANG description: Controller for user account. created date:
 *          11/10/2019 modified: 12/10/2019
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/*
	 * Author: DEVANG Description: Sends the Log In page view to the user. Input: -
	 * Output: Login Page View Created Date: 11/10/2019 Last Modified: 12/10/2019
	 */
	@GetMapping("/login")
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			Model model) {
		String errorMessage = null;
		if (error != null) {
			logger.error("Incorrect Credentials.");
			errorMessage = "Username or Password is incorrect !!";
		}
		model.addAttribute("errorMessage", errorMessage);
		logger.info("Viewing Log In Page.");
		return "LogIn";
	}
	
}
