package com.pgr.rm;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pgr.model.RecentEntity;

@Mapper
public interface RecentMapper {
	int insRecentMatch(List<RecentEntity> data);
	int updRecentMatch(RecentEntity data);
	RecentEntity selRecentMatch(RecentEntity data);
}