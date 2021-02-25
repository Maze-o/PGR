package com.pgr;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.pgr.bet.BetService;
import com.pgr.db.DbUtils;
import com.pgr.model.RecentEntity;
import com.pgr.rm.RecentService;
import com.pgr.team.TeamService;

@Component
public class AllRecentMatch implements ApplicationRunner {
	
	@Autowired
	BetService bService;
	
	@Autowired
	RecentService rService;
	
	@Autowired
	TeamService tService;
	
	 @Override
	 public void run(ApplicationArguments args) throws Exception {
		 List<String> length = DbUtils.getDateList();
		 
		 for(int j=0;j<length.size();j++) {
			List<RecentEntity> list = DbUtils.getRmList("?dates=" + length.get(j));
			int count = 0;
			for(Iterator<RecentEntity> i = list.iterator() ; i.hasNext();) { // 리스트를 삭제하면 인덱스가 바뀌기때문에 이터레이터 사용
				RecentEntity temp = i.next();
				if(rService.selRecentMatch(temp) != null) { // 이미 데이터가 있다면 업데이트된다.
					if(temp.getCompleted() == true && rService.selRecentMatch(temp).getCompleted() == false) {
						bService.updBetSuccess(temp);
					}
					rService.updRecentMatch(temp);
					count++;
					i.remove(); // 업데이트하고 리스트에서 삭제	
				}
			}
			if(! list.isEmpty()) { // 리스트가 비어있지않으면 새로운 데이터를 추가
				int result = rService.insRecentMatch(list);
				System.out.println("작업 추가");
			}
		 }
	 }
}
