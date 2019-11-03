/**
 * 
 */
package com.cg.frs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.frs.configuration.JwtTokenUtil;
import com.cg.frs.dto.JwtRequest;
import com.cg.frs.dto.JwtResponse;
import com.cg.frs.dto.User;
import com.cg.frs.exception.UserNotFoundException;
import com.cg.frs.service.UserDetailsServiceImpl;
import com.cg.frs.service.UserService;

/**
 * @author nicklodeon10
 *	description: Performs Authentication and User management
 *	created: 21/10/2019
 *	modified: -
 */

@RestController
@CrossOrigin(origins = "http://52.66.238.169:4200")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	//Generates and retrieves token
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		logger.info("Generating Token");
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	//Adds a new user
	@PostMapping("/signup")
	public ResponseEntity<User> registerUser(@RequestBody User user){
		user.setActive(true);
		user.setRoles("ROLE_USER");
		logger.info("Adding User");
		return new ResponseEntity<User>(userService.addUser(user), HttpStatus.OK);
	}
	
	//Returns the role of a user
	@GetMapping("/getRole")
	public ResponseEntity<User> getRole(@RequestParam("username") String username){
		try {
			logger.info("Finding User Role");
			User user=userService.getUserFromName(username);
			user.setUserPassword("******");
			logger.info("Returning User for role");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			logger.info("Error in fetching");
			return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
		}
	}

	//Checks if user is authenticated or not
	private void authenticate(String username, String password) throws Exception {
		try {
			logger.info("Authenticating");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			logger.error("Auth Error");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			logger.error("Auth Error");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}