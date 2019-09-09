package com.cg.frs.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.frs.dao.UserDao;
import com.cg.frs.dao.UserDaoImpl;
import com.cg.frs.dto.User;

public class UserServiceImpl implements UserService
{
	UserDao userdao=new UserDaoImpl();
	public User addUser(User user)
	{
		return userdao.addUser(user);
	}
	public List<User> viewUser()
	{
		return userdao.showUser();
	}
	public User viewUser(BigInteger userId)
	{
		return userdao.showUser(userId);
	}
	public User updateUser(User user)
	{
		return userdao.updateUser(user);
	}
	public void deleteUser(BigInteger user)
	{
		userdao.removeUser(user);
	}
	//validation is to be done
}
