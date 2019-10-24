/**
 * 
 */
package com.cg.frs.controller;

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
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	UserService userService;

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<User> registerUser(@RequestBody User user){
		user.setActive(true);
		user.setRoles("ROLE_USER");
		return new ResponseEntity<User>(userService.addUser(user), HttpStatus.OK);
	}
	
	@GetMapping("/getRole")
	public ResponseEntity<User> getRole(@RequestParam("username") String username){
		try {
			System.out.println("Finding User Role");
			User user=userService.getUserFromName(username);
			user.setUserPassword("******");
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (UserNotFoundException e) {
			System.out.println("Error in fetching");
			return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}