package com.pgr.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgr.SecurityUtils;
import com.pgr.model.BoardDTO;
import com.pgr.model.BoardDomain;
import com.pgr.model.BoardEntity;


@Service
public class BoardService {
	
	@Autowired
	private SecurityUtils sUtils;
	
	@Autowired
	private BoardMapper mapper;
	
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
	
	public BoardDomain selBoardDetail(BoardEntity p, HttpSession hs) {
		if (sUtils.getLoginUser(hs) != null) {
			BoardEntity p2 = new BoardEntity();
			p2.setBoardPk(p.getBoardPk());
			p2.setHits(1);
			mapper.upBoard(p2);
		}

		return mapper.selBoard(p);
	}

	public BoardEntity selBoard(BoardEntity p) {
		return mapper.selBoard(p);
	}
	
	public int delBoard(BoardEntity p) {
		p.setIsDel(1);
		return mapper.upBoard(p);
	}
	
	public int upBoard(BoardEntity p) {
		if (p.getTitle().equals("")) {
			return 0;
		}
		
		if(p.getCtnt().equals("")) {
			return 1;
		}

		mapper.upBoard(p);
		
		return 2;
	}
	
	public int selMaxPageNum(BoardDTO p) {
		return mapper.selMaxPageNum(p);
	}
	
	public List<BoardDomain> selBoardList(BoardDTO p) {
		int sIdx = (p.getPage() - 1) * p.getRowCnt();
		p.setsIdx(sIdx);
		
		return mapper.selBoardList(p);
	}

}
