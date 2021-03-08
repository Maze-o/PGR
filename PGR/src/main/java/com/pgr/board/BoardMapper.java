package com.pgr.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pgr.model.BoardDTO;
import com.pgr.model.BoardDomain;
import com.pgr.model.BoardEntity;

@Mapper
public interface BoardMapper {
	int insBoard(BoardEntity p);
	List<BoardDomain> selBoardList(BoardDTO p);
	BoardDomain selBoard(BoardEntity p);
	int upBoard(BoardEntity p);
	int selMaxPageNum(BoardDTO p);
}
