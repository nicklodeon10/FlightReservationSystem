package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.User;
import com.cg.frs.exception.FRSException;

public interface UserService {
	
	public User addUser(User user);
	
	public List<User> viewUser();
	
	public User viewUser(BigInteger userId);
	
	public User updateUser(User user);
	
	public void deleteUser(BigInteger user);
	
	public BigInteger validateUserWithId(BigInteger userId) throws FRSException;
	
	public BigInteger validateCustomerWithId(BigInteger userId) throws FRSException;

	public BigInteger validateAdminWithId(BigInteger userId) throws FRSException;
	
	public User validateUser(User user) throws FRSException;
}
