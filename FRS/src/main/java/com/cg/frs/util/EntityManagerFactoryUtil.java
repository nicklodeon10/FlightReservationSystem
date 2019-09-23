package com.cg.frs.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryUtil {
	
	private static EntityManagerFactory entityManagerFactory;
	
	private EntityManagerFactoryUtil() {}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("frs");
		}
		return entityManagerFactory;
	}

	public static void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		EntityManagerFactoryUtil.entityManagerFactory = entityManagerFactory;
	}
	
}
