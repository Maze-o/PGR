package com.pgr.scheduler;

import java.util.Iterator;
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
}
