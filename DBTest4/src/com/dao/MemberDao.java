package com.dao;

import java.util.List;

import com.dto.MemberDto;

public interface MemberDao {
	
	String SELECT_LIST_SQL=" SELECT M_NO,M_NAME,M_AGE,M_GENDER,M_LOCATION,M_JOB,M_TEL,M_EMAIL " 
							+" FROM TB_MEMBER "+" ORDER BY M_N ASC ";
			
	String SELECT_ONE_SQL= " SELECT M_NO,M_NAME,M_AGE,M_GENDER,M_LOCATION,M_JOB,M_TEL,M_EMAIL " 
							+" FROM TB_MEMBER "+" WHERE M_NO=? ";
	
	String INSERT_SQL= " INSERT INTO TB_MEMBER "+"VALUES(MEMEBERSEQ.NEXTVAL,?,?,?,?,?,?,?)";
	
	String UPDATE_SQL= " UPDATE TB_MEMBER "+" WHERE M_NO=? ";
	
	String DELETE_SQL= "DELETE FROM TB_MEMBER "+" SET M_NAME=? , M_AGE=? , M_GENDER=? , M_LOCATION=? , M_JOB=? , M_TEL=? , M_EMAIL=? "+" WHERE M_NO=? ";

	public List<MemberDto> selectList();
	
	public MemberDto selectOne(int m_no);
	
	public int insert(MemberDto dto);
	
	public int update(MemberDto dto);
	
	public int delete(int m_no);

}
