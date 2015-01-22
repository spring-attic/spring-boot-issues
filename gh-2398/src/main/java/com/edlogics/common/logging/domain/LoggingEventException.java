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
@Table(name = "logging_event_exception")
public class LoggingEventException implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "i")
	private Integer id;

	@Column(name = "trace_line")
	private String traceLine;

	@ManyToOne
	@Id
	@JoinColumn(name = "event_id", referencedColumnName = "event_id")
	private LoggingEvent loggingEvent;

	public LoggingEventException() {
		super();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 17, 37 )
				.append( id )
				.append( loggingEvent )
				.toHashCode();
	}

	@Override
	public boolean equals( Object obj ) {
		if ( !( obj instanceof LoggingEventException ) ) {
			return false;
		}
		LoggingEventException ua = (LoggingEventException) obj;
		return new EqualsBuilder()
				.append( id, ua.id )
				.append( loggingEvent, ua.loggingEvent )
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder( this )
				.append( "id", id )
				.append( "traceLine", traceLine )
				.append( "loggingEvent", loggingEvent )
				.toString();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( Integer id ) {
		this.id = id;
	}

	/**
	 * @return the traceLine
	 */
	public String getTraceLine() {
		return traceLine;
	}

	/**
	 * @param traceLine the traceLine to set
	 */
	public void setTraceLine( String traceLine ) {
		this.traceLine = traceLine;
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