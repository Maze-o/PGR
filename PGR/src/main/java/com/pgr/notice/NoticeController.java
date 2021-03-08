package com.pgr.notice;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgr.Const;
import com.pgr.SecurityUtils;
import com.pgr.model.BoardEntity;
import com.pgr.model.NoticeDomain;
import com.pgr.model.NoticeEntity;

@Controller
public class NoticeController {

	@Autowired
	private SecurityUtils sUtils;
	
	@Autowired
	private NoticeService service;

	@GetMapping("/notice")
	public String home(NoticeDomain p, Model model) {
		model.addAttribute(Const.KEY_DATA, service.selNoticeList(p));

		return "menus/notice/home";
	}

	@GetMapping("/writeEdit1")
	public String writeEdit() {
		return "menus/notice/writeEdit1";
	}

	@PostMapping("/writeEdit1")
	@ResponseBody
	public Map<String, Object> writeEdit(@RequestBody NoticeEntity p) {
		System.out.println("p.getTitle() : " + p.getTitle());
		System.out.println("p : " + p);
		Map<String, Object> map = new HashMap<>();
		map.put(Const.KEY_RESULT, service.insNotice(p));
		return map;

	}

	@GetMapping("/detail1")
	public String detail(NoticeEntity p, Model model) {
		model.addAttribute(Const.KEY_DATA, service.selNotice(p));
		return "menus/notice/detail";
	}
	
	@GetMapping("/edit1")
	public String edit(NoticeEntity p, Model model) {
		model.addAttribute(Const.KEY_DATA, service.selNotice(p));
		return "/menus/notice/writeEdit1";
	}

	@PostMapping("/edit1")
	public String edit(NoticeEntity p, HttpSession hs) {
		p.setUserPk(sUtils.getLoginUserPk(hs));
		service.upNotice(p);
		System.out.println("noticePk" + p.getNoticePk());
		
		return "redirect:/detail?noticePk=" + p.getNoticePk();
	}

}
