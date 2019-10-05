package com.cg.frs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Airport;

@Repository("airportDao")
public class AirportDaoImpl implements AirportDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Airport> viewAirport() {
		TypedQuery<Airport> query = entityManager.createQuery("FROM Airport", Airport.class);
		return query.getResultList();
	}

}
