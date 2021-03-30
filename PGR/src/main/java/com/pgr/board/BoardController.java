package com.pgr.board;

import java.util.HashMap;
import java.util.List;
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
import com.pgr.model.BoardDomain;
import com.pgr.model.BoardEntity;

@Controller
public class BoardController {

	@Autowired
	private SecurityUtils sUtils;
	
	@Autowired
	private BoardService service;

	@GetMapping("/gallery")
	public String home() {
		return "/menus/board/list";
	}
	
	// 페이징 ajax처리
	@GetMapping("/listData")
	@ResponseBody
	public List<BoardDomain> listData(BoardDTO p) {
		return service.selBoardList(p);
	}

	// 페이징 max값 구하는 처리
	@ResponseBody
	@GetMapping("/getMaxPageNum")
	public int selMaxPageNum(BoardDTO p) {
		return service.selMaxPageNum(p);
	}
	

	@GetMapping("/write")
	public String writeEdit() {
		return "menus/board/writeEdit";
	}

	// 글쓰기 ajax처리
	@PostMapping("/write")
	@ResponseBody
	public Map<String, Object> writeEdit(@RequestBody BoardEntity p) {
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

	// 글수정 ajax처리
	@PostMapping("/edit")
	@ResponseBody
	public Map<String, Object> edit(@RequestBody BoardEntity p, HttpSession hs) {
		p.setUserPk(sUtils.getLoginUserPk(hs));
		
		Map<String, Object> map = new HashMap<>();
		map.put(Const.KEY_RESULT, service.upBoard(p));
		return map;
	}
	
	// delete ajax처리
	@ResponseBody
	@DeleteMapping("/del/{boardPk}")
	public Map<String, Object> delBoard(BoardEntity p, HttpSession hs) {

		p.setUserPk(sUtils.getLoginUserPk(hs));

		Map<String, Object> map = new HashMap<>();
		map.put(Const.KEY_DATA, service.delBoard(p));
		return map;
		
	}
	

}
