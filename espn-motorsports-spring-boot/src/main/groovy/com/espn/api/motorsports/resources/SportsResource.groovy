package com.espn.api.motorsports.resources

import com.espn.api.resources.InstanceResource
import java.text.SimpleDateFormat
import org.springframework.boot.SpringApplication
import org.springframework.beans.factory.annotation.Autowired
import com.espn.api.motorsports.services.SportsService

class SportsResource extends InstanceResource {

	@Autowired SportsService sportsService
	SpringApplication springApplication
	
	//DictionaryService __
	
	protected Integer getSportId(String sportName) {
		return sportsService.getSportId(sportName);
	}
	
	protected String getParam(String key, String dflt = null) {
		return /*params[key] ?:*/ dflt; // TODO: how to get params from Jersey?
	}
	
	protected Integer getInteger(String key, Integer dflt = null) {
		def value = getParam(key)
		return (value ? value.toInteger() : dflt)
	}
	
	protected Boolean getBoolean(String key, Boolean dflt = null) {
		def value = getParam(key)
		return (value ? value.toBoolean() : dflt)
	}
	
	protected Date shortenDate(Date date) {
		return date.clearTime();
	}
	
	/*protected Date getDate(String dates){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyymmdd")
		Date date
		if(dateStr){
			date = formatter.parse(dateStr)
		}
		else{
			date = formatter.parse(formatter.format(new Date()))
		}
		return date
	}*/
	
	protected int getRacingLeagueId(String leagueIdStr){
			def leagueIdMap = springApplication.config.espn.racing.leagueId
			int leagueId
			if(leagueIdMap[leagueIdStr])
				leagueId = leagueIdMap[leagueIdStr]
			else
				throw new Exception("This is not a motorsports LeagueId!")
			return leagueId
	}
	
	protected int getSeasonYear(String dates){
		return dates ? Integer.parseInt(dates) : (Calendar.instance.get(Calendar.YEAR))
	}
	
}
