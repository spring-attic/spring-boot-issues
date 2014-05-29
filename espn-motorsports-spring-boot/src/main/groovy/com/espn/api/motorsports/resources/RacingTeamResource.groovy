package com.espn.api.motorsports.resources

import javax.servlet.http.HttpServletResponse
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam

import org.springframework.beans.factory.annotation.Autowired

import com.espn.api.Collection
import com.espn.api.Model
import com.espn.api.motorsports.services.RacingTeamService
//import com.espn.groovy.beaninfo.BeanInfo

/**
 * API endpoint to retrieve competition information on the available competitions.
 *
 * @id competition
 * @title racingCompetition
 * @version 1.0
 */
//@BeanInfo
@Path('/api/v2/sports/team') 
class RacingTeamResource extends SportsResource {

	@Autowired RacingTeamService racingTeamService

//	@PathParam("league") String league
	
	@GET
	Model getTeam() {
		def val = racingTeamService.getTeam()
		return buildCollection(Collection.from(lists))
	}
	
	/**
	 * Get the list of all competitions.
	 *
	 * @return  The response containing the list of all competitions
	 */
	@GET
	@Path('/standings')
	Model getTeamStandings(@QueryParam("season") String season) {
		int leagueId = getRacingLeagueId(league)
		int seasonYear = getSeasonYear(season)
		def lists = racingTeamService.getTeamSeasonStandings(leagueId,seasonYear)
		return buildCollection(Collection.from(lists))
	}
	
	@GET
	@Path('/standings/{teamId}')
	Model getTeamStandings(@PathParam("teamId") int teamId,@QueryParam("season") String season) {
		
		int leagueId = getRacingLeagueId(league)
		int seasonYear = getSeasonYear(season)
		return buildInstance(racingTeamService.getTeamSeasonStanding(teamId,leagueId,seasonYear))
	}
	
	

}
