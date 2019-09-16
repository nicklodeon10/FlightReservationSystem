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
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;

import com.cg.frs.dto.Booking;
import com.cg.frs.exception.FRSException;
import com.cg.frs.util.DBUtil;

public class BookingDaoImpl implements BookingDao {

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

	public Booking addBooking(Booking booking) {
		String sql = "insert into booking(booking_date, ticket_cost, flight_number, user_id, passenger_count) values(?,?,?,?,?)";
		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			ps.setDouble(2, booking.getTicketCost());
			ps.setLong(3, booking.getFlight().getFlight().getFlightNumber().longValue());
			ps.setLong(5, booking.getUserId().longValue());
			ps.setInt(5, booking.getNoOfPassengers());
			ps.executeUpdate();
			BigInteger generatedId = BigInteger.valueOf(0L);
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				generatedId = BigInteger.valueOf(rs.getLong(1));
				System.out.println("Auto Generated Booking Id: " + generatedId);
				booking.setBookingId(generatedId);
			}
		} catch (SQLException e) {
			System.out.println(" Error at addBooking Dao method : " + e);
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
			System.out.println(" Error at addBooking Dao method : " + exception);
		}finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at addBooking Dao method : " + e);
				}
			}
		}
		return booking;
	}

	public List<Booking> showBooking() {
		String sql = "select * from booking";
		List<Booking> bookingList = new ArrayList<Booking>();
		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			rs = ps.executeQuery();
			while (rs.next()) {
				// create booking obj
				Booking booking = new Booking();
				// get the value from rs and set to booking obj
				booking.setBookingId(BigInteger.valueOf(rs.getLong(1)));
				booking.setBookingDate(rs.getString("booking_date"));
				booking.setPassengerName(rs.getString("passenger_name"));
				booking.setTicketCost(rs.getDouble("ticket_cost"));
				booking.getFlight().setFlightNumber(BigInteger.valueOf(rs.getLong("flight_number")));
				booking.setUserId(BigInteger.valueOf(rs.getLong("user_id")));
				booking.setNoOfPassengers(rs.getInt("No of Passengers"));
				// add the booking obj to bookingList
				bookingList.add(booking);

			}
		} catch (SQLException e) {
			System.out.println(" Error at addBooking Dao method : " + e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at addBooking Dao method : " + e);
				}
			}
		}
		return bookingList;
	}

	public boolean removeBooking(BigInteger bookingId) {
		String sql = "Update booking set flag=1 where ";
		try {
			ps = connection.prepareStatement(sql);
			ps.setLong(1, bookingId.longValue());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(" Error at delete Booking Dao method : " + e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(" Error at delete Booking Dao method : " + e);
				}
			}
		}
		return true;
	}
}
