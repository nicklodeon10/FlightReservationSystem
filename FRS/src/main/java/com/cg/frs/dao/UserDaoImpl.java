package com.cg.frs.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.frs.dto.User;
import com.cg.frs.exception.FRSException;
import com.cg.frs.util.DBUtil;

public class UserDaoImpl implements UserDao {
	
	private static Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private static Logger myLogger;
	
	static {
		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir") + "/src/main/resources/";
		//myLogger.info("Current working directory is " + userDir);
		PropertyConfigurator.configure(userDir + "log4j.properties");
		myLogger = Logger.getLogger("DBUtil.class");
		try {
			connection = DBUtil.getConnection();
			myLogger.info("Connection Obtained.");
		} catch (FRSException e) {
			myLogger.info("Connection not obtained at AirportDao :" + e);
		}
	}
	
	@Override
	public User addUser(User user) {
		String sql="Insert into user(user_type, user_name, user_password, user_phone, user_email, flag) values(?,?,?,?,?,0)";
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1, "customer");
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPassword());
			ps.setLong(4, user.getUserPhone().longValue());
		}
		return null;
	}
	
	@Override
	public List<User> showUser() {
		return null;
	}
	
	@Override
	public boolean removeUser(BigInteger userId) {
		return false;
	}

	@Override
	public User updateUser(User user) {
		return user;
	}
}


		