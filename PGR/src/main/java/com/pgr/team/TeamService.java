package com.pgr.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgr.model.TeamEntity;

@Service
public class TeamService {
	@Autowired
	TeamMapper mapper;
	
	public int insTeam(List<TeamEntity> list) {
		int count = 0;
		if(selTeamList().isEmpty()) { // 팀데이터가 아예 비어있을때
			return mapper.insTeam(list);
		} else { // 비어있지 않다면 값이 있는거니까 UPDATE를 한다.
			for(TeamEntity temp : list ) {
				updTeam(temp);
				count++;
			}
		}
		return count;
	}
	
	public List<TeamEntity> selTeamList() {
		return mapper.selTeamList();
	}
	
	public int updTeam(TeamEntity p) {
		return mapper.updTeam(p);
	}
}
