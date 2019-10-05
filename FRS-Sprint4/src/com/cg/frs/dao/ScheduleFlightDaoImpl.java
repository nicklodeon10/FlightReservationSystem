package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Schedule;
import com.cg.frs.dto.ScheduleFlight;

@Repository("scheduleFlightDao")
public class ScheduleFlightDaoImpl implements ScheduleFlightDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ScheduleFlight addScheduleFlight(ScheduleFlight scheduleflight) {
		entityManager.persist(scheduleflight);
		return scheduleflight;
	}

	@Override
	public List<ScheduleFlight> viewScheduleFlight() {
		TypedQuery<ScheduleFlight> query = entityManager.createQuery("FROM ScheduleFlight WHERE scheduleFlightState=true",
				ScheduleFlight.class);
		return query.getResultList();
	}

	@Override
	public ScheduleFlight updateScheduleFlight(ScheduleFlight scheduleFlight) {
		ScheduleFlight updateScheduleFlight = entityManager.find(ScheduleFlight.class, scheduleFlight.getScheduleFlightId());
		Schedule updateSchedule = entityManager.find(Schedule.class, scheduleFlight.getSchedule().getScheduleId());
		updateScheduleFlight.setAvailableSeats(scheduleFlight.getAvailableSeats());
		updateScheduleFlight.setTicketCost(scheduleFlight.getTicketCost());
		updateSchedule.setSourceAirport(scheduleFlight.getSchedule().getSourceAirport());
		updateSchedule.setDestinationAirport(scheduleFlight.getSchedule().getDestinationAirport());
		updateSchedule.setArrivalDateTime(scheduleFlight.getSchedule().getArrivalDateTime());
		updateSchedule.setDepartureDateTime(scheduleFlight.getSchedule().getDepartureDateTime());
		return scheduleFlight;
	}

	@Override
	public boolean deleteScheduleFlight(BigInteger scheduleFlightId) {
		ScheduleFlight removeScheduleFlight = entityManager.find(ScheduleFlight.class, scheduleFlightId);
		Schedule removeSchedule = entityManager.find(Schedule.class, removeScheduleFlight.getSchedule().getScheduleId());
		removeScheduleFlight.setScheduleFlightState(false);
		entityManager.remove(removeSchedule);
		return true;
	}

}
