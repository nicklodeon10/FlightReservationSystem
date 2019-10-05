package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Passenger;
import com.cg.frs.dto.ScheduleFlight;

@Repository("bookingDao")
public class BookingDaoImpl implements BookingDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	ScheduleFlightDao scheduleFlightDao;

	@Override
	public Booking addBooking(Booking booking) {
		ScheduleFlight scheduleFlight=entityManager.find(ScheduleFlight.class, booking.getScheduleFlight().getScheduleFlightId());
		entityManager.persist(booking);
		scheduleFlight.setAvailableSeats(scheduleFlight.getAvailableSeats()-booking.getPassengerCount());
		return booking;
	}

	@Override
	public List<Booking> showBooking() {
		TypedQuery<Booking> query = entityManager.createQuery("FROM Booking WHERE bookingState=true", Booking.class);
		return query.getResultList();
	}

	@Override
	public boolean removeBooking(BigInteger bookingId) {
		Booking bookingRemove = entityManager.find(Booking.class, bookingId);
		ScheduleFlight scheduleFlight=entityManager.find(ScheduleFlight.class, bookingRemove.getScheduleFlight().getScheduleFlightId());
		List<Passenger> removePassengerList=bookingRemove.getPassengerList();
		scheduleFlight.setAvailableSeats(scheduleFlight.getAvailableSeats()+bookingRemove.getPassengerCount());
		bookingRemove.setBookingState(false);
		for(Passenger removePassenger: removePassengerList) {
			removePassenger.setPassengerState(false);
		}
		return false;
	}

}
