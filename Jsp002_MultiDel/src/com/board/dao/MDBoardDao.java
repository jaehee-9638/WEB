package com.board.dao;

import java.util.List;

import com.board.dto.MDBoardDto;

public interface MDBoardDao {

	String SELECT_LIST_SQL=" SELECT SEQ,WRITER,TITLE,CONTENT,REGDATE FROM MDBOARD ";
	String SELECT_ONE_SQL=" SELECT SEQ,WRITER,TITLE,CONTENT,REGDATE FROM MDBOARD WHERE SEQ=? ";
	String INSERT_SQL=" INSERT INTO MDBOARD VALUES ( MDBOARDSEQ.NEXTVAL,?,?,?,SYSDATE) ";
	String UPDATE_SQL=" UPDATE MDBOARD SET TITLE=?,CONTENT=? WHERE SEQ=? ";
	String DELETE_SQL=" DELETE FROM MDBOARD WHERE SEQ=? ";
	
	public List<MDBoardDto> selectList();
	public MDBoardDto selectOne(int seq); 
	public int insert (MDBoardDto dto);
	public int update (MDBoardDto dto);
	public int delete (int seq);
	public int multidel (String[]seq);
	
}
