package com.pgr.chating.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.pgr.chating.handler.SocketHandler;


@Configuration //configuration을 클래스에 적용하고  @Bean을 해당 클래스의 메소드에 적용하면 @Autowried로 빈을 부를 수 있다. 
@EnableWebSocket //소켓통신을 하겠다
public class WebSocketConfig implements WebSocketConfigurer{ //소켓 내장인터페이스

	@Autowired
	SocketHandler socketHandler;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(socketHandler, "/chating/{roomNumber}"); //구분하는 방 이동 
	}
	
	
}
