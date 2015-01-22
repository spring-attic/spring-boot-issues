/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.common;

import java.util.regex.Pattern;

public class Constants {

	public static final String UTC_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

	public static Pattern nonDigitsPattern = Pattern.compile( "[^0-9]+" );

}
