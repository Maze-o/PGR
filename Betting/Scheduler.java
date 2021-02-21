package com.pgr.scheduler;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pgr.bet.BetService;
import com.pgr.db.DbFunction;
import com.pgr.model.BetEntity;
import com.pgr.model.RecentEntity;
import com.pgr.rm.RecentService;

@Component
public class Scheduler {
	private static Logger logger = LoggerFactory.getLogger(Scheduler.class);
	@Autowired
	RecentService service;
	
	@Autowired
	BetService bService;
	
	@Scheduled(cron = "*/5 * * * * *") // 매일 12시에 가져온다.(00~24시 기준)
	public void RecentMatchs() { // 최근 경기를 가져온다.
		BetEntity p = new BetEntity();
		p.setUserPk(1);
		p.setProperty(5000);
		p.setId(578503);
		p.setMyProperty(10000);
		p.setTeam(1);
		BetEntity p1 = new BetEntity();
		p1.setUserPk(2);
		p1.setProperty(6000);
		p1.setId(578503);
		p1.setMyProperty(10000);
		p1.setTeam(2);
		BetEntity p2 = new BetEntity();
		p2.setUserPk(3);
		p2.setProperty(7000);
		p2.setId(578503);
		p2.setMyProperty(10000);
		p2.setTeam(0);
		BetEntity p3 = new BetEntity();
		p3.setUserPk(4);
		p3.setProperty(4000);
		p3.setId(578503);
		p3.setMyProperty(10000);
		p3.setTeam(2);
		BetEntity p4 = new BetEntity();
		p4.setUserPk(5);
		p4.setProperty(8000);
		p4.setId(578503);
		p4.setMyProperty(10000);
		p4.setTeam(0);
		BetEntity p5 = new BetEntity();
		p5.setUserPk(6);
		p5.setProperty(4000);
		p5.setId(578503);
		p5.setMyProperty(10000);
		p5.setTeam(2);

		RecentEntity li = new RecentEntity();
		li.setLscore(4);
		li.setRscore(3);
		li.setId(578503);
		
		bService.updBetSuccess(li, p);
		bService.updBetSuccess(li, p1);
		bService.updBetSuccess(li, p2);
		bService.updBetSuccess(li, p3);
		bService.updBetSuccess(li, p4);
		bService.updBetSuccess(li, p5);
		
		List<RecentEntity> list = DbFunction.getRmList();
		int count = 0;
		for(Iterator<RecentEntity> i = list.iterator() ; i.hasNext();) {
			RecentEntity temp = i.next();
			if(service.selRecentMatch(temp) != null) { // 이미 데이터가 있다면 업데이트된다.
				if(temp.getCompleted() == true && service.selRecentMatch(temp).getCompleted() == false) {
					
				}
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
