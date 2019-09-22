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

import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Flight;
import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.dto.User;
import com.cg.frs.exception.FRSException;
import com.cg.frs.service.AirportServiceImpl;
import com.cg.frs.service.FlightServiceImpl;
import com.cg.frs.util.DBUtil;

public class ScheduleFlightDaoImpl implements ScheduleFlightDao {

	private static EntityManagerFactory emf=Persistence.createEntityManagerFactory("library");
	private static EntityManager em=emf.createEntityManager();
	private static EntityTransaction tran = em.getTransaction();
	
	
	@Override
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight) {
		// TODO Auto-generated method stub
		
		tran.begin();
		em.persist(scheduleflight);
		tran.commit();
		
		
		return scheduleflight;
	}

	@Override
	public List<ScheduleFlight> viewScheduleFlight() {
		// TODO Auto-generated method stub
		
		Query query=em.createQuery("FROM ScheduleFlight");
		List<ScheduleFlight> scheduleFlightList=query.getResultList();
		
		
		
		return scheduleFlightList;
	}

	@Override
	public ScheduleFlight updateScheduleFlight(ScheduleFlight scheduleflight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteScheduleFlight(BigInteger flightId) {
		// TODO Auto-generated method stub
		
tran.begin();
		
ScheduleFlight scheduleFlightRemove=em.find(Booking.class,flightId );
		em.remove(em.merge(scheduleFlight));
		
		tran.commit();
		
		return false;
	}

	
}
