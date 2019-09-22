package com.cg.frs.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.frs.dto.User;
import com.cg.frs.exception.FRSException;
import com.cg.frs.util.DBUtil;
import com.cg.library.dao.EntityManager;
import com.cg.library.dao.EntityManagerFactory;
import com.cg.library.dao.EntityTransaction;
import com.cg.library.dao.Query;
import com.cg.library.dto.Author;

public class UserDaoImpl implements UserDao {

	private static EntityManagerFactory emf=Persistence.createEntityManagerFactory("library");
	private static EntityManager em=emf.createEntityManager();
	private static EntityTransaction tran = em.getTransaction();
	
	
	
	
	
	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		
		tran.begin();
		em.persist(user);
		tran.commit();
		
		return user;
	}

	@Override
	public List<User> showUser() {
		// TODO Auto-generated method stub
		
		Query query=em.createQuery("FROM User");
		List<User> userList=query.getResultList();
		return userList;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		
		User userUpdate=em.find(User.class,user.getUserId() );
		tran.begin();
		em.remove(userUpdate);
		userUpdate.setAuthorId(authorId);
		System.out.println("Enter new name");
		String authorName = scan.next();
		authorUpdate.setAuthorName(authorName);
		
		em.persist(authorUpdate);
		tran.commit();
		
		
		return null;
	}

	@Override
	public boolean removeUser(BigInteger user) {
		// TODO Auto-generated method stub
		
        tran.begin();
		
		User userRemove=em.find(User.class,user );
		em.remove(em.merge(userRemove));
		
		tran.commit();
		
		return false;
	}
	
	
}


		