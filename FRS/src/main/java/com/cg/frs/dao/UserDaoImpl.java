package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.User;

public class UserDaoImpl implements UserDao {
	
	private List<User> userList=new ArrayList<User>();
	
	public User addUser(User user) {
		userList.add(user);
		return user;
	}
	
	public List<User> showUser() {
		return userList;
	}

	public void removeUser(BigInteger userId) {
		for(User u:userList) {
			if(u.getUserId()==userId){
				userList.remove(u);
				break;
			}
		}
	}
}


		