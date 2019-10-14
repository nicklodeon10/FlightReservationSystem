/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public BigInteger getUserIdFromName(String username) throws UserNotFoundException {
		logger.info("Retrieving User.");
		Optional<User> user;
		try {
			user = userRepository.findByUserName(username);
		} catch (NoSuchElementException exception) {
			logger.error("User not found: " + username);
			throw new UserNotFoundException("User Not Found.");
		}
		logger.info("Returning Found User.");
		return user.get().getUserId();
	}

	@Override
	public User addUser(User user) throws Exception {
		logger.info("Adding User.");
		userRepository.save(user);
		return user;
	}

	@Override
	public List<User> viewUser() throws UserNotFoundException {
		logger.info("Retrieving Users.");
		List<User> userList;
		userList = userRepository.findAll();
		logger.info("Retrieved All Users.");
		if (userList.isEmpty()) {
			logger.error("No Users Found");
			throw new UserNotFoundException("No Users found");
		}
		logger.info("Returning Retrieved Users.");
		return userList;
	}

}
