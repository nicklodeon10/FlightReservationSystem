package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.frs.dao.UserDao;
import com.cg.frs.dto.User;
import com.cg.frs.exception.FRSException;

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
	public BigInteger validateUserWithId(BigInteger userId) throws FRSException {
		User user = userDao.showUser(userId);
		if (user.getUserId() == null)
			throw new FRSException("User Not Found.");
		return userId;
	}

	@Override
	public BigInteger validateCustomerWithId(BigInteger userId) throws FRSException {
		try {
			User user = userDao.showUser(userId);
			if (user == null || user.getUserType().equalsIgnoreCase("admin"))
				throw new FRSException("Customer not found.");
		} catch (NullPointerException e) {
			throw new FRSException("Admin not found.");
		}
		return userId;
	}

	@Override
	public BigInteger validateAdminWithId(BigInteger userId) throws FRSException {
		try {
			User user = userDao.showUser(userId);
			if (user == null || user.getUserType().equalsIgnoreCase("customer"))
				throw new FRSException("Admin not found.");
		} catch (NullPointerException e) {
			throw new FRSException("Admin not found.");
		}
		return userId;
	}

	@Override
	public boolean validatePhoneNumber(BigInteger phoneNo) throws FRSException {
		String userPhone = phoneNo.toString();
		char firstPhoneDigit = userPhone.charAt(0);
		if (userPhone.length() != 10 || firstPhoneDigit == '1' || firstPhoneDigit == '2' || firstPhoneDigit == '3'
				|| firstPhoneDigit == '4' || firstPhoneDigit == '5')
			throw new FRSException("Invalid Phone Number.");
		return true;
	}

	@Override
	public boolean validateEmail(String email) throws FRSException {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		if (!pattern.matcher(email).matches())
			throw new FRSException("Invalid Email Address.");
		return true;
	}

}
