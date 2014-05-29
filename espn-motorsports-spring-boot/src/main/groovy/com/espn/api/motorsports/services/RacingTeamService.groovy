package com.espn.api.motorsports.services


import com.espn.api.Reference
import com.espn.sports.persistence.motorsports.jpa.RacingTeamSeasonRecordEntity
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import com.codahale.metrics.annotation.Timed;
import com.espn.api.motorsports.domain.RacingTeamSeasonRecord
import com.espn.sports.dao.motorsports.jpa.RacingTeamJpaDAOImpl;
//import com.espn.sports.ws.motorsports.client.RacingTeamClient;

@Service
class RacingTeamService extends SportsService {

	static transactional = false
	
//	@Autowired RacingTeamClient racingTeamSeasonStandingsClient
	@Autowired RacingTeamJpaDAOImpl racingTeam
	
	List<RacingTeamSeasonRecordEntity> findTeamSeasonStandings(int leagueId,int seasonYear) {
		if (!leagueId || !seasonYear ) { return Collections.emptyList() }
		return racingTeamSeasonStandingsClient.getTeamSeasonRecords(seasonYear,2,leagueId)
	}
	
	RacingTeamSeasonRecordEntity findTeamSeasonStanding(int teamId, int leagueId,int seasonYear) {
		if (!leagueId || !seasonYear || !teamId) { return Collections.emptyList() }
		return racingTeamSeasonStandingsClient.getTeamSeasonRecord(teamId,seasonYear,2,leagueId)
	}
	
	def getTeam(){
		println('teamid-->'+racingTeam.getMaxTeamId())
		return null
	}
	
	@Timed
	List<RacingTeamSeasonRecord> getTeamSeasonStandings(int leagueId,int seasonYear) {
		List<RacingTeamSeasonRecordEntity> xoverStandings = findTeamSeasonStandings(leagueId,seasonYear)
		List<RacingTeamSeasonRecord> standings = new ArrayList<RacingTeamSeasonRecord>(xoverStandings.size())
		for (RacingTeamSeasonRecordEntity standing : xoverStandings) {
			standings.add(buildStanding(standing))
		}
		return standings
	}	
	
	@Timed
	RacingTeamSeasonRecord getTeamSeasonStanding(int teamId,int leagueId,int seasonYear) {
		
		return buildStanding(findTeamSeasonStanding(teamId,leagueId,seasonYear))
	}
	
	

	protected RacingTeamSeasonRecord buildStanding(RacingTeamSeasonRecordEntity standing) {
		if (!standing) { return null }
		
		def sportName = springApplication.sport.name
		def leagueName =  springApplication.espn.racing.leagueId.find{it.value == standing.leagueId}?.key
		
		return buildInstance(new RacingTeamSeasonRecord(
		complete 		:standing.complete,
		position		:standing.position,
		pointsBehind	:standing.pointsBehind,
		earnings		:standing.earnings,
		races			:standing.races,
		starts			:standing.starts,
		didNotFinish	:standing.didNotFinish,
		polePositions	:standing.polePositions,
		top5			:standing.top5,
		top10			:standing.top10,
		countback		:standing.countback
		), sportName,leagueName,standing.getTeam().getId())
	}
}
