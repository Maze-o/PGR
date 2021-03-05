package com.pgr.bet;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgr.model.BetDomain;
import com.pgr.model.BetEntity;
import com.pgr.model.RecentEntity;
import com.pgr.rm.RecentService;
import com.pgr.user.SecurityUtils;

@Controller
public class BetController {
	
	@Autowired
	private BetService bService;
	
	@Autowired
	SecurityUtils sUtils;
	
	@GetMapping("/betting")
	public String betting() {
		return "menus/bet/betlist";
	}
	
	@GetMapping("/bettingroom")
	public String bettingroom(Model model, HttpSession hs, BetEntity bet) {
		
		if(sUtils.getLoginUser(hs) != null) {
			BetEntity temp = new BetEntity();
			temp.setUserPk(sUtils.getLoginUser(hs).getUserPk());
			temp.setId(bet.getId());
			model.addAttribute("bet", bService.selBetUser(temp));
		}

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
	
	@ResponseBody
	@PostMapping("/bet")
	public int bet(@RequestBody BetEntity data, HttpSession hs) {
		data.setMyProperty(sUtils.getLoginUser(hs).getMyProperty());
		return bService.insBet(data);
	}
	
	@ResponseBody
	@GetMapping("/betallocation")
	public BetDomain betallocation() {
		return bService.selBetAllocation();
	}
}
