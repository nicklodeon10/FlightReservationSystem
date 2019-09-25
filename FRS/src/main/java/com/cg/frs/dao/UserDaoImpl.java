package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.cg.frs.dto.User;
import com.cg.frs.util.EntityManagerFactoryUtil;
import com.cg.frs.util.EntityTransactionUtil;

public class UserDaoImpl implements UserDao {

	private EntityManagerFactory emf = EntityManagerFactoryUtil.getEntityManagerFactory();
	private EntityManager em = emf.createEntityManager();
	private EntityTransaction tran = EntityTransactionUtil.getTransaction(em);

	@Override
	public User addUser(User user) {
		tran.begin();
		em.persist(user);
		tran.commit();
		return user;
	}

	@Override
	public List<User> showUser() {
		TypedQuery<User> query = em.createQuery("FROM User WHERE userState=true", User.class);
		return query.getResultList();
	}

	public User showUser(BigInteger userId) {
		return em.find(User.class, userId);
	}

	public User updateUser(User user) {
		User userUpdate = em.find(User.class, user.getUserId());
		tran.begin();
		userUpdate.setUserName(user.getUserName());
		userUpdate.setUserPassword(user.getUserPassword());
		userUpdate.setUserPhone(user.getUserPhone());
		userUpdate.setEmail(user.getEmail());
		tran.commit();
		return null;
	}

	@Override
	public boolean removeUser(BigInteger userId) {
		User userUpdate = em.find(User.class, userId);
		tran.begin();
		userUpdate.setUserState(false);
		tran.commit();
		return true;
	}
}
