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

	// insert
	public int insBoard(BoardEntity p) {

		if (p.getTitle().equals("")) { // 제목이 비어있으면 0 리턴
			return 0;
		}

		if (p.getCtnt().equals("")) { // 내용이 비어있으면 1 리턴
			return 1;
		}

		mapper.insBoard(p); // 위에 if문에 안걸리면 insert

		return 2;
	}

	// select
	public BoardDomain selBoardDetail(BoardEntity p, HttpSession hs) {
		if (sUtils.getLoginUser(hs) != null) { // 세션에 로그인 정보가 들어있으면(로그인이 되어 있으면) 실행
			BoardEntity p2 = new BoardEntity();
			p2.setBoardPk(p.getBoardPk());
			p2.setHits(1); // 글을 볼때마다 조회수 증가
			mapper.upBoard(p2);
		}

		return mapper.selBoard(p);
	}

	// select
	public BoardEntity selBoard(BoardEntity p) {
		return mapper.selBoard(p);
	}

	// 페이징
	public List<BoardDomain> selBoardList(BoardDTO p) {
		int sIdx = (p.getPage() - 1) * p.getRowCnt();
		p.setsIdx(sIdx);

		return mapper.selBoardList(p);
	}

	// max값 구하기
	public int selMaxPageNum(BoardDTO p) {
		return mapper.selMaxPageNum(p);
	}

	// update
	public int upBoard(BoardEntity p) {

		if (p.getTitle().equals("")) { // 제목이 비어있으면 0 리턴
			return 0;
		}

		if (p.getCtnt().equals("")) { // 내용이 비어있으면 1리턴
			return 1;
		}

		mapper.upBoard(p);

		return 2; // 문제 없으면 2 리턴
	}

	// delete
	public int delBoard(BoardEntity p) {
		p.setIsDel(1); // 삭제되면 isDel을 0 -> 1으로 처리하고 리스트에 보이지 않게 함 (DB에는 정보가 남는다)
		return mapper.upBoard(p);
	}

}
