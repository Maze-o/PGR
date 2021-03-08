package com.pgr.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgr.SecurityUtils;
import com.pgr.model.NoticeDomain;
import com.pgr.model.NoticeEntity;


@Service
public class NoticeService {
	
	@Autowired
	private SecurityUtils sUtils;
	
	@Autowired
	private NoticeMapper mapper;
	
	public List<NoticeDomain> selNoticeList(NoticeDomain p) {
		return mapper.selNoticeList(p);
	}
	
	public int insNotice(NoticeEntity p) {

		if (p.getTitle().equals("")) {
			return 0;
		}
		
		if(p.getCtnt().equals("")) {
			return 1;
		}
		
		mapper.insNotice(p);
		
		return 2;
	}
	
	public NoticeEntity selNotice(NoticeEntity p) {
		return mapper.selNotice(p);
	}
	
	public int upNotice(NoticeEntity p) {
		return mapper.upNotice(p);
	}
}
