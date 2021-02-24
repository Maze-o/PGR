package com.pgr.bet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BetController {
	
	@GetMapping("/betting")
	public String betting() {
		return "menus/betlist";
	}
}
