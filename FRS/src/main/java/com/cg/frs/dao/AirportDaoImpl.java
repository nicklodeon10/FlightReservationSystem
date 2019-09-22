package com.cg.frs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.frs.dto.Airport;

public class AirportDaoImpl implements AirportDao {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
	private static EntityManager em = emf.createEntityManager();

	@Override
	public List<Airport> viewAirport() {
		TypedQuery<Airport> query = em.createQuery("FROM Airport", Airport.class);
		List<Airport> airportList = query.getResultList();
		return airportList;
	}

}
