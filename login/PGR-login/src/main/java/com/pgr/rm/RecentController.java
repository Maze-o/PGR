package com.pgr.rm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgr.model.RecentEntity;

@RequestMapping("/recent")
@RestController
public class RecentController {
	
	@Autowired
	private RecentService service;
	
	@GetMapping
	public List<RecentEntity> list() {
		return service.selListRecentMatch();
	}
}
