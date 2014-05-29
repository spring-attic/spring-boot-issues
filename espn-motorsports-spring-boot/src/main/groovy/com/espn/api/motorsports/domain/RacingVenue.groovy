package com.espn.api.motorsports.domain

import com.espn.api.Instance
import com.espn.api.Collection
import com.espn.api.Reference
import com.espn.sports.persistence.motorsports.jpa.RacingVenueEntity
/**
 * The information about the currently venue
 * 
 * @title RacingVenue
 */
@Mixin(RacingVenueEntity)
class RacingVenue extends Instance {

	public static final String URI_TEMPLATE = '/{network}/now';
	 
	static singular = true
	static order = ['id', 'startDate','endDate' ]
	
	
	
	
}
