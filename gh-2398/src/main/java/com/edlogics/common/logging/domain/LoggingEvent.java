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
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author jlanpher
 *
 */
@Entity
@Table(name = "logging_event")
public class LoggingEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "event_id")
	private Long id;

	@Column(name = "timestmp")
	private Long timeStamp;

	@Column(name = "formatted_message")
	private String formattedMessage;

	@Column(name = "logger_name")
	private String loggerName;

	@Column(name = "level_string")
	private String levelString;

	@Column(name = "thread_name")
	private String threadName;

	@Column(name = "reference_flag")
	private Integer referenceFlag;

	@Column(name = "arg0")
	private String arg0;

	@Column(name = "arg1")
	private String arg1;

	@Column(name = "arg2")
	private String arg2;

	@Column(name = "arg3")
	private String arg3;

	@Column(name = "caller_filename")
	private String callerFileName;

	@Column(name = "caller_class")
	private String callerClass;

	@Column(name = "caller_method")
	private String callerMethod;

	@Column(name = "caller_line")
	private String callerLine;

	@OneToMany(mappedBy = "loggingEvent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<LoggingEventException> loggingEventExceptions;

	@OneToMany(mappedBy = "loggingEvent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<LoggingEventProperty> loggingEventProperties;

	public LoggingEvent() {
		super();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder( 17, 37 )
				.append( id )
				.toHashCode();
	}

	@Override
	public boolean equals( Object obj ) {
		if ( !( obj instanceof LoggingEvent ) ) {
			return false;
		}
		LoggingEvent ua = (LoggingEvent) obj;
		return new EqualsBuilder()
				.append( id, ua.id )
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder( this )
				.append( "id", getId() )
				.toString();
	}

	@Transient
	public Date getDateOccurred() {
		return new Date( this.timeStamp );
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( Long id ) {
		this.id = id;
	}

	/**
	 * @return the timeStamp
	 */
	public Long getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp( Long timeStamp ) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the formattedMessage
	 */
	public String getFormattedMessage() {
		return formattedMessage;
	}

	/**
	 * @param formattedMessage the formattedMessage to set
	 */
	public void setFormattedMessage( String formattedMessage ) {
		this.formattedMessage = formattedMessage;
	}

	/**
	 * @return the loggerName
	 */
	public String getLoggerName() {
		return loggerName;
	}

	/**
	 * @param loggerName the loggerName to set
	 */
	public void setLoggerName( String loggerName ) {
		this.loggerName = loggerName;
	}

	/**
	 * @return the levelString
	 */
	public String getLevelString() {
		return levelString;
	}

	/**
	 * @param levelString the levelString to set
	 */
	public void setLevelString( String levelString ) {
		this.levelString = levelString;
	}

	/**
	 * @return the threadName
	 */
	public String getThreadName() {
		return threadName;
	}

	/**
	 * @param threadName the threadName to set
	 */
	public void setThreadName( String threadName ) {
		this.threadName = threadName;
	}

	/**
	 * @return the referenceFlag
	 */
	public Integer getReferenceFlag() {
		return referenceFlag;
	}

	/**
	 * @param referenceFlag the referenceFlag to set
	 */
	public void setReferenceFlag( Integer referenceFlag ) {
		this.referenceFlag = referenceFlag;
	}

	/**
	 * @return the arg0
	 */
	public String getArg0() {
		return arg0;
	}

	/**
	 * @param arg0 the arg0 to set
	 */
	public void setArg0( String arg0 ) {
		this.arg0 = arg0;
	}

	/**
	 * @return the arg1
	 */
	public String getArg1() {
		return arg1;
	}

	/**
	 * @param arg1 the arg1 to set
	 */
	public void setArg1( String arg1 ) {
		this.arg1 = arg1;
	}

	/**
	 * @return the arg2
	 */
	public String getArg2() {
		return arg2;
	}

	/**
	 * @param arg2 the arg2 to set
	 */
	public void setArg2( String arg2 ) {
		this.arg2 = arg2;
	}

	/**
	 * @return the arg3
	 */
	public String getArg3() {
		return arg3;
	}

	/**
	 * @param arg3 the arg3 to set
	 */
	public void setArg3( String arg3 ) {
		this.arg3 = arg3;
	}

	/**
	 * @return the callerFileName
	 */
	public String getCallerFileName() {
		return callerFileName;
	}

	/**
	 * @param callerFileName the callerFileName to set
	 */
	public void setCallerFileName( String callerFileName ) {
		this.callerFileName = callerFileName;
	}

	/**
	 * @return the callerClass
	 */
	public String getCallerClass() {
		return callerClass;
	}

	/**
	 * @param callerClass the callerClass to set
	 */
	public void setCallerClass( String callerClass ) {
		this.callerClass = callerClass;
	}

	/**
	 * @return the callerMethod
	 */
	public String getCallerMethod() {
		return callerMethod;
	}

	/**
	 * @param callerMethod the callerMethod to set
	 */
	public void setCallerMethod( String callerMethod ) {
		this.callerMethod = callerMethod;
	}

	/**
	 * @return the callerLine
	 */
	public String getCallerLine() {
		return callerLine;
	}

	/**
	 * @param callerLine the callerLine to set
	 */
	public void setCallerLine( String callerLine ) {
		this.callerLine = callerLine;
	}

	/**
	 * @return the loggingEventExceptions
	 */
	public List<LoggingEventException> getLoggingEventExceptions() {
		return loggingEventExceptions;
	}

	/**
	 * @param loggingEventExceptions the loggingEventExceptions to set
	 */
	public void setLoggingEventExceptions( List<LoggingEventException> loggingEventExceptions ) {
		this.loggingEventExceptions = loggingEventExceptions;
	}

	/**
	 * @return the loggingEventProperties
	 */
	public List<LoggingEventProperty> getLoggingEventProperties() {
		return loggingEventProperties;
	}

	/**
	 * @param loggingEventProperties the loggingEventProperties to set
	 */
	public void setLoggingEventProperties( List<LoggingEventProperty> loggingEventProperties ) {
		this.loggingEventProperties = loggingEventProperties;
	}
}