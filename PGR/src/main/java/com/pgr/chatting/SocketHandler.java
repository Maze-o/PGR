package com.pgr.chatting;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {

	HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		
		JSONParser parser = new JSONParser();
		JSONObject temps;
		try {
			temps = (JSONObject)parser.parse(message.getPayload());
			String msg = (String)temps.get("msg");
			String nickname = (String)temps.get("nickname");
			TempData ted = new TempData();
			ted.setMsg(msg);
			ted.setNickname(nickname);
			
			TempStatic.tData.add(ted);
			
			if(TempStatic.tData.size() >= 31) {
				TempStatic.tData.remove(0);
			}
			
			for(String key : sessionMap.keySet()) {
				WebSocketSession wss = sessionMap.get(key);
				try {
					JSONObject temp = new JSONObject();
					temp.put("type", "chat");
					temp.put("nickname", nickname);
					temp.put("value", msg);
					wss.sendMessage(new TextMessage(temp.toString()));
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//소켓 연결
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
		TempStatic.total++;
		
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				JSONObject temp = new JSONObject();
				temp.put("type", "status");
				temp.put("value", Integer.toString(TempStatic.total));
				wss.sendMessage(new TextMessage(temp.toString()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//소켓 종료
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
		TempStatic.total--;
		
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				JSONObject temp = new JSONObject();
				temp.put("type", "status");
				temp.put("value", Integer.toString(TempStatic.total));
				wss.sendMessage(new TextMessage(temp.toString()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
