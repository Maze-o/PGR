package com.pgr.matchrecord;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgr.model.MatchRecordEntity;

@Service
public class MatchRecordService {

	@Autowired
	private MatchRecordMapper mapper;
	
	public int insMatchRecordList(List<MatchRecordEntity> list) {
		int count = 0;
		for(Iterator<MatchRecordEntity> i = list.iterator() ; i.hasNext();) { // 리스트를 삭제하면 인덱스가 바뀌기때문에 이터레이터 사용
			MatchRecordEntity temp = i.next();
			if(mapper.selMatchRecord(temp) != null) { // 이미 데이터가 있다면 업데이트된다.
				mapper.updMatchRecord(temp);
				count++;
				i.remove(); // 업데이트하고 리스트에서 삭제	
			}
		}
		if(! list.isEmpty()) { // 리스트가 비어있지않으면 새로운 데이터를 추가
			return mapper.insMatchRecordList(list);
		}
		return count;
	}
}
