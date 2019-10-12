/**
 * 
 */
package com.cg.frs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: DEVANG
 * description: Controller for user account. 
 * created date: 11/10/2019
 * modified: 12/10/2019
 */
@Controller
public class LoginController {
	
	/*	
	 *  Author: DEVANG
	 *  Description: Sends the Log In page view to the user.
	 *  Input: -
	 *  Output: Login Page View
	 *  Created Date: 11/10/2019
	 *  Last Modified: 12/10/2019
	 */
	@GetMapping("/logIn")
	public String loginPage() {
		return "Login";
	}
	
}
