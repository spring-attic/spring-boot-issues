package com.espn.api.motorsports.domain

import com.espn.api.Instance
import com.espn.api.Collection
import com.espn.api.Reference

/**
 * The information about the currently active playlist, category, and item.
 * 
 * @title BottomLine
 */
class RacingCompetition extends Instance {

	public static final String URI_TEMPLATE = RacingEvent.URI_TEMPLATE+'/competitions'+'/{competitionId}'
	static singular = true
	static order = ['competitionId', 'startDate','endDate' ,'season']
	
	int 	competitionId
	Date 	startDate
	Date    endDate
	Reference<Season> season

	
	
	
	
}
