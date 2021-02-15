package com.pgr.team;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pgr.model.TeamEntity;

@Mapper
public interface TeamMapper {
	int insTeam(List<TeamEntity> p);
	List<TeamEntity> selTeamList();
	int updTeam(TeamEntity p);
}
