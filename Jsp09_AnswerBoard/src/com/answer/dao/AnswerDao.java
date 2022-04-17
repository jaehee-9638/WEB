package com.answer.dao;

import java.util.List;

import com.answer.dto.AnswerDto;

public interface AnswerDao {

	String SELECE_LIST_SQL=" SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, TITLE, CONTENT, WRITER, REGDATE "
							+ " FROM ANSWERBOARD "
							+ " ORDER BY GROUPNO DESC, GROUPSEQ "; 
	String SELECE_ONE_SQL=" SELECT BOARDNO, GROUPNO, GROUPSEQ, TITLETAB, TITLE, CONTENT, WRITER, REGDATE "
							+ " FROM ANSWERBOARD "
							+ " WHERE BOARDNO=? ";
	String BOARD_INSERT_SQL=" INSERT INTO ANSWERBOARD VALUES(BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL, 1, 0,?,?,?, SYSDATE) ";
	String BOARD_UPDATE_SQL="  ";
	String BOARD_DELETE_SQL="  ";
	
	String ANSWER_INSERT_SQL="  ";
	String ANSWER_UPDATE_SQL="  ";
	
	
	public List<AnswerDto> selectList();
	public AnswerDto selectOne(int boardno);
	public int boardInsert(AnswerDto dto);
	public int boardUpdate(AnswerDto dto);
	public int boardDelete(int boardno);
	
	public int answerInsert(AnswerDto dto);
	public int answerUpdate(int parentBoardNo);
	
}
