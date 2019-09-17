package com.cg.frs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.frs.dto.Airport;
import com.cg.frs.exception.FRSException;
import com.cg.frs.util.DBUtil;

public class AirportDaoImpl implements AirportDao {

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
			myLogger.error("Connection not obtained at AirportDao :" + e);
		}
	}

	@Override
	public List<Airport> viewAirport() {
		String sql = "select * from airport";
		List<Airport> airportList = new ArrayList<Airport>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Airport airport = new Airport();
				airport.setAirportName(rs.getString("airport_name"));
				airport.setAirportLocation(rs.getString("airport_location"));
				airport.setAirportCode(rs.getString("airport_code"));
				airportList.add(airport);
			}
		} catch (SQLException e) {
			myLogger.error(" Error at showAirport Dao method : " + e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at showAirport Dao method : " + e);
				}
			}
		}
		return airportList;
	}

}
