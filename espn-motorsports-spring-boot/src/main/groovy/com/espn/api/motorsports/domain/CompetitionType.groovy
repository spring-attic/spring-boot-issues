package com.espn.api.motorsports.domain

import com.espn.api.Instance
import com.espn.api.Collection
import com.espn.api.Reference
import com.espn.sports.persistence.jpa.CompetitionTypeEntity

/**
 * The information about the currently active playlist, category, and item.
 * 
 * @title BottomLine
 */
@Mixin(CompetitionTypeEntity)
class CompetitionType extends Instance {

	public static final String URI_TEMPLATE = '/{network}/now';
	 
	static singular = true
	static order = ['id', 'startDate','endDate' ,'season','type','venue','event']
	
	
	
	
	
}
