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

	private static EntityManagerFactory emf = EntityManagerFactoryUtil.getEntityManagerFactory();
	private static EntityManager em = emf.createEntityManager();
	private static EntityTransaction tran = em.getTransaction();

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
	public Booking updateBooking(Booking booking) {
		Booking updateBooking = em.find(Booking.class, booking.getBookingId());
		List<Passenger> removedPassengerList = updateBooking.getPassengerList();
		List<Passenger> newPassengerList = booking.getPassengerList();
		for (Passenger passenger : newPassengerList) {
			removedPassengerList.remove(passenger);
		}
		tran.begin();
		updateBooking.setNoOfPassengers(booking.getNoOfPassengers());
		updateBooking.setPassengerList(booking.getPassengerList());
		updateBooking.setTicketCost(booking.getTicketCost());
		for (Passenger passenger : removedPassengerList) {
			passenger.setPassengerState(false);
		}
		tran.commit();
		return booking;
	}

	@Override
	public boolean removeBooking(BigInteger bookingId) {
		tran.begin();
		Booking bookingRemove = em.find(Booking.class, bookingId);
		bookingRemove.setBookingState(false);
		tran.commit();
		return false;
	}

}
