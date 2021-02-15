package com.pgr.rm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgr.model.RecentEntity;

@Service
public class RecentService {
	@Autowired
	RecentMapper mapper;
	
	public int insRecentMatch(List<RecentEntity> data) {
		return mapper.insRecentMatch(data);
	}
	
	public RecentEntity selRecentMatch(RecentEntity data) {
		return mapper.selRecentMatch(data);
	}
	
	public int updRecentMatch(RecentEntity data) {
		return mapper.updRecentMatch(data);
	}
}
