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

import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Flight;
import com.cg.frs.dto.User;
import com.cg.frs.exception.FRSException;
import com.cg.frs.util.DBUtil;

public class FlightDaoImpl implements FlightDao {

	
	private static EntityManagerFactory emf=Persistence.createEntityManagerFactory("library");
	private static EntityManager em=emf.createEntityManager();
	private static EntityTransaction tran = em.getTransaction();
	
	
	@Override
	public Flight addFlight(Flight flight) {
		// TODO Auto-generated method stub
		tran.begin();
		em.persist(flight);
		tran.commit();
		
		
		
		
		return flight;
	}

	@Override
	public List<Flight> viewFlight() {
		// TODO Auto-generated method stub
		
		Query query=em.createQuery("FROM Flight");
		List<Flight> flightList=query.getResultList();
		
		
		return flightList;
	}

	@Override
	public Flight updateFlight(Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFlight(BigInteger flightId) {
		// TODO Auto-generated method stub
		
		
		tran.begin();
		
		Flight flightRemove=em.find(Booking.class,flightId );
		em.remove(em.merge(flightRemove));
		
		tran.commit();
		
		return false;
	}

	
	
}