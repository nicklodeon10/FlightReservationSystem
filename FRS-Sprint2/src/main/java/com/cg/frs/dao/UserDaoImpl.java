package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.User;

public class UserDaoImpl implements UserDao {
	
	private static List<User> userList=new ArrayList<User>();
	
	static {
		User admin=new User();
		admin.setUserType("admin");
		admin.setUserId(BigInteger.valueOf(99999L));
		admin.setUserName("ADMIN");
		admin.setUserPassword("ADMIN");
		admin.setUserPhone(BigInteger.valueOf(9999999999L));
		admin.setEmail("admin@frs.com");
		userList.add(admin);
		User user=new User();
		user.setUserType("customer");
		user.setUserId(BigInteger.valueOf(88888));
		user.setUserName("CUSTOMER");
		user.setUserPassword("CUSTOMER");
		user.setUserPhone(BigInteger.valueOf(8888888888L));
		user.setEmail("user@frs.com");
		userList.add(user);
	}
	
	public User addUser(User user) {
		if(userList.indexOf(user)== -1) {
			userList.add(user);
		}else {
			int modifyIndex=userList.indexOf(user);
			userList.set(modifyIndex, user);
		}
		return user;
	}
	
	public List<User> showUser() {
		return userList;
	}

	public boolean removeUser(BigInteger userId) {
		for(User u:userList) {
			if(u.getUserId().equals(userId)){
				userList.remove(u);
				return true;
			}
		}
		return false;
	}
}


		