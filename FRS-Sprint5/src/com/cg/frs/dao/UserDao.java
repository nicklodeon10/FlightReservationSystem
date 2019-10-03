package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dto.User;

public interface UserDao {
	
	public User addUser(User user);
	
	public List<User> showUser();
	
	public User showUser(BigInteger userId);
	
	public User updateUser(User user);
	
	public boolean removeUser(BigInteger user);

}
