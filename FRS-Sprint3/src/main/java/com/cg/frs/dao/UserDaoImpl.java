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
			ps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, "customer");
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getUserPassword());
			ps.setLong(4, user.getUserPhone().longValue());
			ps.setString(5, user.getEmail());
			ps.executeUpdate();
			rs=ps.getGeneratedKeys();
			if(rs.next()) {
				user.setUserId(BigInteger.valueOf(rs.getLong(1)));
			}
		}catch(SQLException exception) {
			myLogger.error(exception.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at Add User Dao method: " + e);
				}
			}
		}
		return user;
	}
	
	@Override
	public List<User> showUser() {
		String sql="Select * from user where flag=?";
		List<User> userList=new ArrayList<User>();
		try {
			User user=null;
			ps=connection.prepareStatement(sql);
			ps.setInt(1, 0);
			rs=ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUserId(BigInteger.valueOf(rs.getLong("user_id")));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserPhone(BigInteger.valueOf(rs.getLong("user_phone")));
				user.setUserType(rs.getString("user_type"));
				user.setEmail(rs.getString("user_email"));
				userList.add(user);
			}
		}catch(SQLException exception) {
			myLogger.error(exception.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at Add User Dao method: " + e);
				}
			}
		}
		return userList;
	}
	
	public User showUser(BigInteger userId) {
		String sql="Select * from user where user_id=? and flag=0;";
		User user=new User();
		try {
			ps=connection.prepareStatement(sql);
			ps.setLong(1, userId.longValue());
			rs=ps.executeQuery();
			if(rs.next()) {
				user.setUserId(userId);
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserPhone(BigInteger.valueOf(rs.getLong("user_phone")));
				user.setUserType(rs.getString("user_type"));
				user.setEmail(rs.getString("user_email"));
			}
		}catch(SQLException exception) {
			myLogger.error(exception.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at Add User Dao method: " + e);
				}
			}
		}
		return user;
	}
	
	@Override
	public boolean removeUser(BigInteger userId) {
		String sql="Update user set flag=1 where user_id=?;";
		try {
			ps=connection.prepareStatement(sql);
			ps.setLong(1, userId.longValue());
			ps.executeUpdate();
		}catch(SQLException exception) {
			myLogger.error(exception.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at Add User Dao method: " + e);
				}
			}
		}
		return true;
	}

	@Override
	public User updateUser(User user) {
		String sql="Update user set user_name=?, user_password=?, user_phone=?, user_email=? where user_id=?;";
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPassword());
			ps.setLong(3, user.getUserPhone().longValue());
			ps.setString(4, user.getEmail());
			ps.setLong(5, user.getUserId().longValue());
			ps.executeUpdate();
		}catch(SQLException exception) {
			myLogger.error(exception.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at Add User Dao method: " + e);
				}
			}
		}
		return user;
	}
}


		