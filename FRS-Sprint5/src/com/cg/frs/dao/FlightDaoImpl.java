package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Flight;

@Repository("flightDao")
public class FlightDaoImpl implements FlightDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Flight addFlight(Flight flight) {
		entityManager.persist(flight);
		return flight;
	}

	@Override
	public List<Flight> viewFlight() {
		TypedQuery<Flight> query = entityManager.createQuery("FROM Flight WHERE flightState=true", Flight.class);
		return query.getResultList();
	}

	@Override
	public Flight updateFlight(Flight flight) {
		Flight updateFlight=entityManager.find(Flight.class, flight.getFlightNumber());
		updateFlight.setCarrierName(flight.getCarrierName());
		updateFlight.setFlightModel(flight.getFlightModel());
		updateFlight.setSeatCapacity(flight.getSeatCapacity());
		return flight;
	}

	@Override
	public boolean deleteFlight(BigInteger flightId) {
		Flight removeFlight=entityManager.find(Flight.class, flightId);
		removeFlight.setFlightState(false);
		return false;
	}
}