package com.pgr.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgr.SecurityUtils;
import com.pgr.model.BoardDomain;
import com.pgr.model.BoardEntity;


@Service
public class BoardService {
	
	@Autowired
	private SecurityUtils sUtils;
	
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardDomain> selBoardList(BoardDomain p) {
		return mapper.selBoardList(p);
	}
	
	public int insBoard(BoardEntity p) {

		if (p.getTitle().equals("")) {
			return 0;
		}
		
		if(p.getCtnt().equals("")) {
			return 1;
		}
		
		mapper.insBoard(p);
		
		return 2;
	}
	
	public BoardEntity selBoard(BoardEntity p) {
		return mapper.selBoard(p);
	}
	
	public int upBoard(BoardEntity p) {
		return mapper.upBoard(p);
	}
}
