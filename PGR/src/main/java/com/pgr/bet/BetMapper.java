package com.pgr.bet;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pgr.model.BetEntity;
import com.pgr.model.RecentEntity;

@Mapper
public interface BetMapper {
	int insBet(BetEntity p);
	List<BetEntity> selBetList(RecentEntity p);
	int updBetSuccess(BetEntity p);
	int updBetuser(BetEntity p);
}
