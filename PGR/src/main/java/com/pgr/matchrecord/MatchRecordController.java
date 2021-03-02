package com.pgr.matchrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgr.model.MatchRecordEntity;

@RestController
public class MatchRecordController {
	
	@Autowired
	MatchRecordMapper mapper;
	
	@GetMapping("/record")
	public MatchRecordEntity record(MatchRecordEntity p) {
		return mapper.selMatchRecord(p);
	}
}
