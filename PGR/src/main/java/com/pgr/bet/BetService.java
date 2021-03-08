package com.pgr.bet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgr.model.BetDomain;
import com.pgr.model.BetDomain2;
import com.pgr.model.BetEntity;
import com.pgr.model.RecentEntity;
import com.pgr.model.UserEntity;
import com.pgr.rm.RecentMapper;
import com.pgr.user.UserMapper;

@Service
public class BetService {
	
	@Autowired
	BetMapper bMapper;
	
	@Autowired
	RecentMapper mapper;
	
	@Autowired
	UserMapper uMapper;
	
	public int insBet(BetEntity p) {
		if(p.getTeam() == 2) { //팀선택시에 자동으로 property값이 win lose draw에 들어가게 설정
			p.setLose(p.getProperty());
		} else if(p.getTeam() == 1) {
			p.setDraw(p.getProperty());
		} else if(p.getTeam() == 0) {
			p.setWin(p.getProperty());
		}
		UserEntity up = new UserEntity();
		
		if(p.getMyProperty()-p.getProperty() >= 0) { //보유자산이하에서만 배팅가능
			p.setMyProperty(p.getMyProperty()-p.getProperty()); //보유자산에서 배팅한 금액만큼 차감
		}
		
		up.setMyProperty(p.getMyProperty());
		up.setUserPk(p.getUserPk());
		uMapper.updProperty(up);
		
		return bMapper.insBet(p);
	}

	public int updBetSuccess(RecentEntity rp) {
		BetEntity bp = new BetEntity(); //RecentEntity 받을 객체 생성
		bp.setId(rp.getId());
		if(rp.getLscore() < rp.getRscore()) {
			bp.setcTeam(2);
		} else if(rp.getLscore() == rp.getRscore()) {
			bp.setcTeam(1);
		} else if(rp.getLscore() > rp.getRscore()) {
			bp.setcTeam(0);
		}
		return bMapper.updBetSuccess(bp);
	}
	
	public int updBetUser(RecentEntity rp) {
		BetEntity bp = new BetEntity();
		bp.setId(rp.getId()); //경기id 값 얻기위함
		return bMapper.updBetUser(bp);
	}
	
	public BetEntity selBetUser(BetEntity p) {
		return bMapper.selBetUser(p);
	}
	
	public List<BetEntity> selBetList(RecentEntity p) {
		return bMapper.selBetList(p);
	}
	
	public List<RecentEntity> selBettingRoomList() {
		return mapper.selBettingRoomList();
	}
	
	public List<RecentEntity> selEndBettingroomList() {
		return mapper.selEndBettingRoomList();
	}
	
	public BetDomain selBetAllocation(BetEntity p) {
		return bMapper.selBetAllocation(p);
	}
	
	public List<BetDomain2> selBetUserPk(BetEntity p) {
		return bMapper.selBetUserPk(p);
	}

}
