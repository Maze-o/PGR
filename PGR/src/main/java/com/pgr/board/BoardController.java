package com.pgr.board;

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
import com.pgr.model.BoardDomain;
import com.pgr.model.BoardEntity;

@Controller
public class BoardController {

	@Autowired
	private SecurityUtils sUtils;
	
	@Autowired
	private BoardService service;

	@GetMapping("/home")
	public String home(BoardDomain p, Model model) {
		model.addAttribute(Const.KEY_DATA, service.selBoardList(p));

		return "menus/board/home";
	}

	@GetMapping("/writeEdit")
	public String writeEdit() {
		return "menus/board/writeEdit";
	}

	@PostMapping("/writeEdit")
	@ResponseBody
	public Map<String, Object> writeEdit(@RequestBody BoardEntity p) {
		System.out.println("p.getTitle() : " + p.getTitle());
		System.out.println("p : " + p);
		Map<String, Object> map = new HashMap<>();
		map.put(Const.KEY_RESULT, service.insBoard(p));
		return map;

	}

	@GetMapping("/detail")
	public String detail(BoardEntity p, Model model) {
		model.addAttribute(Const.KEY_DATA, service.selBoard(p));
		return "menus/board/detail";
	}
	
	@GetMapping("/edit")
	public String edit(BoardEntity p, Model model) {
		model.addAttribute(Const.KEY_DATA, service.selBoard(p));
		return "/menus/board/writeEdit";
	}

	@PostMapping("/edit")
	public String edit(BoardEntity p, HttpSession hs) {
		p.setUserPk(sUtils.getLoginUserPk(hs));
		service.upBoard(p);
		System.out.println("boardPk" + p.getBoardPk());

		return "redirect:/detail?boardPk=" + p.getBoardPk();
	}

}
