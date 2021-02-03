package com.pgr.rm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgr.rm.model.RecentEntity;

@Service
public class RecentService {
	@Autowired
	RecentMapper mapper;
	
	public int insRecentMatch(List<RecentEntity> data) {
		return mapper.insRecentMatch(data);
	}
}
