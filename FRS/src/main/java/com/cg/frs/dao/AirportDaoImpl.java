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
import com.cg.frs.dto.Booking;
import com.cg.frs.exception.FRSException;
import com.cg.frs.util.DBUtil;

public class AirportDaoImpl implements AirportDao {

	private static EntityManagerFactory emf=Persistence.createEntityManagerFactory("library");
	private static EntityManager em=emf.createEntityManager();
	@Override
	public List<Airport> viewAirport() {
		// TODO Auto-generated method stub
		
		Query query=em.createQuery("FROM Airport");
		List<Airport> airportList=query.getResultList();
		
		return airportList;
	}

	

}
