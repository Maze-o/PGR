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

import com.pgr.SecurityUtils;
import com.pgr.model.BetDomain;
import com.pgr.model.BetDomain2;
import com.pgr.model.BetEntity;
import com.pgr.model.RecentEntity;

@Controller
public class BetController {
	
	@Autowired
	private BetService bService;
	
	@Autowired
	SecurityUtils sUtils;
	
	@GetMapping("/betting")
	public String betting() { //메뉴바에서 betting 누르면 바로 bettinglist로 이동
		return "menus/bet/betlist";
	}
	
	@GetMapping("/bettingroom")
	public String bettingroom(Model model, HttpSession hs, BetEntity bet) {
		//bettingroom에 입장시 로그인 했다면 UserPk값을 세션을 통해서 받고 id 값으로 어떤 경기인지 파악
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
	@GetMapping("/betUser")
	public BetEntity selBetUser(BetEntity p) {
		return bService.selBetUser(p);
	}
	
	@ResponseBody
	@PostMapping("/bet")
	public int bet(@RequestBody BetEntity data, HttpSession hs) {
		data.setMyProperty(sUtils.getLoginUser(hs).getMyProperty());
		return bService.insBet(data);
	}
	
	@ResponseBody
	@GetMapping("/betallocation")
	public BetDomain betallocation(BetEntity data) {
		BetDomain bet = bService.selBetAllocation(data);
		if(bet != null) { 
			return bet;
		} else { //배팅 참여가 전무한 경우에 allocation이 null로 다 넘어오기때문에 0으로 default값을 줌
			BetDomain temp = new BetDomain();
			temp.setL_allocation(0);
			temp.setD_allocation(0);
			temp.setW_allocation(0);
			
			return temp;
		}
	}
	
	@ResponseBody
	@GetMapping("/betUserPk")
	public List<BetDomain2> betUserPk(BetEntity data) {
		return bService.selBetUserPk(data);
	}
}
