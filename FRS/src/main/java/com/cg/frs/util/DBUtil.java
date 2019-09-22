package com.cg.frs.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.frs.exception.FRSException;

public class DBUtil {

	private static Logger myLogger;

	static {
		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir") + "/src/main/resources/";
		//myLogger.info("Current working directory is " + userDir);
		PropertyConfigurator.configure(userDir + "log4j.properties");
		myLogger = Logger.getLogger("DBUtil.class");
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

}
