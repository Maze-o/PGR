package com.pgr.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pgr.model.BoardDomain;
import com.pgr.model.BoardEntity;

@Mapper
public interface BoardMapper {
	List<BoardDomain> selBoardList(BoardDomain p);
	int insBoard(BoardEntity p);
}
