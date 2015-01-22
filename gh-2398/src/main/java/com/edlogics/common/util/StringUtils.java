/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.common.util;

/**
 * String Utilities needed, but not found in any common libraries. e.g. Apache Commons
 *
 * @author Christopher Savory
 *
 */
public class StringUtils {

	/**
	 * Converts a constant convention ("XXXXXXX_YYYY_ZZZ")
	 * to CamelCase ("xxxxxxxYyyyZzz")
	 *
	 * Note: could not find anywhere on any StringUtils
	 *
	 * @param cn
	 * @return
	 */
	public static String convertToCamelCase( String cn ) {
		StringBuilder sb = new StringBuilder();
		String[] str = cn.split( "_" );
		boolean firstTime = true;
		for ( String temp : str ) {
			if ( firstTime ) {
				sb.append( temp.toLowerCase() );
				firstTime = false;
			} else {
				sb.append( Character.toUpperCase( temp.charAt( 0 ) ) );
				sb.append( temp.substring( 1 ).toLowerCase() );
			}
		}
		return sb.toString();
	}

}
