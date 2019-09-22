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
import com.cg.frs.dto.User;
import com.cg.frs.exception.FRSException;
import com.cg.frs.service.ScheduleFlightServiceImpl;
import com.cg.frs.util.DBUtil;

public class BookingDaoImpl implements BookingDao {

	private static EntityManagerFactory emf=Persistence.createEntityManagerFactory("library");
	private static EntityManager em=emf.createEntityManager();
	private static EntityTransaction tran = em.getTransaction();
	
	@Override
	public Booking addBooking(Booking booking) {
		// TODO Auto-generated method stub
		

		tran.begin();
		em.persist(booking);
		tran.commit();
		
		
		return booking;
	}

	@Override
	public List<Booking> showBooking() {
		// TODO Auto-generated method stub
		
		Query query=em.createQuery("FROM Booking");
		List<Booking> bookingList=query.getResultList();
		
		return bookingList;
	}

	@Override
	public boolean removeBooking(BigInteger bookingId) {
		// TODO Auto-generated method stub
		 tran.begin();
			
		 Booking bookingRemove=em.find(Booking.class,bookingId );
			em.remove(em.merge(bookingRemove));
			
			tran.commit();
		return false;
	}

	@Override
	public Booking updateBooking(Booking booking) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
