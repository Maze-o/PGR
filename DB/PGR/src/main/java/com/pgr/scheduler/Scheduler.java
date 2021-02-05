package com.pgr.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pgr.db.DbFunction;
import com.pgr.rm.RecentService;
import com.pgr.rm.model.RecentEntity;

@Component
public class Scheduler {
	
	@Autowired
	RecentService service;
	
	@Scheduled(cron = "0 0 12 * * *") // 매일 12시에 가져온다.(00~24시 기준)
	public void RecentMatchs() { // 최근 경기를 가져온다.
		List<RecentEntity> list = DbFunction.getRmList();
		int result = service.insRecentMatch(list);
		System.out.println("RECENT MATCHS ADDED: " + result);
	}
}
