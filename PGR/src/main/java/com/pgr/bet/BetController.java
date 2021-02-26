package com.pgr.bet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BetController {
	
	@GetMapping("/betting")
	public String betting() {
		return "menus/betlist";
	}
	
	@GetMapping("/bettingroom")
	public String bettingroom() {
		return "menus/bettingroom";
	}
	
	@GetMapping("/endbetting")
	public String endbetting() {
		return "menus/endbetlist";
	}
	
	@GetMapping("/endbettingroom")
	public String endbettingroom() {
		return "menus/endbettingroom";
	}
}
