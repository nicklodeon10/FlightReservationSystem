package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.User;

public interface UserService {
	
	public User addUser(User user);
	
	public List<User> viewUser();
	
	public User viewUser(BigInteger userId);
	
	public User updateUser(User user);
	
	public void deleteUser(BigInteger user);

}
