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
		myLogger.info("Current working directory is " + userDir);
		PropertyConfigurator.configure(userDir + "log4j.properties");
		myLogger = Logger.getLogger("DBUtil.class");
		try {
			connection = DBUtil.getConnection();
			myLogger.info("Connection Obtained.");
		} catch (FRSException e) {
			myLogger.info("Connection not obtained at AirportDao :" + e);
		}
	}
	
	public User addUser(User user) {
		String sql ="insert into user(user_type,user_name,user_password,user_phone,user_email, flag) values(?,?,?,?,?,?)";		
		try {
			ps= connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUserType());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPassword());
			ps.setLong(4, user.getUserPhone().longValue());
			ps.setString(5, user.getEmail());
			ps.setBoolean(6, true);
			ps.executeUpdate();
			BigInteger generatedId = BigInteger.valueOf(0L);
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				generatedId = BigInteger.valueOf(rs.getLong(1));
				 myLogger.error("Auto generated Id " + generatedId);
			}	
			user.setUserId(generatedId);
		} catch (SQLException e) {
			myLogger.error("Error at addUser Dao method: "+e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at addUser Dao method : "+e);
				}
			}
		}
		return user;
	}
	
	public List<User> showUser() {
		String sql ="select * from user where flag=0 and user_type='customer'";
		List<User> userList = new ArrayList<User>();	
		try {
			ps= connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			rs= ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(BigInteger.valueOf(rs.getLong(1)));
				user.setUserType(rs.getString("user_type"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserPhone(BigInteger.valueOf(rs.getLong("user_phone")));
				user.setEmail(rs.getString("email"));
				userList.add(user);	
			}
		} catch (SQLException e) {
			myLogger.error(" Error at addUser Dao method : "+e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at addUser Dao method : "+e);
				}
			}
		}
		return userList;
	}

	public boolean removeUser(BigInteger userId) {
		String sql ="update user set flag=1 where user_id=?";
		try {
			ps=connection.prepareStatement(sql);
			ps.setLong(1, userId.longValue());
			ps.executeUpdate();
		}catch (SQLException e) {
			myLogger.error(" Error at delete User Dao method : "+e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at delete User Dao method : "+e);
				}
			}
		}
		return true;
	}

	@Override
	public User updateUser(User user) {
		String sql="Update user set user_name=?, user_password=?, user_phone=?, user_email=?;";
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPassword());
			ps.setLong(3, user.getUserPhone().longValue());
			ps.setString(4, user.getEmail());
			ps.executeUpdate();
		}catch (SQLException e) {
			myLogger.error(" Error at update User Dao method : "+e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at update User Dao method : "+e);
				}
			}
		}
		return user;
	}
}


		