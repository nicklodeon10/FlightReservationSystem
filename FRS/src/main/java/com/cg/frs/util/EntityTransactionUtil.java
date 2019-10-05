package com.cg.frs.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EntityTransactionUtil {

	private static EntityTransaction transaction;
	
	public EntityTransactionUtil() {}

	public static EntityTransaction getTransaction(EntityManager em) {
		if(transaction==null) {
			transaction=em.getTransaction();
		}
		return transaction;
	}
	
}
