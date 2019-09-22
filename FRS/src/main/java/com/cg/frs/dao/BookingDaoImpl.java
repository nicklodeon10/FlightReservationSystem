package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.frs.dto.Booking;

public class BookingDaoImpl implements BookingDao {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
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
		TypedQuery<Booking> query = em.createQuery("FROM Booking", Booking.class);
		List<Booking> bookingList = query.getResultList();
		return bookingList;
	}

	@Override
	public boolean removeBooking(BigInteger bookingId) {
		tran.begin();
		Booking bookingRemove = em.find(Booking.class, bookingId);
		em.remove(em.merge(bookingRemove));
		tran.commit();
		return false;
	}

	@Override
	public Booking updateBooking(Booking booking) {
		return null;
	}

}
