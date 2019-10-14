/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dto.User;
import com.cg.frs.exception.UserNotFoundException;
import com.cg.frs.repository.UserRepository;

/**
 * @author OSIS
 *
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public BigInteger getUserIdFromName(String username) throws UserNotFoundException {
		Optional<User> user=userRepository.findByUserName(username);
		return user.get().getUserId();
	}

}
