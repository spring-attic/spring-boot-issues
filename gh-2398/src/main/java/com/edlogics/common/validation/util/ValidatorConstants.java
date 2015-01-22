/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.common.validation.util;

/**
 * @author jlanpher
 * 
 *         The purpose of this class is simply to provide a single location to
 *         define re-usable validation related constants.
 *
 */
public final class ValidatorConstants {

	//TODO: Please comment on what this pattern represents.
	public static final String STANDARD_TEXT_VALIDATOR_255 = "(^(?!.{255})^(?!\\s*$).+)";

	//TODO: Please comment on what this pattern represents.
	public static final String STANDARD_TEXT_VALIDATOR_50 = "/^[a-zA-Z0-9*]{0,50}$/";

	//TODO: Please comment on what this pattern represents.
	public static final String ARBITRARY_PASSWORD_FOR_FORM_BINDING = "Suppr3ssed!";

	//TODO: Please comment on what this pattern represents.
	public static final String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";

	/**
	 * Only contains '-', 'whitespace', 'aA'-'zZ' or apostrophe
	 */
	public static final String NAME_REGEX = "^[-\\sa-zA-Z']+$";

	/**
	 * Only contains numbers and letters'
	 */
	public static final String SCREEN_NAME_REGEX = "^[0-9a-zA-Z]+$";

	/**
	 * Only contains letters'
	 */
	public static final String ALPHA_REGEX = "^[a-zA-Z]+$";

	/**
	 * Only contains uppercase letters with a length of 2
	 */
	public static final String STATE_CODE_REGEX = "^[A-Z]{2}+$";

	/**
	 * Only contains numbers and is between 10 and 11 in length
	 */
	public static final String PHONE_REGEX = "^[0-9]{10,11}$";

	/**
	 * only contains letters, spaces, dashes apostrophe between 2 and 30 characters
	 * http://blog.letterstream.com/2014/03/19/whats-the-longest-city-name-in-the-u-s/
	 */
	public static final String CITY_REGEX = "^[-\\.\\sa-zA-Z']{2,30}$";

	/**
	 * only contains letters, spaces, comma, period, question mark, percent, plus, dollar, parentheses, semicolon, colon, dash, underscore, single quote, double
	 * quote, greater than, less than and forward slash
	 */
	public static final String QUESTION_ANSWER_TEXT_REGEX = "^[\\(\\)\\<\\>\\,_\\-\\.\\sa-zA-Z0-9'\"\\/\\?\\$%\\+:;]+$";

	private ValidatorConstants() {}
}
