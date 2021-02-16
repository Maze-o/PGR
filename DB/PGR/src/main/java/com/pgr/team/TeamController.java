package com.pgr.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgr.model.TeamEntity;

@Controller
public class TeamController {
	
	@Autowired
	private TeamService service;

	@GetMapping("/teams")
	public String teams() {
		return "menus/teams";
	}
	
	@ResponseBody
	@GetMapping("/teamdata")
	public List<TeamEntity> teamdata() {
		return service.selTeamList();
	}
	
}
