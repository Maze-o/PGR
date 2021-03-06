package com.pgr.chatting;

import java.util.HashMap;

import org.json.simple.JSONObject;
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
		//메시지 발송
		String msg = message.getPayload();
		
		if(TempData.chat.size() >= 30) {
			TempData.chat.remove(0);
			TempData.chat.add(msg);
		} else {
			TempData.chat.add(msg);
		}
		
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				JSONObject temp = new JSONObject();
				temp.put("type", "chat");
				temp.put("value", msg);
				wss.sendMessage(new TextMessage(temp.toString()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//소켓 연결
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
		TempData.total++;
		
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				JSONObject temp = new JSONObject();
				temp.put("type", "status");
				temp.put("value", Integer.toString(TempData.total));
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
		TempData.total--;
		
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				JSONObject temp = new JSONObject();
				temp.put("type", "status");
				temp.put("value", Integer.toString(TempData.total));
				wss.sendMessage(new TextMessage(temp.toString()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
