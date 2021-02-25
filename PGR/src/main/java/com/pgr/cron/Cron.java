package com.pgr.cron;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pgr.MatchPull;
import com.pgr.bet.BetService;
import com.pgr.db.DbUtils;
import com.pgr.model.RecentEntity;
import com.pgr.model.TeamEntity;
import com.pgr.rm.RecentService;
import com.pgr.team.TeamService;

@Component
public class Cron {
	
	private static Logger logger = LoggerFactory.getLogger(Cron.class);
	
	@Autowired
	RecentService rService;
	
	@Autowired
	TeamService tService;
	
	@Scheduled(cron = "*/10 * * * * *") // 매분 0초마다 실행한다.
	public void RecentMatchs() { // 최근 경기를 가져온다.
		if(MatchPull.tempThread == false) { // 모든 경기를 가져오는 작업이 다 끝낫을때 실행
			List<RecentEntity> list = DbUtils.getRmList("");
			logger.info("Recent Match Updated: "  + rService.insRecentMatch(list));
		}
	}
	
	@Scheduled(cron = "0 * * * * *") // 매분 0초마다 실행한다.
	public void TeamsStat() { // 최근 팀 통계를 가져온다.
		List<TeamEntity> list = DbUtils.getTeamsList();
		logger.info("Teams Stat Updated: " + tService.insTeam(list));
	}
}
