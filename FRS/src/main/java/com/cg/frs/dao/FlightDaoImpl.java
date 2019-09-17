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

import com.cg.frs.dto.Flight;
import com.cg.frs.exception.FRSException;
import com.cg.frs.util.DBUtil;

public class FlightDaoImpl implements FlightDao {

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

	public Flight addFlight(Flight flight) {
		String sql = "insert into flight(carrier_name,flight_model,seat_capacity) values(?,?,?)";
		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, flight.getCarrierName());
			ps.setString(2, flight.getFlightModel());
			ps.setInt(3, flight.getSeatCapacity());
			ps.executeUpdate();
			BigInteger generatedId = BigInteger.valueOf(0L);
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				generatedId = BigInteger.valueOf(rs.getLong(1));
				 myLogger.error("Auto generated Id " + generatedId);
			}	
			flight.setFlightNumber(generatedId);
		} catch (SQLException e) {
			myLogger.error(" Error at addFlight Dao method : " + e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at addFlight Dao method : " + e);
				}
			}
		}
		return flight;
	}

	public List<Flight> viewFlight() {
		String sql = "Select * from flight";
		List<Flight> flightList = new ArrayList<Flight>();
		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			rs = ps.executeQuery();
			while (rs.next()) {
				Flight flight = new Flight();
				flight.setFlightNumber(BigInteger.valueOf(rs.getLong(1)));
				flight.setFlightModel(rs.getString("carrier_name"));
				flight.setCarrierName(rs.getString("flight_model"));
				flight.setSeatCapacity(rs.getInt("seat_capacity"));
				flightList.add(flight);
			}
		} catch (SQLException e) {
			myLogger.error("Error at addFlight Dao method: " + e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at addFlight Dao method: " + e);
				}
			}
		}
		return flightList;
	}
	
	@Override
	public Flight updateFlight(Flight flight) {
		String sql="update flight set carrier_name=?, flight_model=?, seat_capacity=?;";
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1, flight.getCarrierName());
			ps.setString(2, flight.getFlightModel());
			ps.setInt(3, flight.getSeatCapacity());
			ps.executeUpdate();
		}catch (SQLException e) {
			myLogger.error(" Error at update Flight Dao method : " + e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at update Flight Dao method : " + e);
				}
			}
		}
		return flight;
	}

	@Override
	public boolean deleteFlight(BigInteger flightId) {
		String sql = "delete from flight where flight_number=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, flightId.longValue());
			ps.executeUpdate();
		} catch (SQLException e) {
			myLogger.error(" Error at delete Flight Dao method : " + e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at delete Flight Dao method : " + e);
				}
			}
		}
		return true;
	}
	
}