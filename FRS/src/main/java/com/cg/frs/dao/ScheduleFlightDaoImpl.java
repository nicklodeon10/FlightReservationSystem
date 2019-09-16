package com.cg.frs.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FRSException;
import com.cg.frs.util.DBUtil;

public class ScheduleFlightDaoImpl implements ScheduleFlightDao {

	private static Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private static Logger myLogger;

	static {
		Properties props = System.getProperties();
		String userDir = props.getProperty("user.dir") + "/src/main/resources/";
		System.out.println("Current working directory is " + userDir);
		PropertyConfigurator.configure(userDir + "log4j.properties");
		myLogger = Logger.getLogger("DBUtil.class");
		try {
			connection = DBUtil.getConnection();
		} catch (FRSException e) {
			myLogger.info("Connection not obtained at AirportDao :" + e);
		}
	}

	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleFlight) {
		String sql ="insert into scheduled_flight(flight_number, available_seats, source_airport, destination_airport) values(?, ?, ?, ?)";		
		try {
			ps= connection.prepareStatement(sql);
			ps.setLong(1, scheduleFlight.getFlight().getFlightNumber().longValue());
			ps.setInt(2, scheduleFlight.getAvailableSeats());
			ps.setString(3, scheduleFlight.getSchedule().getSourceAirport().getAirportCode());
			ps.setString(4, scheduleFlight.getSchedule().getDestinationAirport().getAirportCode());
			ps.executeUpdate();
		}catch (SQLException e) {
			System.out.println("Error at addScheduleFlight Dao method: "+e);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Error at addScheduleFlight Dao method: "+e);
				}
			}
		}
		return scheduleFlight;
	}

	public List<ScheduleFlight> viewScheduleFlight() {
		String sql = "Select * from schedule_flight";
		List<ScheduleFlight> scheduleflightList = new ArrayList<ScheduleFlight>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ScheduleFlight scheduleflight = new ScheduleFlight();
				scheduleflight.setAvailableSeats(rs.getInt("available_seats"));
				scheduleflight.getFlight().setFlightNumber(BigInteger.valueOf(rs.getLong("flight_number")));
				scheduleflight.getSchedule().getSourceAirport().setAirportName(rs.getString("source_airport"));
				scheduleflight.getSchedule().getDestinationAirport()
						.setAirportName(rs.getString("destination_airport"));
				scheduleflightList.add(scheduleflight);
			}
		} catch (SQLException e) {
			System.out.println(" Error at addScheduleFlight Dao method: " + e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at addScheduleFlight Dao method: " + e);
				}
			}
		}
		return scheduleflightList;
	}

	public boolean deleteScheduleFlight(BigInteger flightId) {
		String sql = "delete from schedule_flight where flight_number=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, flightId.longValue());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(" Error at delete ScheduleFlight Dao method : " + e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at delete ScheduleFlight Dao method : " + e);
				}
			}
		}
		return true;
	}
}
