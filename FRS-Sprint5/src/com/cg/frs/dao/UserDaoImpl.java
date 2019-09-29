package com.cg.frs.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.frs.dto.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User addUser(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public List<User> showUser() {
		TypedQuery<User> query = entityManager.createQuery("FROM User WHERE userState=true", User.class);
		return query.getResultList();
	}

	public User showUser(BigInteger userId) {
		return entityManager.find(User.class, userId);
	}

	public User updateUser(User user) {
		User userUpdate = entityManager.find(User.class, user.getUserId());
		userUpdate.setUserName(user.getUserName());
		userUpdate.setUserPassword(user.getUserPassword());
		userUpdate.setUserPhone(user.getUserPhone());
		userUpdate.setEmail(user.getEmail());
		return user;
	}

	@Override
	public boolean removeUser(BigInteger userId) {
		User userUpdate = entityManager.find(User.class, userId);
		userUpdate.setUserState(false);
		return true;
	}
}
