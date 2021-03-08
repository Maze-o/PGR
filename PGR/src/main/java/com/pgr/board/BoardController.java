package com.pgr.board;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;

import com.pgr.Const;
import com.pgr.SecurityUtils;
import com.pgr.model.BoardDTO;
import com.pgr.model.BoardEntity;

@Controller
public class BoardController {

	@Autowired
	private SecurityUtils sUtils;
	
	@Autowired
	private BoardService service;

	@GetMapping("/gallery")
	public String home(BoardDTO p, Model model) {
		model.addAttribute(Const.KEY_DATA, service.selBoardList(p));

		return "menus/board/home";
	}

	@GetMapping("/write")
	public String writeEdit() {
		return "menus/board/writeEdit";
	}

	@PostMapping("/write")
	@ResponseBody
	public Map<String, Object> writeEdit(@RequestBody BoardEntity p) {
		System.out.println("p.getTitle() : " + p.getTitle());
		System.out.println("p : " + p);
		Map<String, Object> map = new HashMap<>();
		map.put(Const.KEY_RESULT, service.insBoard(p));
		return map;

	}

	@GetMapping("/detail")
	public String detail(BoardEntity p, Model model, HttpSession hs) {
		model.addAttribute(Const.KEY_DATA, service.selBoardDetail(p, hs));
		return "menus/board/detail";
	}

	
	@GetMapping("/edit")
	public String edit(BoardEntity p, Model model) {
		model.addAttribute(Const.KEY_DATA, service.selBoard(p));
		return "/menus/board/writeEdit";
	}

	@PostMapping("/edit")
	@ResponseBody
	public Map<String, Object> edit(@RequestBody BoardEntity p, HttpSession hs) {
		p.setUserPk(sUtils.getLoginUserPk(hs));
		
		Map<String, Object> map = new HashMap<>();
		map.put(Const.KEY_RESULT, service.upBoard(p));
		return map;
	}
	
	@ResponseBody
	@DeleteMapping("/del/{boardPk}")
	public Map<String, Object> delBoard(BoardEntity p, HttpSession hs) {
		p.setUserPk(sUtils.getLoginUserPk(hs));
		
		System.out.println("boardPk : " + p.getBoardPk());
		Map<String, Object> map = new HashMap<>();
		map.put(Const.KEY_DATA, service.delBoard(p));
		return map;
		
	}
	
	@ResponseBody
	@GetMapping("/getMaxPageNum")
	public String selMaxPageNum(BoardDTO p) {
		service.selMaxPageNum(p);
		return "/menus/board/home";
	}
	

}
