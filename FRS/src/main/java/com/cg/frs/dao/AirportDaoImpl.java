package com.cg.frs.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.cg.frs.dto.Airport;
import com.cg.frs.util.EntityManagerFactoryUtil;

public class AirportDaoImpl implements AirportDao {

	private EntityManagerFactory emf = EntityManagerFactoryUtil.getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();

	@Override
	public List<Airport> viewAirport() {
		TypedQuery<Airport> query = em.createQuery("FROM Airport", Airport.class);
		return query.getResultList();
	}

}
