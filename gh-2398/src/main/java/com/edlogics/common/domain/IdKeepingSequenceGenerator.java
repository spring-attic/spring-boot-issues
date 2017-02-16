/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.common.domain;

import java.io.Serializable;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.id.SequenceHiLoGenerator;
import org.springframework.data.domain.Persistable;

/**
 * A subclass of {@link SequenceGenerator} that will keep an Id if one is set.
 * This condition will be rare, e.g. Batch Imports
 * 
 * @author Christopher Savory
 *
 */
public class IdKeepingSequenceGenerator extends SequenceHiLoGenerator {

	@Override
	public Serializable generate( SessionImplementor session, Object object ) {
		if ( object instanceof Persistable ) {
			Persistable<? extends Serializable> persistable = (Persistable<?>) object;
			if ( persistable.getId() != null ) {
				return persistable.getId();
			}
		}
		return super.generate( session, object );
	}
}