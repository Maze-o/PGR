package com.pgr.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pgr.model.NoticeDomain;
import com.pgr.model.NoticeEntity;

@Mapper
public interface NoticeMapper {
	int insNotice(NoticeEntity p);
	List<NoticeDomain> selNoticeList(NoticeDomain p);
	NoticeDomain selNotice(NoticeEntity p);
	int upNotice(NoticeEntity p);
}
