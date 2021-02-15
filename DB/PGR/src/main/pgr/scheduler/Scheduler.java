package com.pgr.scheduler;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pgr.db.DbUtils;
import com.pgr.model.RecentEntity;
import com.pgr.model.TeamEntity;
import com.pgr.rm.RecentService;
import com.pgr.team.TeamService;

@Component
public class Scheduler {
	
	private static Logger logger = LoggerFactory.getLogger(Scheduler.class);
	
	@Autowired
	RecentService rService;
	
	@Autowired
	TeamService tService;
	
	@Scheduled(cron = "0 * * * * *") // 매분 0초마다 실행한다.
	public void RecentMatchs() { // 최근 경기를 가져온다.
		List<RecentEntity> list = DbUtils.getRmList();
		int count = 0;
		for(Iterator<RecentEntity> i = list.iterator() ; i.hasNext();) {
			RecentEntity temp = i.next();
			if(rService.selRecentMatch(temp) != null) { // 이미 데이터가 있다면 업데이트된다.
				rService.updRecentMatch(temp);
				count++;
				i.remove(); // 업데이트하고 리스트에서 삭제	
			}
		}
		logger.info("RECENT MATCHS UPDATED: " + count);
		if(! list.isEmpty()) { // 리스트가 비어있지않으면 새로운 데이터를 추가
			int result = rService.insRecentMatch(list);
			logger.info("RECENT MATCHS ADDED: " + result);
		}
	}
	
	@Scheduled(cron = "0 * * * * *") // 매분 0초마다 실행한다.
	public void TeamsStat() { // 최근 팀 통계를 가져온다.
		List<TeamEntity> list = DbUtils.getTeamsList();
		if(tService.selTeamList().isEmpty()) { // 팀데이터가 아예 비어있을때
			logger.info("TEAMS STAT ADDED: " + tService.insTeam(list));
		} else { // 비어있지 않다면 값이 있는거니까 UPDATE를 한다.
			int count = 0;
			for(TeamEntity te : list ) {
				count += tService.updTeam(te);
			}
			logger.info("TEAMS STAT UPDATED: " + count);
		}
	}
}
