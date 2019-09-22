package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.frs.dto.Flight;

public class FlightDaoImpl implements FlightDao {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
	private static EntityManager em = emf.createEntityManager();
	private static EntityTransaction tran = em.getTransaction();

	@Override
	public Flight addFlight(Flight flight) {
		tran.begin();
		em.persist(flight);
		tran.commit();
		return flight;
	}

	@Override
	public List<Flight> viewFlight() {
		TypedQuery<Flight> query = em.createQuery("FROM Flight", Flight.class);
		List<Flight> flightList = query.getResultList();
		return flightList;
	}

	@Override
	public Flight updateFlight(Flight flight) {
		return null;
	}

	@Override
	public boolean deleteFlight(BigInteger flightId) {
		tran.begin();
		tran.commit();
		return false;
	}
}