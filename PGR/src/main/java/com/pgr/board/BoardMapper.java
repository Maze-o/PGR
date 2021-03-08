package com.pgr.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pgr.model.BoardDomain;
import com.pgr.model.BoardEntity;

@Mapper
public interface BoardMapper {
	int insBoard(BoardEntity p);
	List<BoardDomain> selBoardList(BoardDomain p);
	BoardDomain selBoard(BoardEntity p);
	int upBoard(BoardEntity p);
}
