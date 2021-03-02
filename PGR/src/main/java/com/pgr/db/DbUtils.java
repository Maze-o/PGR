package com.pgr.db;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.pgr.Const;
import com.pgr.model.MatchRecordEntity;
import com.pgr.model.RecentEntity;
import com.pgr.model.TeamEntity;

public class DbUtils {
	
	// 경기 일정 가져오는 함수
	public static List<String> getDateList() {
		String url = Const.RECENT_MATCHES;
		String teamData = getData(url);
		
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		JSONArray arr = null;
		
		List<String> list = new ArrayList<>();
		
		try {
			obj = (JSONObject)parser.parse(teamData);
			JSONArray leagues = (JSONArray)obj.get("leagues");
			obj = (JSONObject)leagues.get(0);
			arr = (JSONArray)obj.get("calendar");
			
			for(int i=0;i<arr.size();i++) {
				String temp = (String)arr.get(i);
				temp = temp.substring(0, 4) + temp.substring(5, 7) + temp.substring(8, 10); // 년, 월, 일만 가져와서 String형으로 합친다.
				list.add(temp);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//경기의 상세 레코드 가져오는 함수
	public static List<MatchRecordEntity> getMrList(String addUrl) {
		String url = Const.RECENT_MATCHES + addUrl; // API URL
		String teamData = getData(url);
		
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		JSONArray arr = null;
		
		List<MatchRecordEntity> list = new ArrayList<>();
		
		try {
			obj = (JSONObject)parser.parse(teamData);
			JSONArray event = (JSONArray)obj.get("events");
			
			for(int i=0;i<event.size();i++) {
				MatchRecordEntity re = new MatchRecordEntity();
				
				obj = (JSONObject)event.get(i);
				re.setId(Integer.parseInt((String)obj.get("id")));
				
				obj = (JSONObject)event.get(i);
				
				arr = (JSONArray)obj.get("competitions");
				
				obj = (JSONObject)arr.get(0);
				arr = (JSONArray)obj.get("competitors");
				
				obj = (JSONObject)arr.get(0);
				JSONArray arr2 = (JSONArray)obj.get("statistics");
				if(arr2 != null) { // NULL POINTER 방지
					if(! arr2.isEmpty()) { // 이미 끝나서 데이터가 담겨져있는 경기들만
						obj = (JSONObject)arr2.get(1);
						re.setLfoulsCommitted(Integer.parseInt((String)obj.get("displayValue")));
						obj = (JSONObject)arr2.get(2);
						re.setLwonCorners(Integer.parseInt((String)obj.get("displayValue")));
						obj = (JSONObject)arr2.get(4);
						re.setLpossessionPct(Double.parseDouble((String)obj.get("displayValue")));
						obj = (JSONObject)arr2.get(6);
						re.setLshotsOnTarget(Integer.parseInt((String)obj.get("displayValue")));
						obj = (JSONObject)arr2.get(8);
						re.setLtotalShots(Integer.parseInt((String)obj.get("displayValue")));
						
						obj = (JSONObject)arr.get(1);
						arr2 = (JSONArray)obj.get("statistics");
						obj = (JSONObject)arr2.get(1);
						re.setRfoulsCommitted(Integer.parseInt((String)obj.get("displayValue")));
						obj = (JSONObject)arr2.get(2);
						re.setRwonCorners(Integer.parseInt((String)obj.get("displayValue")));
						obj = (JSONObject)arr2.get(4);
						re.setRpossessionPct(Double.parseDouble((String)obj.get("displayValue")));
						obj = (JSONObject)arr2.get(6);
						re.setRshotsOnTarget(Integer.parseInt((String)obj.get("displayValue")));
						obj = (JSONObject)arr2.get(8);
						re.setRtotalShots(Integer.parseInt((String)obj.get("displayValue")));
					}
				}
				list.add(re);
			}
		} catch(Exception e) {
			System.out.println("!!!!!!!!!! cant parse !!!!!!!!!!");
			e.printStackTrace();
		}
		return list;
	}

	//경기의 데이터 가져오는 함수
	public static List<RecentEntity> getRmList(String addUrl) {
		String url = Const.RECENT_MATCHES + addUrl; // API URL
		String teamData = getData(url);
		
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		JSONArray arr = null;
		
		List<RecentEntity> list = new ArrayList<>();
		
		try {
			obj = (JSONObject)parser.parse(teamData);
			JSONArray event = (JSONArray)obj.get("events");
			
			for(int i=0;i<event.size();i++) {
				RecentEntity re = new RecentEntity();
				
				obj = (JSONObject)event.get(i);
				re.setId(Integer.parseInt((String)obj.get("id")));
				re.setDate((String)obj.get("date"));
				
				obj = (JSONObject)obj.get("status");
				obj = (JSONObject)obj.get("type");
				re.setCompleted((Boolean)obj.get("completed"));
				
				obj = (JSONObject)event.get(i);
				
				arr = (JSONArray)obj.get("competitions");
				obj = (JSONObject)arr.get(0);
				
				obj = (JSONObject)obj.get("venue");
				re.setVenue((String)obj.get("fullName"));
				
				obj = (JSONObject)arr.get(0);
				arr = (JSONArray)obj.get("competitors");
				
				obj = (JSONObject)arr.get(0);
				re.setLscore(Integer.parseInt((String)obj.get("score")));
				
				obj = (JSONObject)obj.get("team");
				re.setLteam((String)obj.get("name"));
				re.setLid(Integer.parseInt((String)obj.get("id")));
				
				obj = (JSONObject)arr.get(1);
				re.setRscore(Integer.parseInt((String)obj.get("score")));
				
				obj = (JSONObject)obj.get("team");
				re.setRteam((String)obj.get("name"));
				re.setRid(Integer.parseInt((String)obj.get("id")));
				// events안의 id date값, events->status->type의 completed값
				//events->competitions->competitors->score값, events->competitions->competitors->team->name값을 가져오는 과정
				
				list.add(re);
			}
		} catch(Exception e) {
			System.out.println("!!!!!!!!!! cant parse !!!!!!!!!!");
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<TeamEntity> getTeamsList() {
		String url = Const.TEAMS_STAT; // API URL
		String teamData = getData(url);
		
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		JSONArray arr = null;
		
		List<TeamEntity> list = new ArrayList<>();
		
		try {
			obj = (JSONObject)parser.parse(teamData);
			arr = (JSONArray)obj.get("sports");
			obj = (JSONObject)arr.get(0);
			arr = (JSONArray)obj.get("leagues");
			obj = (JSONObject)arr.get(0);
			arr = (JSONArray)obj.get("teams"); // sports -> leagues -> teams의 키값으로 들어가는 과정

			
			for(int i=0;i<arr.size();i++) {
				TeamEntity te = new TeamEntity();
				obj = (JSONObject)arr.get(i);
				obj = (JSONObject)obj.get("team");
				
				te.setId(Integer.parseInt((String)obj.get("id")));
				te.setName((String)obj.get("name"));
				//System.out.printf("id: %s, name %s\n", obj.get("id"), obj.get("name"));
				
				JSONObject obj2 = (JSONObject)obj.get("record");
				JSONArray arr2 = (JSONArray)obj2.get("items");
				obj2 = (JSONObject)arr2.get(0);
				arr2 = (JSONArray)obj2.get("stats");
				
				for(int j=0;j<arr2.size();j++) {
					obj2 = (JSONObject)arr2.get(j);
					
					switch(j) {
						case 0:
							te.setWins((double)obj2.get("value"));
							break;
						case 1:
							te.setLosses((double)obj2.get("value"));
							break;
						case 2:
							te.setTies((double)obj2.get("value"));
							break;
						case 3:
							te.setGamesPlayed((double)obj2.get("value"));
							break;
						case 4:
							te.setPointsFor((double)obj2.get("value"));
							break;
						case 5:
							te.setPointsAgainst((double)obj2.get("value"));
							break;
						case 6:
							te.setPoints((double)obj2.get("value"));
							break;
						case 7:
							te.setStreak((double)obj2.get("value"));
							break;
						case 8:
							te.setRankChange((double)obj2.get("value"));
							break;
						case 9:
							te.setRank((double)obj2.get("value"));
							break;
						case 10:
							te.setPointDifferential((double)obj2.get("value"));
							break;
						case 11:
							te.setHomePointsFor((double)obj2.get("value"));
							break;
						case 12:
							te.setHomePointsAgainst((double)obj2.get("value"));
							break;
						case 13:
							te.setHomeLosses((double)obj2.get("value"));
							break;
						case 14:
							te.setHomeWins((double)obj2.get("value"));
							break;
						case 15:
							te.setHomeTies((double)obj2.get("value"));
							break;
						case 16:
							te.setHomeGamesPlayed((double)obj2.get("value"));
							break;
						case 17:
							te.setAwayPointsFor((double)obj2.get("value"));
							break;
						case 18:
							te.setAwayPointsAgainst((double)obj2.get("value"));
							break;
						case 19:
							te.setAwayLosses((double)obj2.get("value"));
							break;
						case 20:
							te.setAwayWins((double)obj2.get("value"));
							break;
						case 21:
							te.setAwayTies((double)obj2.get("value"));
							break;
						case 22:
							te.setAwayGamesPlayed((double)obj2.get("value"));
							break;
						case 23:
							te.setDeductions((double)obj2.get("value"));
							break;
						case 24:
							te.setPpg((double)obj2.get("value"));
							break;
					}
					
					//System.out.printf("name: %s, value: %f\n", obj2.get("name"), obj2.get("value"));
				}
				list.add(te);
			}
		} catch(Exception e) {
			System.out.println("!!!!!!!!!! cant parse !!!!!!!!!!");
			e.printStackTrace();
		}
		return list;
	}
	
	private static String getData(String requestURL) {
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성

			HttpResponse response = client.execute(getRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				return body;
				
			} else {
				//System.out.println("response is error : " + response.getStatusLine().getStatusCode());
			}

		} catch (Exception e){
			System.err.println(e.toString());
		}
		return "ERROR";
	}
}