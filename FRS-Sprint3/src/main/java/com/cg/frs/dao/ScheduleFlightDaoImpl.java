package com.cg.frs.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.exception.FRSException;
import com.cg.frs.service.AirportServiceImpl;
import com.cg.frs.service.FlightServiceImpl;
import com.cg.frs.util.DBUtil;

public class ScheduleFlightDaoImpl implements ScheduleFlightDao {

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

	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleFlight) {
		String sql ="insert into scheduled_flight(flight_number, available_seats, ticket_cost) values(?,?,?);";		
		try {
			ps= connection.prepareStatement(sql);
			ps.setLong(1, scheduleFlight.getFlight().getFlightNumber().longValue());
			ps.setInt(2, scheduleFlight.getAvailableSeats());
			ps.setDouble(3, scheduleFlight.getTicketCost());
			ps.executeUpdate();
		}catch (SQLException e) {
			myLogger.error("Error at addScheduleFlight Dao method: "+e);
		}
		sql="Insert into schedule(source_airport, destination_airport, arrival_time, departure_time, flight_number) values(?,?,?,?,?)";
		try{
			ps=connection.prepareStatement(sql);
			ps.setString(1, scheduleFlight.getSchedule().getSourceAirport().getAirportCode());
			ps.setString(2, scheduleFlight.getSchedule().getDestinationAirport().getAirportCode());
			ps.setTimestamp(3, Timestamp.valueOf(scheduleFlight.getSchedule().getArrivalDateTime()));
			ps.setTimestamp(4, Timestamp.valueOf(scheduleFlight.getSchedule().getDepartureDateTime()));
			ps.setLong(5, scheduleFlight.getFlight().getFlightNumber().longValue());
			System.out.println("ps :"+ps.executeUpdate());
		}catch(SQLException exception) {
			myLogger.error("Error at addScheduleFlight Dao method: "+exception);
		}finally {
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error("Error at addScheduleFlight Dao method: "+e);
				}
			}
		}
		return scheduleFlight;
	}

	public List<ScheduleFlight> viewScheduleFlight() {
		String sql = "Select * from scheduled_flight";
		List<ScheduleFlight> scheduleflightList = new ArrayList<ScheduleFlight>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ScheduleFlight scheduleflight = new ScheduleFlight();
				scheduleflight.setAvailableSeats(rs.getInt("available_seats"));
				try {
					scheduleflight.setFlight(new FlightServiceImpl().viewFlight(BigInteger.valueOf(rs.getLong("flight_number"))));
				} catch (FRSException e) {
					myLogger.error(e.getMessage());
				}
				scheduleflight.setTicketCost(rs.getDouble("ticket_cost"));
				scheduleflightList.add(scheduleflight);
			}
		} catch (SQLException e) {
			myLogger.error(" Error at addScheduleFlight Dao method: " + e);
		}
		sql="select * from schedule where flight_number=?;";
		try {
			ps=connection.prepareStatement(sql);
			for(ScheduleFlight scheduleFlight: scheduleflightList) {
				ps.setLong(1, scheduleFlight.getFlight().getFlightNumber().longValue());
				rs=ps.executeQuery();
				if(rs.next()) {
					Schedule schedule=new Schedule();
					try {
						schedule.setSourceAirport(new AirportServiceImpl().viewAirport(rs.getString("source_airport")));
						schedule.setDestinationAirport(new AirportServiceImpl().viewAirport(rs.getString("destination_airport")));
					} catch (FRSException exception) {
						myLogger.error(" Error at addScheduleFlight Dao method: " + exception);
					}
					schedule.setDepartureDateTime(rs.getTimestamp("departure_time").toLocalDateTime());
					schedule.setArrivalDateTime(rs.getTimestamp("arrival_time").toLocalDateTime());
					scheduleFlight.setSchedule(schedule);
				}
			}
		}catch(SQLException exception) {
			myLogger.error(" Error at addScheduleFlight Dao method: " + exception);
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at addScheduleFlight Dao method: " + e);
				}
			}
		}
		return scheduleflightList;
	}
	
	@Override
	public ScheduleFlight updateScheduleFlight(ScheduleFlight scheduleFlight) {
		String sql="Update scheduled_flight set source_airport=?, destination_airport=?, ticket_cost=? where flight_number=?;";
		try {
			ps=connection.prepareStatement(sql);
			ps.setString(1, scheduleFlight.getSchedule().getSourceAirport().getAirportCode());
			ps.setString(2, scheduleFlight.getSchedule().getDestinationAirport().getAirportCode());
			ps.setDouble(3, scheduleFlight.getTicketCost());
			ps.setLong(4, scheduleFlight.getFlight().getFlightNumber().longValue());
			ps.executeUpdate();
		}catch (SQLException e) {
			myLogger.error(" Error at update ScheduleFlight Dao method : " + e);
		} 
		sql="Update schedule set source_airport=?, destination_airport=?, arrival_time=?, departure_time=? where flight_number=?;";
		try{
			ps=connection.prepareStatement(sql);
			ps.setString(1, scheduleFlight.getSchedule().getSourceAirport().getAirportCode());
			ps.setString(2, scheduleFlight.getSchedule().getDestinationAirport().getAirportCode());
			ps.setTimestamp(3, Timestamp.valueOf(scheduleFlight.getSchedule().getArrivalDateTime()));
			ps.setTimestamp(4, Timestamp.valueOf(scheduleFlight.getSchedule().getDepartureDateTime()));
			ps.setLong(5, scheduleFlight.getFlight().getFlightNumber().longValue());
			ps.executeUpdate();
		}catch(SQLException exception){
			myLogger.info(" Error at update ScheduleFlight Dao method : " + exception);
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at delete ScheduleFlight Dao method : " + e);
				}
			}
		}
		return scheduleFlight;
	}

	public boolean deleteScheduleFlight(BigInteger flightId) {
		String sql = "delete from scheduled_flight where flight_number=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, flightId.longValue());
			ps.executeUpdate();
		} catch (SQLException e) {
			myLogger.error(" Error at delete ScheduleFlight Dao method : " + e);
		}
		sql = "delete from schedule where flight_number=?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, flightId.longValue());
			ps.executeUpdate();
		}catch(SQLException exception){
			myLogger.info("Error at delete ScheduleFlight Dao method : " + exception);
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at delete ScheduleFlight Dao method : " + e);
				}
			}
		}
		return true;
	}
	
}
