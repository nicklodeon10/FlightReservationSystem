/**
 * 
 */
package com.cg.frs.controller;

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

	/*
	 * Author: DEVANG Description: Sends the Log In page view to the user. Input: -
	 * Output: Login Page View Created Date: 11/10/2019 Last Modified: 12/10/2019
	 */
	@GetMapping("/login")
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		String errorMessage = null;
		if (error != null) {
			errorMessage = "Username or Password is incorrect !!";
		}
		if (logout != null) {
			errorMessage = "You have been successfully logged out !!";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "Login";
	}
}
