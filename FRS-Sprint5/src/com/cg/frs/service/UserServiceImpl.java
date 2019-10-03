package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dao.UserDao;
import com.cg.frs.dto.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public User addUser(User user) {
		return userDao.addUser(user);
	}

	public List<User> viewUser() {
		return userDao.showUser();
	}

	public User viewUser(BigInteger userId) {
		return userDao.showUser(userId);
	}

	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	public boolean deleteUser(BigInteger user) {
		return userDao.removeUser(user);
	}

	@Override
	public boolean validateUserWithId(BigInteger userId) {
		User user = userDao.showUser(userId);
		if (user.getUserId() == null)
			return false;
		return true;
	}

	@Override
	public boolean validateCustomerWithId(BigInteger userId) {
		User user = userDao.showUser(userId);
		if (user == null || user.getUserType().equalsIgnoreCase("admin"))
			return false;
		return true;
	}

	@Override
	public boolean validateAdminWithId(BigInteger userId) {
		User user = userDao.showUser(userId);
		if (user == null || user.getUserType().equalsIgnoreCase("customer"))
			return false;
		return true;
	}

	@Override
	public BigInteger validateUser(String username, String password) {
		List<User> userList = viewUser();
		for (User user : userList) {
			if (user.getUserName().equals(username) && user.getUserPassword().equals(password)) {
				return user.getUserId();
			}
		}
		return null;
	}

}
