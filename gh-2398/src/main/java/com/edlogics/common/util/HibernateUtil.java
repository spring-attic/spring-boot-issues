/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.common.util;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

/**
 * A set of Hibernate utilities
 * 
 * @author Christopher Savory
 *
 */
public class HibernateUtil {

	/**
	 * Unwraps a proxied entity object.
	 * Useful for EntityLinks which cannot recognize a proxied (layz) entity.
	 * 
	 * @param entity
	 * @return
	 */
	public static <T> T unproxy( T entity ) {
		if ( entity == null ) {
			return null;
		}

		if ( entity instanceof HibernateProxy ) {
			Hibernate.initialize( entity );
			entity = (T) ( (HibernateProxy) entity ).getHibernateLazyInitializer().getImplementation();
		}

		return entity;
	}

}
