package com.board.dao;

import java.util.List;

import com.board.dto.MDBoardDto;

public interface MDBoardDao {
	
	
	public List<MDBoardDto> selectList();
	public MDBoardDto selectOne(int seq);
	public int insert(MDBoardDto dto);
	public int update(MDBoardDto dto);
	public int delete(int seq);
	public int muldel(String[] seq);
}
