package com.pgr.rm;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgr.bet.BetService;
import com.pgr.model.RecentDTO;
import com.pgr.model.RecentEntity;

@Service
public class RecentService {
	
	@Autowired
	private RecentMapper mapper;
	
	@Autowired
	private BetService bService;
	
	public int insRecentMatch(List<RecentEntity> list) {
		int count = 0;
		for(Iterator<RecentEntity> i = list.iterator() ; i.hasNext();) { // 리스트를 삭제하면 인덱스가 바뀌기때문에 이터레이터 사용
			RecentEntity temp = i.next();
			if(selRecentMatch(temp) != null) { // 이미 데이터가 있다면 업데이트된다.
				if(temp.getCompleted() == true && selRecentMatch(temp).getCompleted() == false) {
					bService.updBetSuccess(temp);
					bService.updBetUser(temp);
				}
				updRecentMatch(temp);
				count++;
				i.remove(); // 업데이트하고 리스트에서 삭제	
			}
		}
		if(! list.isEmpty()) { // 리스트가 비어있지않으면 새로운 데이터를 추가
			return mapper.insRecentMatch(list);
		}
		return count;
	}
	
	public RecentEntity selRecentMatch(RecentEntity data) {
		return mapper.selRecentMatch(data);
	}
	
	public int updRecentMatch(RecentEntity data) {
		return mapper.updRecentMatch(data);
	}
	
	public List<RecentEntity> selListRecentMatch() {
		return mapper.selListRecentMatch();
	}
	
	public int delRecentMatch(RecentEntity data) {
		return mapper.delRecentMatch(data);
	}
	
	public List<RecentEntity> selScheduleList(RecentDTO data) {
		return mapper.selScheduleList(data);
	}
}
