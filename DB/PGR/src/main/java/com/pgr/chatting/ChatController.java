package com.pgr.chatting;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.pgr.model.ChatMessage;

@Controller
public class ChatController {
	/* 
		"/app"로 시작하는 대상이 있는 클라이언트에서 보낸 모든 메시지는  @MessageMapping으로 메서드 라우팅
	*/
	@MessageMapping("/chat.sendMessage") // /app/chat.sendMessage인 메시지는 sendMessage()로 라우팅
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}
	
	@MessageMapping("/chat.addUser") // /app/chat.addUser인 메시지는 addUser()로 라우팅
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}
}
