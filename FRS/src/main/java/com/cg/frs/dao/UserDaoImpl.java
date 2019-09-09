package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.cg.frs.dto.User;

public class UserDaoImpl implements UserDao 
{
	public List<User> userList=new ArrayList<User>();
	public User addUser(User user)
	{
		userList.add(user);
		return user;
	}
	public List<User> showUser()
	{
		return userList;
	}
	public User showUser(BigInteger userId)
	{
		for(User u:userList)
		{
			if(u.getUserId()==userId)
			return u;
	}
		return null;
	}
	public User updateUser(User user)
	{
		for(User u:userList)
		{
			if(u.getUserId()==user.getUserId())
			{
				u.setUserType(user.getUserType());
				u.setUserName(user.getUserName());
				u.setUserPassword(user.getUserPassword());
				u.setUserPhone(user.getUserPhone());
				u.setEmail(user.getEmail());
				return u;
			}
		}
		return null;
	}
	public void removeUser(BigInteger userId)
	{
		for(User u:userList)
		{
			if(u.getUserId()==userId)
			{
				userList.remove(u);
		}
	}
	}
}


		