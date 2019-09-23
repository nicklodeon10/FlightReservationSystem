package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.cg.frs.dto.Flight;
import com.cg.frs.util.EntityManagerFactoryUtil;

public class FlightDaoImpl implements FlightDao {

	private EntityManagerFactory emf = EntityManagerFactoryUtil.getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tran = em.getTransaction();

	@Override
	public Flight addFlight(Flight flight) {
		tran.begin();
		em.persist(flight);
		tran.commit();
		return flight;
	}

	@Override
	public List<Flight> viewFlight() {
		TypedQuery<Flight> query = em.createQuery("FROM Flight WHERE flightState=true", Flight.class);
		return query.getResultList();
	}

	@Override
	public Flight updateFlight(Flight flight) {
		Flight updateFlight=em.find(Flight.class, flight.getFlightNumber());
		tran.begin();
		updateFlight.setCarrierName(flight.getCarrierName());
		updateFlight.setFlightModel(flight.getFlightModel());
		updateFlight.setSeatCapacity(flight.getSeatCapacity());
		tran.commit();
		return flight;
	}

	@Override
	public boolean deleteFlight(BigInteger flightId) {
		Flight removeFlight=em.find(Flight.class, flightId);
		tran.begin();
		removeFlight.setFlightState(false);
		tran.commit();
		return false;
	}
}