package com.espn.api.motorsports.services

import org.springframework.boot.SpringApplication
import com.espn.api.services.BaseService
import org.springframework.stereotype.Service

@Service
class SportsService extends BaseService {

	//UidService uidService
	SpringApplication springApplication

	/*Sport getSport(String sportName) {
		def sportId = getSportId(sportName)
		
		def sport = create()
		sport.id = sportId
		sport.name = __[sportName]
		//sport.uid = uidService.getUID(sportId);
		return sport;
	}*/
	/*
	Sport create() {
		return new Sport();
	}*/
	
	def getSports() {
		return springApplication.config.espn.sports;
	}
	
	def getLeagues() {
		return springApplication.config.espn.leagues;
	}
	
	Integer getSportId(String sportName) {
		
		sportName = sportName?.toLowerCase()
		
		// this is the NEW method using content link IDs from the DB
		def sportId = getContentLinkSportId(sportName);
		
		// this is a fallback method for backwards compatability
		if (sportId == -1) {
			def leagues = getLeagues()
			def sportInfo = leagues[sportName]
			if (sportInfo) {
				sportId = sportInfo['id']
			}
		}
		
		sportId
	}
	
	Integer getContentLinkSportId(Object tag) {
		if (tag instanceof String) {
			tag = ((String) tag).toLowerCase()
		} else if (tag instanceof Integer) {
			// nothing to do really
		}

		def leagues = getLeagues()
		def sportInfo = leagues[tag]
		return (sportInfo ? sportInfo['id'] : -1)
	}

	Integer getSportIdByResource(String resource) {
		def leagues = getLeagues()
		def resourceInfo = leagues[resource];
		return resourceInfo ? getSportId(resourceInfo['sportName']) : null;
	}
	
	Integer getParentSportIdByAbbrev(String abbr) {
		def sport = null;
		if (abbr != null) {
			sport = getSportByLeagueAbbrev(abbr.toLowerCase());
		}
		
		return sport ? getSportId(sport) : null;
	}
	
	Map getLeagueConfig(String resource) {
		
		def name = resource
		if (resource == "racing" || resource == "nascar" || resource == "rpm") {
			name = 'racing'
		}
		else if (resource == "soccer" || resource == "soccernet") {
			name = 'soccer'
		}
		
		def leagues = getLeagues()
		return leagues[name]
	}
	
	private def getSportByLeagueAbbrev(String league) {
		def sportInfos = getSports();
		league = league.toLowerCase();
		def entry = sportInfos.find { sportName, sportInfo ->
			return sportInfo['leagues']?.contains(league)
		}
		
		if (entry == null) {
			 // TODO: Check if this is a soccer league
			 //soccerLeague = SoccerLeagueApplication.getSoccerLeagueByTag(league);
			 //if (soccerLeague != null) {
			 //    sport = "soccer";
			 //}
		}
		
		return entry.key
	}
	
}
