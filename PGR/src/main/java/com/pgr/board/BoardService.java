package com.pgr.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgr.model.BoardDomain;
import com.pgr.model.BoardEntity;


@Service
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	public List<BoardDomain> selBoardList(BoardDomain p) {
		return mapper.selBoardList(p);
	}
	
	public int insBoard(BoardEntity p) {
		return mapper.insBoard(p);
	}
	
}
