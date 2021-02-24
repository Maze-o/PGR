package com.pgr.bet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgr.model.BetEntity;
import com.pgr.model.RecentEntity;
import com.pgr.rm.RecentMapper;

@Service
public class BetService {
	
	@Autowired
	BetMapper bMapper;
	
	@Autowired
	RecentMapper mapper;
	
	public int insBet(BetEntity p) {
		if(p.getTeam() == 2) { //팀선택시에 자동으로 property값이 win lose draw에 들어가게 설정
			p.setLose(p.getProperty());
		} else if(p.getTeam() == 1) {
			p.setDraw(p.getProperty());
		} else if(p.getTeam() == 0) {
			p.setWin(p.getProperty());
		}
		
		p.setMyProperty(p.getMyProperty()-p.getProperty()); //보유자산에서 배팅한 금액만큼 차감

		System.out.println("win : " + p.getWin());
		System.out.println("lose : " + p.getLose());
		System.out.println("draw : " + p.getDraw());
		return bMapper.insBet(p);
	}
	
	public double selBetList(RecentEntity p) {
		System.out.println("p:" + p.getId());
		int total = 0;
		int wTotal = 0;
		int lTotal = 0;
		int dTotal = 0;
		List<BetEntity> li = bMapper.selBetList(p);
		for(int i=0;i< li.size();i++) {
		li.get(i);
		System.out.println(li.get(i).getProperty());
		System.out.println(li.get(i).getWin());
		System.out.println(li.get(i).getLose());
		System.out.println(li.get(i).getDraw());
		total += li.get(i).getProperty(); 
		wTotal += li.get(i).getWin(); 
		lTotal += li.get(i).getLose(); 
		dTotal += li.get(i).getDraw(); 
		}
		System.out.println("wTotal : " + wTotal + "lTotal : " + lTotal + "dTotal : " + dTotal);
		System.out.println("total : " + total);
		double allocation = 1;
		if(p.getLscore() + p.getRscore() == 0 && dTotal == 0 ) {
			return allocation;
		}
		else if(p.getLscore() < p.getRscore()) {
			allocation = total/lTotal;
			System.out.println("allocation : " + allocation);
			return allocation;
		} else if(p.getLscore() == p.getRscore()) {
			allocation = total/dTotal;
			System.out.println("allocation : " + allocation);
			return allocation;
		} else if(p.getLscore() > p.getRscore()) {
			allocation = total/wTotal;
			System.out.println("allocation : " + allocation);
			return allocation;
		}
		
		return 0;
	}

	public int updBetSuccess(RecentEntity rp) {
		BetEntity bp = new BetEntity();
		bp.setId(rp.getId());
		if(rp.getLscore() > rp.getRscore()) {
			bp.setcTeam(2);
		} else if(rp.getLscore() == rp.getRscore()) {
			bp.setcTeam(1);
		} else if(rp.getLscore() < rp.getLscore()) {
			bp.setcTeam(0);
		}
		return bMapper.updBetSuccess(bp);
	}
}
