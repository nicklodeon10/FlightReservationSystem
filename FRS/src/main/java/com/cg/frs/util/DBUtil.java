package com.cg.frs.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.frs.exception.FRSException;

public class DBUtil {

	private static Logger myLogger;
	private static Connection connection;

	static {
		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir") + "/src/main/resources/";
		System.out.println("Current working directory is " + userDir);
		PropertyConfigurator.configure(userDir + "log4j.properties");
		myLogger = Logger.getLogger("DBUtil.class");
	}

	public static Connection getConnection() throws FRSException {

		String url, username, password;
		try {
			Properties prop = DBUtil.loadProp();
			url = prop.getProperty("url");
			username = prop.getProperty("user");
			password = prop.getProperty("password");
			connection = DriverManager.getConnection(url, username, password);
			if (connection != null)
				myLogger.info("connection Obtained! : " + connection);
			else
				throw new FRSException("sorry!!! Something went wrong" + " with the connection");
		} catch (Exception e) {
			throw new FRSException(e.getMessage());
		}
		return connection;
	}

	private static Properties loadProp() throws FRSException {
		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir") + "/src/main/resources/";
		Properties myProp = new Properties();
		try (FileInputStream fis = new FileInputStream(userDir + "jdbc.properties")) {
			myProp.load(fis);
			myLogger.info("Property File loaded : ");
		} catch (Exception e) {
			myLogger.error("Property File Not loaded");
			throw new FRSException(e.getMessage());
		}
		return myProp;
	}

//method for closing the connection
	public static void closeConnection() throws FRSException {
		try {
			if (connection != null) {
				connection.close();
				myLogger.error("Closing Connection");
			} else
				myLogger.error("Connection already closed");
		} catch (Exception e) {
			myLogger.error("Connection already closed");
			throw new FRSException(e.getMessage());
		}
	}
}
