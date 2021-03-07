package com.pgr.rm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgr.model.RecentDTO;
import com.pgr.model.RecentEntity;

@RestController
public class RecentController {
	
	@Autowired
	private RecentService service;
	
	@GetMapping("/recent")
	public List<RecentEntity> list() {
		return service.selListRecentMatch();
	}

	@GetMapping("/recentMatch")
	public RecentEntity recentMatch(RecentEntity data) {
		return service.selRecentMatch(data);
	}
	
	@GetMapping("/scheduleMatch")
	public List<RecentEntity> scheduleMatch(RecentDTO data) {
		return service.selScheduleList(data);
	}
}
