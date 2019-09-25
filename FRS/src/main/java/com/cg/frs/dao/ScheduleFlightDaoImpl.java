package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;
import com.cg.frs.util.EntityManagerFactoryUtil;
import com.cg.frs.util.EntityTransactionUtil;

public class ScheduleFlightDaoImpl implements ScheduleFlightDao {

	private EntityManagerFactory emf = EntityManagerFactoryUtil.getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tran = EntityTransactionUtil.getTransaction(em);

	@Override
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight) {
		tran.begin();
		em.persist(scheduleflight);
		tran.commit();
		return scheduleflight;
	}

	@Override
	public List<ScheduleFlight> viewScheduleFlight() {
		TypedQuery<ScheduleFlight> query = em.createQuery("FROM ScheduleFlight WHERE scheduleFlightState=true",
				ScheduleFlight.class);
		return query.getResultList();
	}

	@Override
	public ScheduleFlight updateScheduleFlight(ScheduleFlight scheduleFlight) {
		ScheduleFlight updateScheduleFlight = em.find(ScheduleFlight.class, scheduleFlight.getScheduleFlightId());
		Schedule updateSchedule = em.find(Schedule.class, scheduleFlight.getSchedule().getScheduleId());
		tran.begin();
		updateScheduleFlight.setAvailableSeats(scheduleFlight.getAvailableSeats());
		updateScheduleFlight.setTicketCost(scheduleFlight.getTicketCost());
		updateSchedule.setSourceAirport(scheduleFlight.getSchedule().getSourceAirport());
		updateSchedule.setDestinationAirport(scheduleFlight.getSchedule().getDestinationAirport());
		updateSchedule.setArrivalDateTime(scheduleFlight.getSchedule().getArrivalDateTime());
		updateSchedule.setDepartureDateTime(scheduleFlight.getSchedule().getDepartureDateTime());
		tran.commit();
		return scheduleFlight;
	}

	@Override
	public boolean deleteScheduleFlight(BigInteger scheduleFlightId) {
		ScheduleFlight removeScheduleFlight = em.find(ScheduleFlight.class, scheduleFlightId);
		Schedule removeSchedule = em.find(Schedule.class, removeScheduleFlight.getSchedule().getScheduleId());
		tran.begin();
		removeScheduleFlight.setScheduleFlightState(false);
		em.remove(removeSchedule);
		tran.commit();
		return true;
	}

}
