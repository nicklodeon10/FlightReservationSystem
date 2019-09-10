package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dao.UserDao;
import com.cg.frs.dao.UserDaoImpl;
import com.cg.frs.dto.User;
import com.cg.frs.exception.FRSException;

public class UserServiceImpl implements UserService{
	
	UserDao userDao=new UserDaoImpl();
	
	public User addUser(User user) {
		return userDao.addUser(user);
	}
	
	public List<User> viewUser() {
		return userDao.showUser();
	}
	
	public User viewUser(BigInteger userId)	{
		List<User> userList=userDao.showUser();
		for(User user: userList) {
			if(user.getUserId()==userId) {
				return user;
			}
		}
		return null;
	}
	
	public User updateUser(User user) {
		return userDao.addUser(user);
	}
	
	public void deleteUser(BigInteger user) {
		userDao.removeUser(user);
	}

	@Override
	public BigInteger validateUserWithId(BigInteger userId) throws FRSException {
		if(viewUser(userId)==null)
			throw new FRSException("Invalid User Id.");
		return userId;
	}

}
