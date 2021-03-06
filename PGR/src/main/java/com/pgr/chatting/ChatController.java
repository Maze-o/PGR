package com.pgr.chatting;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
	@GetMapping("/chatRecord")
	public List<String> chat() {
		return TempData.chat;
	}
	
}
