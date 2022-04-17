package com.board.biz;

import java.util.List;

import com.board.dao.BoardDao;
import com.board.dto.BoardDto;

public class BoardBiz {

	BoardDao dao = new BoardDao();
	public List<BoardDto> selectAllList(){
		return dao.selectAllList();
	}
	public BoardDto selectOneByTitle(int freecomm_seq) {
		return dao.selectOneByTitle(freecomm_seq);
	}
	public int insert(BoardDto dto) {
		return dao.insert(dto);
	}
	
	public int delete(int freecomm_seq) {
		
		return dao.delete(freecomm_seq);
	}
	
	public int update (BoardDto dto) {
		return dao.update(dto);
	}
	
	public int multidelete(String[] seq) {
		
		return dao.multidelete(seq);
	}
	/*
	 * public List<BoardDto> selectPaging(int page){ return dao.selectPaging(page);
	 * }
	 */
}
