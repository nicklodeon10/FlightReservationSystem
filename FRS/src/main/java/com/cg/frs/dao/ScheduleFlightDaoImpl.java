package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.util.EntityManagerFactoryUtil;

public class ScheduleFlightDaoImpl implements ScheduleFlightDao {

	private static EntityManagerFactory emf = EntityManagerFactoryUtil.getEntityManagerFactory();
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
		TypedQuery<ScheduleFlight> query = em.createQuery("FROM ScheduleFlight WHERE scheduleFlightState=true;", ScheduleFlight.class);
		return query.getResultList();
	}

	@Override
	public ScheduleFlight updateScheduleFlight(ScheduleFlight scheduleFlight) {
		ScheduleFlight updateScheduleFlight=em.find(ScheduleFlight.class, scheduleFlight.getScheduleFlightId());
		tran.begin();
		updateScheduleFlight.setAvailableSeats(scheduleFlight.getAvailableSeats());
		updateScheduleFlight.setTicketCost(scheduleFlight.getTicketCost());
		updateScheduleFlight.setSchedule(scheduleFlight.getSchedule());
		tran.commit();
		return scheduleFlight;
	}

	@Override
	public boolean deleteScheduleFlight(BigInteger scheduleFlightId) {
		ScheduleFlight updateScheduleFlight=em.find(ScheduleFlight.class, scheduleFlightId);
		tran.begin();
		updateScheduleFlight.setScheduleFlightState(false);
		tran.commit();
		return true;
	}

}
