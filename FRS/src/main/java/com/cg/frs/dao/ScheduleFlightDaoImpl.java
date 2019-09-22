package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.frs.dto.ScheduleFlight;

public class ScheduleFlightDaoImpl implements ScheduleFlightDao {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
	private static EntityManager em = emf.createEntityManager();
	private static EntityTransaction tran = em.getTransaction();

	@Override
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight) {
		tran.begin();
		em.persist(scheduleflight);
		tran.commit();
		return scheduleflight;
	}

	@Override
	public List<ScheduleFlight> viewScheduleFlight() {
		TypedQuery<ScheduleFlight> query = em.createQuery("FROM ScheduleFlight", ScheduleFlight.class);
		List<ScheduleFlight> scheduleFlightList = query.getResultList();
		return scheduleFlightList;
	}

	@Override
	public ScheduleFlight updateScheduleFlight(ScheduleFlight scheduleflight) {
		return null;
	}

	@Override
	public boolean deleteScheduleFlight(BigInteger flightId) {
		tran.begin();
		tran.commit();
		return true;
	}

}
