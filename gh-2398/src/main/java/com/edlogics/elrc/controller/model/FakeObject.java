/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.elrc.controller.model;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * @author Christopher Savory
 *
 */
public class FakeObject extends ResourceSupport {

	/**
	 * 
	 */
	public FakeObject() {
		// TODO Auto-generated constructor stub
	}

	public Date getDate() {
		return new Date();
	}

	public DateTime getJodaDate() {
		return DateTime.now();
	}

	@JsonFormat(shape = Shape.STRING)
	public DateTime getJodaDateForceAsString() {
		return DateTime.now();
	}

}
