package com.pgr.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/index")
	public void index() {}
	
	@GetMapping("/sign_in")
	public String signin() {
		return "menus/sign_in";
	}
	
	@GetMapping("/sign_up")
	public String signup() {
		return "menus/sign_up";
	}
}
