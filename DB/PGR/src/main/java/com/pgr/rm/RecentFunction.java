package com.pgr.rm;

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

public class RecentFunction {

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
				re.setId((String)temp.get("id"));
				re.setUid((String)temp.get("uid"));
				re.setDate((String)temp.get("date"));
				re.setName((String)temp.get("name"));
				re.setShortname((String)temp.get("shortName"));
				
				JSONObject status = (JSONObject)temp.get("status");
				JSONObject type = (JSONObject)status.get("type");
				re.setCompleted((Boolean)type.get("completed"));
				
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