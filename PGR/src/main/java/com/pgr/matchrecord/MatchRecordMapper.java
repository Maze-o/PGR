package com.pgr.matchrecord;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pgr.model.MatchRecordEntity;

@Mapper
public interface MatchRecordMapper {
	int insMatchRecordList(List<MatchRecordEntity> p);
	int updMatchRecord(MatchRecordEntity p);
	int delMatchRecord(MatchRecordEntity p);
	MatchRecordEntity selMatchRecord(MatchRecordEntity p);
}
