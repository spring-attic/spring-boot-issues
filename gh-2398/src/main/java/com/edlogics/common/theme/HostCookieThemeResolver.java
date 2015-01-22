/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.common.theme;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.util.WebUtils;

/**
 * Extend CookieThemeResolver with the opportunity to resolve the theme from the host
 * contained in the HttpServletRequest. This requires Spring themes to be named the same
 * as the host name (in reverse), such as "com/edlogics/demo".
 *
 * If the subdomain contains a "-" then the subdomain will be split and the first part taken
 * for the theme. So, halo-latest.edlogics.com would become com/edlogics/halo.
 */
public class HostCookieThemeResolver extends CookieThemeResolver {

	private static final Logger LOG = LoggerFactory.getLogger( HostCookieThemeResolver.class );

	@Override
	public String resolveThemeName( HttpServletRequest request ) {

		// Check request for preparsed or preset theme (copied from original Spring code).
		String attr = (String) request.getAttribute( THEME_REQUEST_ATTRIBUTE_NAME );
		LOG.debug( "Theme param (" + THEME_REQUEST_ATTRIBUTE_NAME + " is: " + attr );
		if ( attr != null ) {
			return attr;
		}

		// Retrieve cookie value from request (copied from original Spring code).
		String cookieName = getCookieName();
		Cookie cookie = WebUtils.getCookie( request, cookieName );
		LOG.debug( "Theme cookie (" + cookieName + " is: " + cookie );
		if ( cookie != null ) {
			LOG.debug( "Cookie is: " + cookie.getValue() );
			return cookie.getValue();
		}

		// Retrieve host value from request.
		try {
			URL url = new URL( ( request.getRequestURL() ).toString() );
			if ( url.getHost() != null && !InetAddressValidator.getInstance().isValidInet4Address( url.getHost() ) ) {
				LOG.debug( "Host is: " + url.getHost() );
				// Convert the domain name to a reference to the theme message resource
				// Reverse url parts (i.e. change demo.edlogics.com to com/edlogics/demo)
				String[] parts = url.getHost().split( "\\." );

				StringBuffer host = new StringBuffer();
				for ( int i = parts.length - 1; i >= 0; i-- ) {
					String part = parts[i];
					// If (sub)domain name contains "-", take the first part to get the common theme
					if ( i == 0 ) {
						part = part.split( "-" )[0];
					}
					// Append with slashes
					host.append( "/" ).append( part );
				}
				// Chop off first / char
				String theme = host.toString().substring( 1 );
				LOG.debug( "Theme (from host) is: " + theme );
				return theme;
			}
			else {
				LOG.error( "Host is null or IP Address in HttpServletRequest URL. Theme cannot be set. URL is: " + request.getRequestURL().toString() );
				// Fall back to default theme.
				return getDefaultThemeName();
			}
		} catch ( MalformedURLException e ) {
			LOG.error( "URL is malformed. Theme cannot be set. URL is: " + request.getRequestURL().toString(), e );
			return getDefaultThemeName();
		}
	}
}