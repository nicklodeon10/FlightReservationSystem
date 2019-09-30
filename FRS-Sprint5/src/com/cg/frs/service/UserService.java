package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.User;

public interface UserService {
	
	public User addUser(User user);
	
	public List<User> viewUser();
	
	public User viewUser(BigInteger userId);
	
	public User updateUser(User user);
	
	public boolean deleteUser(BigInteger user);
	
	public boolean validateUserWithId(BigInteger userId);
	
	public boolean validateCustomerWithId(BigInteger userId);

	public boolean validateAdminWithId(BigInteger userId);
	
	public BigInteger validateUser(String username, String password);
}
