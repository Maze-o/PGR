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
		String url = Const.RECENT_MATCHES;
		String teamData = getData(url);
		
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		
		List<RecentEntity> list = new ArrayList<>();
		
		try {
			obj = (JSONObject)parser.parse(teamData);
			JSONArray event = (JSONArray)obj.get("events");
			
			for(int i=0;i<event.size();i++) {
				JSONObject temp = (JSONObject)event.get(i);
				
				RecentEntity re = new RecentEntity();
				re.setId(Integer.parseInt((String)temp.get("id")));
				re.setDate((String)temp.get("date"));
				
				JSONObject status = (JSONObject)temp.get("status");
				JSONObject type = (JSONObject)status.get("type");
				re.setCompleted((Boolean)type.get("completed"));
				
				JSONArray competitions = (JSONArray)temp.get("competitions");
				JSONObject left = (JSONObject)competitions.get(0);
				competitions = (JSONArray)left.get("competitors");
				
				left = (JSONObject)competitions.get(0);
				JSONObject right = (JSONObject)competitions.get(1);
				
				re.setLscore(Integer.parseInt((String)left.get("score")));
				re.setRscore(Integer.parseInt((String)right.get("score")));
				
				left = (JSONObject)left.get("team");
				re.setLteam((String)left.get("name"));
				right = (JSONObject)right.get("team");
				re.setRteam((String)right.get("name"));
				
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