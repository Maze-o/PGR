package com.pgr.rm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pgr.rm.model.RecentEntity;

@Controller
public class RecentController {
	
	@Autowired
	RecentService service;	
	
	@RequestMapping("/recent")
	public String recent() { 
		return "recent";
	}
	
	@PostMapping("/recent")
	public String recent(Model model) {
		List<RecentEntity> list = RecentFunction.getRmList();
		service.insRecentMatch(list);
		return "recent";
	}
}
