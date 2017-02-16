/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.elrc.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	protected Logger logger = LoggerFactory.getLogger( getClass() );

	@Value("${app.timezone}")
	String elrcTimezone;

	/**
	 * Home page.
	 *
	 * @return The view name (an HTML page with Thymeleaf markup).
	 */
	@RequestMapping({ "/", "/home" })
	public String home( HttpServletRequest request, HttpServletResponse response, Locale locale ) {
		return "index";
	}
}
