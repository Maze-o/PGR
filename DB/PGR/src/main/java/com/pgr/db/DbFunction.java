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
import com.pgr.rm.model.RecentEntity;

public class DbFunction {

	public static List<RecentEntity> getRmList() {
		String url = Const.RECENT_MATCHES; // API URL
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
				arr = (JSONArray)obj.get("competitors");
				
				obj = (JSONObject)arr.get(0);
				re.setLscore(Integer.parseInt((String)obj.get("score")));
				obj = (JSONObject)obj.get("team");
				re.setLteam((String)obj.get("name"));
				
				obj = (JSONObject)arr.get(1);
				re.setRscore(Integer.parseInt((String)obj.get("score")));
				obj = (JSONObject)obj.get("team");
				re.setRteam((String)obj.get("name"));
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
	
	private static String getData(String requestURL) {
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성

			HttpResponse response = client.execute(getRequest);

			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				System.out.println("response confirm");
				return body;
				
			} else {
				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
			}

		} catch (Exception e){
			System.err.println(e.toString());
		}
		return "ERROR";
	}
}