package com.pgr.bet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pgr.model.RecentEntity;
import com.pgr.rm.RecentService;

@Controller
public class BetController {
	
	@Autowired
	private BetService bService;
	
	@Autowired
	private RecentService rService;
	
	@GetMapping("/betting")
	public String betting() {
		return "menus/bet/betlist";
	}
	
	@GetMapping("/bettingroom")
	public String bettingroom(RecentEntity data) {
		return "menus/bet/bettingroom";
	}
	
	@GetMapping("/endbetting")
	public String endbetting() {
		return "menus/bet/endbetlist";
	}
	
	@GetMapping("/endbettingroom")
	public String endbettingroom() {
		return "menus/bet/endbettingroom";
	}
	
	@ResponseBody
	@GetMapping("/betroomlist")
	public List<RecentEntity> betroomlist() {
		return bService.selBettingRoomList();
	}
	
	@ResponseBody
	@GetMapping("/endbetroomlist")
	public List<RecentEntity> endbetroomlist() {
		return bService.selEndBettingroomList();
	}
}
