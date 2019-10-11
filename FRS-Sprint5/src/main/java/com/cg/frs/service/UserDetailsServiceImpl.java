/**
 * 
 */
package com.cg.frs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.frs.dao.UserDao;
import com.cg.frs.dto.User;
import com.cg.frs.dto.UserDetailsImpl;

/**
 * @author DEVANG
 *
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDao userdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=userdao.findByUserName(username);
		user.orElseThrow(()->new UsernameNotFoundException("User not found: "+username));
		return user.map(UserDetailsImpl::new).get();
	}

}
