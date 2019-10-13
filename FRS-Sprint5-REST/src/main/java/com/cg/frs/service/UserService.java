/**
 * 
 */
package com.cg.frs.service;

import java.math.BigInteger;

import com.cg.frs.exception.UserNotFoundException;

/**
 * @author DEVANG
 *
 */
public interface UserService {

	public BigInteger getUserIdFromName(String username)throws UserNotFoundException;
	
}
