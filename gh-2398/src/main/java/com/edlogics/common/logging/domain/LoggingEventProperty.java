/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.common.logging.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jlanpher
 *
 */
@Entity
@Table(name = "logging_event_property")
public class LoggingEventProperty implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "mapped_key", nullable = false)
	private String mappedKey;

	@Column(name = "mapped_value")
	private String mappedValue;

	@ManyToOne
	@Id
	@JoinColumn(name = "event_id", referencedColumnName = "event_id")
	private LoggingEvent loggingEvent;

	public LoggingEventProperty() {
		super();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 17, 37 )
				.append( mappedKey )
				.append( loggingEvent )
				.toHashCode();
	}

	@Override
	public boolean equals( Object obj ) {
		if ( !( obj instanceof LoggingEventProperty ) ) {
			return false;
		}
		LoggingEventProperty ua = (LoggingEventProperty) obj;
		return new EqualsBuilder()
				.append( mappedKey, ua.mappedKey )
				.append( loggingEvent, ua.loggingEvent )
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder( this )
				.append( "mappedKey", mappedKey )
				.append( "mappedValue", mappedValue )
				.append( "loggingEvent", loggingEvent )
				.toString();
	}

	/**
	 * @return the mappedKey
	 */
	public String getMappedKey() {
		return mappedKey;
	}

	/**
	 * @param mappedKey the mappedKey to set
	 */
	public void setMappedKey( String mappedKey ) {
		this.mappedKey = mappedKey;
	}

	/**
	 * @return the mappedValue
	 */
	public String getMappedValue() {
		return mappedValue;
	}

	/**
	 * @param mappedValue the mappedValue to set
	 */
	public void setMappedValue( String mappedValue ) {
		this.mappedValue = mappedValue;
	}

	/**
	 * @return the loggingEvent
	 */
	public LoggingEvent getLoggingEvent() {
		return loggingEvent;
	}

	/**
	 * @param loggingEvent the loggingEvent to set
	 */
	public void setLoggingEvent( LoggingEvent loggingEvent ) {
		this.loggingEvent = loggingEvent;
	}
}