/**
 * 
 */
package com.cg.frs.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.frs.dto.User;
import com.cg.frs.dto.UserDetailsImpl;
import com.cg.frs.repository.UserRepository;

/**
 * @author: DEVANG
 * description: Service for UserDetails 
 * created date: 11/10/2019
 * modified: -
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	UserRepository userdao;

	/*	
	 *  Author: DEVANG
	 *  Description: Retrieves a user and maps it to a UserDetails object.
	 *  Input: User name string.
	 *  Output: UserDetails object.
	 *  Created Date: 11/10/2019
	 *  Last Modified: - 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=userdao.findByUserName(username);
		logger.info("Finding user with username: "+username);
		user.orElseThrow(()->new UsernameNotFoundException("User not found: "+username));
		logger.info("Found user with username: "+username);
		logger.info("Mapping User to UserDetails and returning.");
		return user.map(UserDetailsImpl::new).get();
	}

}
