/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.User;
import com.cg.frs.exception.UserNotFoundException;

/**
 * @author DEVANG
 *
 */
public interface UserService {

	public BigInteger getUserIdFromName(String username) throws UserNotFoundException;

	public User addUser(User user);

	public List<User> viewUser() throws UserNotFoundException;
	
	public User getUserFromName(String username) throws UserNotFoundException;

}
