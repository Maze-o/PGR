package com.pgr.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pgr.Const;
import com.pgr.model.BoardDTO;
import com.pgr.model.BoardDomain;
import com.pgr.model.BoardEntity;

@Controller
public class BoardController {

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
		map.put("write", service.insBoard(p));
		return map;
		
	}
}
