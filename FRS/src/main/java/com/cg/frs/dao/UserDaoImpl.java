package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.frs.dto.User;

public class UserDaoImpl implements UserDao {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
	private static EntityManager em = emf.createEntityManager();
	private static EntityTransaction tran = em.getTransaction();

	@Override
	public User addUser(User user) {
		tran.begin();
		em.persist(user);
		tran.commit();
		return user;
	}

	@Override
	public List<User> showUser() {
		TypedQuery<User> query = em.createQuery("FROM User", User.class);
		List<User> userList = query.getResultList();
		return userList;
	}

	public User showUser(BigInteger userId) {
		return em.find(User.class, userId);
	}

	public User updateUser(User user) {
		User userUpdate = em.find(User.class, user.getUserId());
		tran.begin();
		em.remove(userUpdate);
		userUpdate.setUserName(user.getUserName());
		userUpdate.setUserPassword(user.getUserPassword());
		userUpdate.setUserPhone(user.getUserPhone());
		userUpdate.setEmail(user.getEmail());
		tran.commit();
		return null;
	}

	@Override
	public boolean removeUser(BigInteger user) {
		tran.begin();
		em.remove(em.find(User.class, user));
		tran.commit();
		return true;
	}
}
