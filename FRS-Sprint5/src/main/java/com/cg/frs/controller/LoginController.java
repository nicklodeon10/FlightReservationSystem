/**
 * 
 */
package com.cg.frs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author DEVANG
 *
 */

@Controller
public class LoginController {
	
	@GetMapping("/logIn")
	public String loginPage() {
		return "Login";
	}
	
}
