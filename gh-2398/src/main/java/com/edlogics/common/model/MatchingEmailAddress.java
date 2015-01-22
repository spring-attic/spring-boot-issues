/*
 *
 * Copyright EdLogics, LLC. All Rights Reserved.
 *
 * This software is the proprietary information of EdLogics, LLC.
 * Use is subject to license terms.
 *
 */
package com.edlogics.common.model;

import java.io.Serializable;

/**
 * @author jlanpher
 *
 */
public interface MatchingEmailAddress extends Serializable {

	String getEmail();

	String getEmailConfirm();
}