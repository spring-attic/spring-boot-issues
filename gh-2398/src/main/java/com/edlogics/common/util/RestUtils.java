/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.common.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * @author jlanpher
 *
 */
public class RestUtils {

	private RestUtils() {
		super();
	}

	@SuppressWarnings("unchecked")
	public static HttpHeaders generateHttpHeaders( MediaType... mediaTypes ) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept( Arrays.asList( mediaTypes ) );
		return headers;
	}
}