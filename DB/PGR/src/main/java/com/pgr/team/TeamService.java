package com.pgr.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgr.model.TeamEntity;

@Service
public class TeamService {
	@Autowired
	TeamMapper mapper;
	
	public int insTeam(List<TeamEntity> p) {
		return mapper.insTeam(p);
	}
	
	public List<TeamEntity> selTeamList() {
		return mapper.selTeamList();
	}
	
	public int updTeam(TeamEntity p) {
		return mapper.updTeam(p);
	}
}
