package com.espn.api.motorsports.domain

import com.espn.api.Instance
import com.espn.api.Collection
import com.espn.api.Reference

/**
 * The information about the racing event.
 * 
 * @title Event
 */
class RacingEvent extends Instance {

	public static final String URI_TEMPLATE = BaseSport.BASE_URI_TEMPLATE + '{sport}/' + '{league}/events'+Instance.ID_TEMPLATE;
	 
	static singular = true
	static order = ['name','startDate' ,'endDate','venue','competitions']
	
	String name
	String league
	Date   startDate
	Date   endDate
	Reference<RacingVenue> venue
	Reference<Collection<RacingCompetition>> competitions
	
}
