package com.pgr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.pgr.db.DbUtils;
import com.pgr.model.RecentEntity;
import com.pgr.rm.RecentService;

@Component
public class MatchPull implements ApplicationRunner { // 서버 가동시 실행
	
	public static boolean tempThread = true;
	
	@Autowired
	RecentService rService;
	
	 @Override
	 public void run(ApplicationArguments args) throws Exception { // 일정에 있는 모든 날짜의 경기 기록들을 가져옴
		 List<String> length = DbUtils.getDateList(); // 일정 년월일을 list로 String으로 가져옴
		 
		 for(int j=0;j<length.size();j++) {
			List<RecentEntity> list = DbUtils.getRmList("?dates=" + length.get(j));
			rService.insRecentMatch(list);;
		 }
		 tempThread = false; // 임시 쓰레드 역할을 하는 변수 false
	 }
}
