package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.User;

public class UserDao implements IUserDao {
	
	private List<User> userList=new ArrayList<User>();
	
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


		