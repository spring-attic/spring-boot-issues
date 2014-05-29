package com.espn.api.motorsports.domain

import com.espn.api.Instance
import com.espn.api.Collection
import com.espn.api.Reference
import com.espn.sports.persistence.jpa.SeasonEntity


/**
 * The information about the currently active playlist, category, and item.
 * 
 * @title BottomLine
 */
@Mixin(SeasonEntity)
class Season extends Instance {

	public static final String URI_TEMPLATE = BaseSport.BASE_URI_TEMPLATE+'{sport}/' + '{league}/seasons'+Instance.ID_TEMPLATE;
	 
	static singular = true
	static order = ['id', 'startDate','endDate' ,'season','type','venue','event']
	
	
	
	
	
}
