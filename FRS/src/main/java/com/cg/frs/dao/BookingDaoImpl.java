package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.cg.frs.dto.Booking;
import com.cg.frs.dto.Passenger;
import com.cg.frs.util.EntityManagerFactoryUtil;

public class BookingDaoImpl implements BookingDao {

	private EntityManagerFactory emf = EntityManagerFactoryUtil.getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tran = em.getTransaction();

	@Override
	public Booking addBooking(Booking booking) {
		tran.begin();
		em.persist(booking);
		tran.commit();
		return booking;
	}

	@Override
	public List<Booking> showBooking() {
		TypedQuery<Booking> query = em.createQuery("FROM Booking WHERE bookingState=true", Booking.class);
		return query.getResultList();
	}

	@Override
	public boolean removeBooking(BigInteger bookingId) {
		Booking bookingRemove = em.find(Booking.class, bookingId);
		List<Passenger> removePassengerList=bookingRemove.getPassengerList();
		tran.begin();
		bookingRemove.setBookingState(false);
		for(Passenger removePassenger: removePassengerList) {
			removePassenger.setPassengerState(false);
		}
		tran.commit();
		return false;
	}

}
