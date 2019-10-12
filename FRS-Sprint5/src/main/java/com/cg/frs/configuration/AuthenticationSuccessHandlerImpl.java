/**
 * 
 */
package com.cg.frs.configuration;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cg.frs.FlightReservationSystemApplication;

/**
 * @author: DEVANG
 * description: Handles the redirection after successful login.
 * created date: 11/10/2019
 * modified: -
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private static final Logger logger = LoggerFactory.getLogger(FlightReservationSystemApplication.class);
	
	/*	
	 *  Author: DEVANG
	 *  Description: Checks the role of the logged in user and redirects.
	 *  Input: request, response and authentication objects.
	 *  Output: -
	 *  Created Date: 11/10/2019
	 *  Last Modified: -
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String targetUrl=null;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("ROLE_ADMIN"))
				targetUrl = "/admin";
			else if (grantedAuthority.getAuthority().equals("ROLE_USER"))
				targetUrl = "/user";
			else
				throw new IllegalStateException();
		}
		if (response.isCommitted()) {
			logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	//Getter for redirectStrategy object
	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
	
	//Setter for redirectStrategy object
	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

}
