package com.pgr.scheduler;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pgr.db.DbFunction;
import com.pgr.model.RecentEntity;
import com.pgr.model.TeamEntity;
import com.pgr.rm.RecentService;
import com.pgr.team.TeamService;

@Component
public class Scheduler {
	
	@Autowired
	RecentService service;
	
	@Autowired
	TeamService tService;
	
	@Scheduled(cron = "0 * * * * *") // 매분 0초마다 실행한다.
	public void RecentMatchs() { // 최근 경기를 가져온다.
		List<RecentEntity> list = DbFunction.getRmList();
		int count = 0;
		for(Iterator<RecentEntity> i = list.iterator() ; i.hasNext();) {
			RecentEntity temp = i.next();
			if(service.selRecentMatch(temp) != null) { // 이미 데이터가 있다면 업데이트된다.
				service.updRecentMatch(temp);
				count++;
				i.remove(); // 업데이트하고 리스트에서 삭제	
			}
		}
		System.out.println("RECENT MATCHS UPDATED: " + count);
		if(! list.isEmpty()) { // 리스트가 비어있지않으면 새로운 데이터를 추가
			int result = service.insRecentMatch(list);
			System.out.println("RECENT MATCHS ADDED: " + result);
		}
	}
	
	@Scheduled(cron = "0 * * * * *") // 매분 0초마다 실행한다.
	public void TeamsStat() { // 최근 팀 통계를 가져온다.
		List<TeamEntity> list = DbFunction.getTeamsList();
		if(tService.selTeamList().isEmpty()) { // 팀데이터가 아예 비어있을때
			tService.insTeam(list);
		} else { // 비어있지 않다면 값이 있는거니까 UPDATE를 한다.

		}
	}
}
