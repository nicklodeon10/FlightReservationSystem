package com.cg.frs.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Passenger;
import com.cg.frs.exception.FRSException;
import com.cg.frs.service.ScheduleFlightServiceImpl;
import com.cg.frs.util.DBUtil;

public class BookingDaoImpl implements BookingDao {

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

	public Booking addBooking(Booking booking) {
		String sql = "insert into booking(booking_date, ticket_cost, flight_number, user_id, passenger_count, flag) values(?,?,?,?,?,0)";
		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			ps.setDouble(2, booking.getTicketCost());
			ps.setLong(3, booking.getFlight().getFlight().getFlightNumber().longValue());
			ps.setLong(4, booking.getUserId().longValue());
			ps.setInt(5, booking.getNoOfPassengers());
			ps.executeUpdate();
			BigInteger generatedId = BigInteger.valueOf(0L);
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				generatedId = BigInteger.valueOf(rs.getLong(1));
				myLogger.error("Auto Generated Booking Id: " + generatedId);
				booking.setBookingId(generatedId);
			}
		} catch (SQLException e) {
			myLogger.error(" Error at addBooking Dao method : " + e);
		}
		sql="Insert into passenger(passenger_name, passenger_age, passenger_uin, booking_id, flag) values(?,?,?,?,0)";
		try {
			for(int i=0; i<booking.getNoOfPassengers(); i++) {
				ps=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, booking.getPassengerList().get(i).getPassengerName());
				ps.setInt(2, booking.getPassengerList().get(i).getPassengerAge());
				ps.setLong(3, booking.getPassengerList().get(i).getPassengerUIN().longValue());
				ps.setLong(4, booking.getBookingId().longValue());
				ps.executeUpdate();
				BigInteger generatedId = BigInteger.valueOf(0L);
				rs = ps.getGeneratedKeys();
				if(rs.next()) {
					generatedId=BigInteger.valueOf(rs.getLong(1));
					booking.getPassengerList().get(i).setPnrNumber(generatedId);
				}
			}
		}catch(SQLException exception) {
			myLogger.error(" Error at addBooking Dao method : " + exception);
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at addBooking Dao method : " + e);
				}
			}
		}
		return booking;
	}

	public List<Booking> showBooking() {
		String sql = "select * from booking where flag=0;";
		List<Booking> bookingList = new ArrayList<Booking>();
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Booking booking = new Booking();
				booking.setBookingId(BigInteger.valueOf(rs.getLong("booking_id")));
				booking.setBookingDate(rs.getTimestamp("booking_date").toLocalDateTime());
				booking.setTicketCost(rs.getDouble("ticket_cost"));
				booking.setFlight(new ScheduleFlightServiceImpl().viewScheduleFlights(BigInteger.valueOf(rs.getLong("flight_number"))));
				booking.setUserId(BigInteger.valueOf(rs.getLong("user_id")));
				booking.setNoOfPassengers(rs.getInt("passenger_count"));
				bookingList.add(booking);
			}
		} catch (SQLException e) {
			myLogger.error(" Error at addBooking Dao method : " + e);
		} 
		sql="Select * from passenger where booking_id=? and flag=0;";
		try {
			for(Booking booking: bookingList) {
				List<Passenger> passList=new ArrayList<Passenger>();
				ps=connection.prepareStatement(sql);
				ps.setLong(1, booking.getBookingId().longValue());
				rs=ps.executeQuery();
				while(rs.next()) {
					Passenger passenger=new Passenger();
					passenger.setPnrNumber(BigInteger.valueOf(rs.getLong("pnr_number")));
					passenger.setPassengerName(rs.getString("passenger_name"));
					passenger.setPassengerAge(rs.getInt("passenger_age"));
					passenger.setPassengerUIN(BigInteger.valueOf(rs.getLong("passenger_uin")));
					passList.add(passenger);
				}
				booking.setPassengerList(passList);
			}
		}catch(SQLException exception) {
			myLogger.info(" Error at addBooking Dao method : " + exception);
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at addBooking Dao method : " + e);
				}
			}
		}
		return bookingList;
	}

	public boolean removeBooking(BigInteger bookingId) {
		String sql = "Update booking set flag=1 where booking_id=?;";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, bookingId.longValue());
			ps.executeUpdate();
		} catch (SQLException e) {
			myLogger.error(" Error at delete Booking Dao method : " + e);
		} 
		sql = "Update passenger set flag=1 where booking_id=?;";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, bookingId.longValue());
			ps.executeUpdate();
		} catch (SQLException e) {
			myLogger.error(" Error at delete Booking Dao method : " + e);
		} 
		sql="update passenger set flag=1 where booking_id=?;";
		try {
			ps=connection.prepareStatement(sql);
			ps.setLong(1, bookingId.longValue());
		}catch(SQLException exception) {
			myLogger.error("Error at delete Booking Dao method: " + exception);
		}
		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at delete Booking Dao method : " + e);
				}
			}
		}
		return true;
	}

	@Override
	public Booking updateBooking(Booking booking) {
		String sql="Update passenger set flag=1 where pnr_number=?;";
		try {
			ps=connection.prepareStatement(sql);
			for(Passenger passenger: booking.getPassengerList()) {
				ps.setLong(1, passenger.getPnrNumber().longValue());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			myLogger.error(" Error at delete Booking Dao method : " + e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					myLogger.error(" Error at delete Booking Dao method : " + e);
				}
			}
		}
		return booking;
	}

}
