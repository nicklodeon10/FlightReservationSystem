/**
 * 
 */
package com.cg.frs.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author: DEVANG description: Configuration for Spring Security created date:
 *          11/10/2019 modified: 12/10/2019
 */
@EnableWebSecurity
public class SpringWebSecurity extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(SpringWebSecurity.class);

	@Autowired
	UserDetailsService userDetailsService;

	/*
	 * Author: DEVANG Description: Configures the Authentication Manager Input:
	 * AuthenticationManagerBuilder object Output: - Created Date: 11/10/2019 Last
	 * Modified: 12/10/2019
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("Created Authentication Manager.");
		auth.userDetailsService(userDetailsService);
		logger.info("Set UserDetails provider.");
	}

	/*
	 * Author: DEVANG Description: Provides a success handler for login. Input: -
	 * Output: AuthenticationSuccessHandler Object Created Date: 11/10/2019 Last
	 * Modified: -
	 */
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		logger.info("Returning AuthenticationSuccessHandler.");
		return new AuthenticationSuccessHandlerImpl();
	}

	/*
	 * Author: DEVANG Description: Sets access permissions for user roles and sets
	 * success handlers for login and logout. Input: HttpSecurity object Output: -
	 * Created Date: 11/10/2019 Last Modified: 12/10/2019
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("Configuring Security.");
		http.authorizeRequests().antMatchers("/").permitAll()
				.antMatchers("/admin", "/flight/add", "/flight/added", "/flight/view", "/flight/search",
						"/flight/found", "/flight/modify", "/flight/modify/search", "/flight/modified",
						"/flight/remove", "/flight/remove/search", "/flight/removed", "/getScheduleFlightPage",
						"/addScheduleFlight", "/showScheduledFlights", "/modifyScheduledFlight",
						"/scheduledFlightModify", "/removeScheduledFlight", "/scheduledFlightRemoveSearch",
						"/scheduledFlightRemove","/admin/viewallusers")
				.hasRole("ADMIN")
				.antMatchers("/user", "/booking/add", "/booking/addDetails", "/booking/save", "/booking/view",
						"/booking/cancel", "/booking/cancelview", "/booking/confirmcancel")
				.hasRole("USER").and().formLogin().loginPage("/login").failureUrl("/login?error=true")
				.successHandler(authenticationSuccessHandler()).and().logout().logoutSuccessUrl("/")
				.and().csrf().disable();
		logger.info("Configured Security.");
	}

	/*
	 * Author: DEVANG Description: Gets a Password Encoder Input: - Output: Instance
	 * of NoOpPasswordEncoder Created Date: 11/10/2019 Last Modified: -
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		logger.info("Returning BCrypyPasswordEncoder.");
		return NoOpPasswordEncoder.getInstance();
	}

}
